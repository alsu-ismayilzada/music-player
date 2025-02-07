package com.example.music_player.service.impl;

import com.example.music_player.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class S3ServiceImpl implements S3Service {

    private final S3Client s3Client;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    public String uploadFile(MultipartFile file) {
        try {
            String fileName = "mp3/" + file.getOriginalFilename();

            s3Client.putObject(
                    PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(fileName)
                            .contentType(file.getContentType())
                            .build(),
                    RequestBody.fromBytes(file.getBytes())
            );

            return "https://" + bucketName + ".s3.amazonaws.com/" + fileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file", e);
        }
    }

    @Override
    public String downloadFile(String fileName) {
        try {
            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileName)
                    .build();

            ResponseInputStream<GetObjectResponse> s3ObjectResponse = s3Client.getObject(getObjectRequest);
            byte[] content = s3ObjectResponse.readAllBytes();

            return Base64.getEncoder().encodeToString(content);
        } catch (Exception e) {
            throw new RuntimeException("Failed to download file: " + fileName, e);
        }
    }
}
