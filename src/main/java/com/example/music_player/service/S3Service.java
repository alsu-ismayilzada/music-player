package com.example.music_player.service;

import org.springframework.web.multipart.MultipartFile;

public interface S3Service {

    String uploadMP3(MultipartFile file);

    String downloadMp3(String fileName);

    String uploadPoster(MultipartFile file);

    String downloadPoster(String path);
}
