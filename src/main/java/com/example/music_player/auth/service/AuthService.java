package com.example.music_player.auth.service;

import com.example.music_player.auth.payload.request.AuthRequest;
import com.example.music_player.auth.payload.request.AuthResponse;
import com.example.music_player.model.User;
import com.example.music_player.payload.response.UserResponse;

public interface AuthService {

    AuthResponse userRegister(AuthRequest request);
    UserResponse userLogin(String token);
    AuthResponse refreshToken(String refreshToken);
}
