package com.example.music_player.payload.request;

import lombok.Data;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Data
public class PlayListRequest {

    @NotNull(message = "Title must not be empty")
    private String title;

    @NotNull(message = "User must not be empty")
    private Long user;
    private List<Long> musics;
}
