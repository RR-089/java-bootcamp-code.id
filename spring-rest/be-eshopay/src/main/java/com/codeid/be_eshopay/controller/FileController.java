package com.codeid.be_eshopay.controller;

import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/files")
public class FileController {

    private final Path fileStorageLocation;
    private final ServletContext servletContext;

    public FileController(@Value("${file.upload-dir}") String uploadDir, ServletContext servletContext) {
        this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
        this.servletContext = servletContext;
    }

    @GetMapping("/{fileName:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String fileName) {
        try {
            Path filePath = fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                String mimeType = Files.probeContentType(filePath);
                if (mimeType == null) {
                    mimeType = servletContext.getMimeType(filePath.toString());
                }

                if (mimeType == null) {
                    mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
                }

                return ResponseEntity.ok()
                                     .header(HttpHeaders.CONTENT_TYPE, mimeType)
                                     .header(HttpHeaders.CONTENT_DISPOSITION, mimeType.startsWith("image/")
                                             ? "inline"
                                             : "attachment; filename=\"" + resource.getFilename() + "\"")
                                     .body(resource);
            } else {
                throw new RuntimeException("File not found: " + fileName);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving file: " + fileName, e);
        }
    }
}
