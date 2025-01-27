package com.example.music_player.payload.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MusicSearchRequest {

    String title;
    String artist;
    String albumTitle;
    String genre;
}
