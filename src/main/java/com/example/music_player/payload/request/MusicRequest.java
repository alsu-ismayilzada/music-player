package com.example.music_player.payload.request;

import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MusicRequest {

    @NotNull(message = "Title must not be empty")
    private String title;

    @NotNull(message = "Artist must not be empty")
    private String artist;

    @NotNull(message = "Album must not be empty")
    private Long album;

    @Transient
    private MultipartFile file;

    private String genre;

    private byte[] poster;

    @NotNull(message = "Duration must not be empty")
    private int duration;

}
