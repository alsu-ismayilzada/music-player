package com.example.music_player.service.impl;

import com.example.music_player.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    @Override
    public UserDetails loadUserByUsername(Long username) {
        return null;
    }
}
