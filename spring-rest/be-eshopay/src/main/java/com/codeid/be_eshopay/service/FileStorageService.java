package com.codeid.be_eshopay.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    String storeFileWithRandomName(MultipartFile file) throws Exception;

    Resource loadFile(String fileName) throws Exception;

    void deleteFile(String fileName) throws Exception;
}
