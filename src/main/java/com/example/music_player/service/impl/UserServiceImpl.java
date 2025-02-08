package com.example.music_player.service.impl;

import com.example.music_player.mapper.UserMapper;
import com.example.music_player.model.User;
import com.example.music_player.payload.response.UserResponse;
import com.example.music_player.repository.UserRepository;
import com.example.music_player.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User findById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User nor found"));
    }

    @Override
    public UserResponse findUserResponseById(Long id) {
        var user = findById(id);
        return userMapper.toUserResponse(user);
    }

    @Override
    public UserDetails loadUserByUsername(Long username) {
        return null;
    }
}
