package com.example.music_player.controller;

import com.example.music_player.payload.response.UserResponse;
import com.example.music_player.service.UserDetailsService;
import com.example.music_player.service.UserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${app.api_base_url}/users")
@RequiredArgsConstructor
public class UserController {

    private final UserDetailsService userService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{userId}")
    public UserResponse getUserById(@PathVariable Long userId) {
        return userService.findUserResponseById(userId);
    }
}
