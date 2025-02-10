package com.example.music_player.service.impl;

import com.example.music_player.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.zip.GZIPOutputStream;

@Service
@RequiredArgsConstructor
public class S3ServiceImpl implements S3Service {

    private final S3Client s3Client;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    public String uploadMP3(MultipartFile file) {
        try {
            String fileName = "mp3/" + file.getOriginalFilename();
            return uploadFile(file, fileName);

        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Failed to upload mp3 file", e);
        }
    }

    @Override
    public String uploadPoster(MultipartFile file) {
        try {
            String fileName = "img/" + file.getOriginalFilename();
            return uploadFile(file, fileName);

        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Failed to upload img file", e);
        }
    }

    @Override
    public String downloadMp3(String path) {
        var fileName = "mp3/" + extractFileName(path);
        try {
            return downloadFile(fileName);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Failed to upload mp3 file", e);
        }
    }

    @Override
    public String downloadPoster(String path) {
        var fileName = "img/" + extractFileName(path);
        try {
            return downloadFile(fileName);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Failed to upload img file", e);
        }
    }

    public String extractFileName(String path) {
        return path.substring(path.lastIndexOf("/") + 1);
    }

    private String uploadFile(MultipartFile file, String fileName) throws IOException {
        s3Client.putObject(
                PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(fileName)
                        .contentType(file.getContentType())
                        .build(),
                RequestBody.fromBytes(file.getBytes())
        );
        return "https://" + bucketName + ".s3.amazonaws.com/" + fileName;
    }

    private String downloadFile(String fileName) throws IOException {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();

        ResponseInputStream<GetObjectResponse> s3ObjectResponse = s3Client.getObject(getObjectRequest);
        byte[] content = s3ObjectResponse.readAllBytes();

        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        try (GZIPOutputStream gzipStream = new GZIPOutputStream(byteStream)) {
            gzipStream.write(content);
        }

        return Base64.getEncoder().encodeToString(byteStream.toByteArray());
    }

}
