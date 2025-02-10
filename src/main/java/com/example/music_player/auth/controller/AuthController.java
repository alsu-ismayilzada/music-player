package com.example.music_player.auth.controller;

import com.example.music_player.auth.payload.request.AuthRequest;
import com.example.music_player.auth.payload.request.AuthResponse;
import com.example.music_player.auth.service.AuthService;
import com.example.music_player.model.User;
import com.example.music_player.payload.response.UserResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("${app.api_base_url}/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully registered"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> userRegister(@RequestBody AuthRequest request) {
        return new ResponseEntity<>(authService.userRegister(request), CREATED);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully logged in"),
            @ApiResponse(responseCode = "400", description = "Invalid credentials")
    })
    @PostMapping("/login")
    public ResponseEntity<UserResponse> userLogin(@RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(authService.userLogin(token), OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully refreshed token"),
            @ApiResponse(responseCode = "400", description = "Invalid refresh token")
    })
    @PostMapping("/refresh-token")
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody String refreshToken) {
        return new ResponseEntity<>(authService.refreshToken(refreshToken), OK);
    }
}
