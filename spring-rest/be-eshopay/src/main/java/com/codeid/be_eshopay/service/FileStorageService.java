package com.codeid.be_eshopay.service;

import com.codeid.be_eshopay.model.dto.FileMetaDataDTO;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileStorageService {
    String storeFileWithRandomName(MultipartFile file) throws Exception;

    Resource loadFile(String fileName) throws Exception;

    void deleteFile(String fileName) throws Exception;

    List<FileMetaDataDTO> bulkStoreFiles(List<MultipartFile> files) throws Exception;

    void bulkDeleteFiles(List<String> fileNames) throws Exception;
}
