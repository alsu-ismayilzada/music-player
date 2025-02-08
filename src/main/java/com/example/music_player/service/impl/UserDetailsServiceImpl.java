package com.example.music_player.service.impl;

import com.example.music_player.payload.response.UserResponse;
import com.example.music_player.service.PlayListService;
import com.example.music_player.service.UserDetailsService;
import com.example.music_player.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;
    private final PlayListService playListService;

    @Override
    public UserResponse findUserResponseById(Long id) {
        var userResponse = userService.findUserResponseById(id);
        var playLists = playListService.findAllPlayListsByUserId(id);
        userResponse.setPlayLists(playLists);
        return userResponse;
    }
}
