package com.example.music_player.auth.payload.request;

import lombok.Data;

@Data
public class AuthResponse {

    private String accessToken;
    private String refreshToken;
}
