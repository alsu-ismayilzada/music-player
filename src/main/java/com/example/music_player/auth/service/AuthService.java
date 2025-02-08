package com.example.music_player.auth.service;

import com.example.music_player.auth.payload.request.AuthRequest;
import com.example.music_player.auth.payload.request.AuthResponse;
import com.example.music_player.model.User;

public interface AuthService {

    AuthResponse userRegister(AuthRequest request);
    User userLogin(String token);
    AuthResponse refreshToken(String refreshToken);
}
