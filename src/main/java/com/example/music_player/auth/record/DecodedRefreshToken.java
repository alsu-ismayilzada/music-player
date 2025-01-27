package com.example.music_player.auth.record;

import com.example.music_player.auth.model.Role;

import java.util.List;

public record DecodedRefreshToken(
        String mail,
        Long userId,
        List<Role> roles
) {
}