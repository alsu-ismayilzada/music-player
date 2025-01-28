package com.example.music_player.payload.response;

import lombok.Data;

@Data
public class AlbumResponse {

    private Long id;
    private String title;
    private String artist;
}
