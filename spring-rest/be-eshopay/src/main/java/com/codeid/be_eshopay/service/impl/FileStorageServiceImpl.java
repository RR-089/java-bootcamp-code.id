package com.codeid.be_eshopay.service.impl;

import com.codeid.be_eshopay.service.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final Path fileStorageLocation;

    public FileStorageServiceImpl(@Value("${file.upload-dir}") String uploadDir) throws Exception {
        this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
        Files.createDirectories(this.fileStorageLocation);
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
}
