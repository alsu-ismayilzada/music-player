package com.example.music_player.service;

import com.example.music_player.model.User;
import com.example.music_player.payload.response.UserResponse;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    User findById(long id);
    UserResponse findUserResponseById(Long id);
    UserDetails loadUserByUsername(Long id);

}
