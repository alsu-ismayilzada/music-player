package com.example.music_player.service;

import org.springframework.web.multipart.MultipartFile;

public interface S3Service {

    String uploadFile(MultipartFile file);

    String downloadFile(String fileName);
}
