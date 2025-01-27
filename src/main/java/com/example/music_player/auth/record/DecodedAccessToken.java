package com.example.music_player.auth.record;

import java.util.List;

public record DecodedAccessToken(
        long id,
        String mail,
        List<String> roles
) {
}