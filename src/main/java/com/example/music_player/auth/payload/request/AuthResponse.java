package com.example.music_player.auth.payload.request;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class AuthResponse {

    private String accessToken;
    private String refreshToken;
}
