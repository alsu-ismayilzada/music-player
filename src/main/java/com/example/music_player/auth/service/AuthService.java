package com.example.music_player.auth.service;

import com.example.music_player.auth.payload.request.AuthRequest;
import com.example.music_player.auth.payload.request.AuthResponse;

public interface AuthService {

    AuthResponse userRegister(AuthRequest request);
    AuthResponse userLogin(String token);
    AuthResponse refreshToken(String refreshToken);
}
