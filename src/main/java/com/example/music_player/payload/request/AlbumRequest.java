package com.example.music_player.payload.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AlbumRequest {

    @NotNull(message = "Title must not be empty")
    private String title;

    @NotNull(message = "Artist must not be empty")
    private String artist;
}
