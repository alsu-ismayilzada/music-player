package com.example.music_player.payload.response;

import com.example.music_player.model.Music;
import lombok.Data;

import java.util.List;

@Data
public class PlayListResponse {

    Long id;
    private String title;
    private List<Music> musics;
}
