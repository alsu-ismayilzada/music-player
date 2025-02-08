package com.example.music_player.service;

import com.example.music_player.payload.response.UserResponse;

public interface UserDetailsService {

    UserResponse findUserResponseById(Long id);
}
