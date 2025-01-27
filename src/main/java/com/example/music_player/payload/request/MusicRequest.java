package com.example.music_player.payload.request;

import jakarta.validation.constraints.NotNull;

public class MusicRequest {

    @NotNull(message = "Title must not be empty")
    private String title;

    @NotNull(message = "Artist must not be empty")
    private String artist;

    @NotNull(message = "Album must not be empty")
    private Long album;

    private String genre;

    @NotNull(message = "Duration must not be empty")
    private int duration;

}
