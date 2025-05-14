package com.codeid.be_eshopay.service.impl;

import com.codeid.be_eshopay.model.dto.FileMetaDataDTO;
import com.codeid.be_eshopay.model.entity.FileMetaData;
import com.codeid.be_eshopay.service.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final Path fileStorageLocation;

    public FileStorageServiceImpl(@Value("${file.upload-dir}") String uploadDir) throws Exception {
        this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
        Files.createDirectories(this.fileStorageLocation);
    }

    public static FileMetaData mapToEntity(FileMetaDataDTO dto) {
        return FileMetaData.builder()
                           .fileName(dto.getFileName())
                           .fileSize(dto.getFileSize())
                           .fileType(dto.getFileType())
                           .fileUri(dto.getFileUri())
                           .index(dto.getIndex())
                           .build();
    }

    public static FileMetaDataDTO mapToDto(FileMetaData entity) {
        return FileMetaDataDTO.builder()
                              .fileName(entity.getFileName())
                              .fileSize(entity.getFileSize())
                              .fileType(entity.getFileType())
                              .fileUri(entity.getFileUri())
                              .index(entity.getIndex())
                              .build();
    }

    @Override
    public String storeFileWithRandomName(MultipartFile file) throws Exception {
        // Dapatkan ekstensi file asli
        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileExtension = "";

        if (originalFileName.contains(".")) {
            fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        }

        // Generate nama file random
        String randomFileName = UUID.randomUUID().toString() + fileExtension;

        try {
            // Validasi nama file
            if (randomFileName.contains("..")) {
                throw new Exception("Invalid file name: " + randomFileName);
            }

            // Simpan file
            Path targetLocation = this.fileStorageLocation.resolve(randomFileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return randomFileName;
        } catch (IOException ex) {
            throw new Exception("Failed to save file " + randomFileName, ex);
        }
    }

    // Metode untuk mendapatkan file berdasarkan nama random
    @Override
    public Resource loadFile(String fileName) throws Exception {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return resource;
            } else {
                throw new Exception("File not found: " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new Exception("File not found: " + fileName, ex);
        }
    }

    @Override
    public void deleteFile(String fileName) throws Exception {
        try {
            if (fileName == null || fileName.trim().isEmpty()) {
                return;
            }

            if (fileName.contains("..")) {
                throw new Exception("Invalid file name: " + fileName);
            }

            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();

            if (!Files.exists(filePath)) {
                throw new Exception("File not found: " + fileName);
            }

            Files.delete(filePath);
        } catch (IOException ex) {
            throw new Exception("Failed to delete file: " + fileName, ex);
        }
    }


    @Override
    public List<FileMetaDataDTO> bulkStoreFiles(List<MultipartFile> files) throws Exception {
        return files.stream().map(file -> {
            String originalFileName = file.getOriginalFilename();
            String randomFileName = UUID.randomUUID() + (originalFileName != null ? getExtension(originalFileName) : "");
            try {
                Path targetLocation = this.fileStorageLocation.resolve(randomFileName).normalize();
                Files.copy(file.getInputStream(), targetLocation);

                String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                                                            .path("/files/")
                                                            .path(randomFileName)
                                                            .toUriString();

                return FileMetaDataDTO.builder()
                                      .fileName(randomFileName)
                                      .fileSize((float) file.getSize() / 1024) // Size in KB
                                      .fileType(file.getContentType())
                                      .fileUri(fileUri)
                                      .index(files.indexOf(file))
                                      .build();
            } catch (IOException ex) {
                throw new RuntimeException("Could not store file " + randomFileName + ". Please try again!", ex);
            }
        }).toList();
    }

    @Override
    public void bulkDeleteFiles(List<String> uris) throws Exception {
        for (String uri : uris) {
            try {
                String fileName = Paths.get(new URI(uri).getPath()).getFileName().toString();
                System.out.println(fileName);
                
                this.deleteFile(fileName);
            } catch (IOException ex) {
                throw new RuntimeException("Could not delete file for URI " + uri + ". Please try again!", ex);
            }
        }
    }

    private String getExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf(".");
        return (lastIndex == -1) ? "" : fileName.substring(lastIndex);
    }
}
