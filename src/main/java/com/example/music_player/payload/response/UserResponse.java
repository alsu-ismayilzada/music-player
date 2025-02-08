package com.example.music_player.payload.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {

    String email;
    List<PlayListResponse> playLists;
    List<MusicResponse> likedMusics;
}
