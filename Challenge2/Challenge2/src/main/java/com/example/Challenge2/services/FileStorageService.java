package com.example.Challenge2.services;

import com.example.Challenge2.configurations.FileStorageProperties;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    String storeFile(MultipartFile file);

    Resource loadFileAsResource(String fileName);
}
