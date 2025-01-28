package com.example.music_player.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    UserDetails loadUserByUsername(Long id);
}
