package com.example.music_player.auth.service.impl;

import com.example.music_player.auth.model.Role;
import com.example.music_player.auth.payload.request.AuthRequest;
import com.example.music_player.auth.payload.request.AuthResponse;
import com.example.music_player.auth.record.DecodedRefreshToken;
import com.example.music_player.auth.service.AuthService;
import com.example.music_player.mapper.UserMapper;
import com.example.music_player.model.User;
import com.example.music_player.payload.response.UserResponse;
import com.example.music_player.repository.UserRepository;
import com.example.music_player.util.JwtGenerateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtGenerateUtil jwtGenerateUtil;

    @Override
    public AuthResponse userRegister(AuthRequest request) {
        if(!userRepository.existsByEmail(request.getEmail())){
           var user = userMapper.toUserEntity(request);
           userRepository.save(user);
            return getAuthResponse(user.getId(), user.getEmail(), user.getRoles());
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already in use");
        }
    }

    @Override
    public UserResponse userLogin(String token) {
        try {
            var decodedAccessToken = jwtGenerateUtil.decodeAccessToken(token);
            var email = decodedAccessToken.mail();
            var userOptional = userRepository.findByEmail(email);

            if (userOptional.isPresent()) {
                return userMapper.toUserResponse(userOptional.get());
            } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authentication failed", e);
        }
    }

    @Override
    public AuthResponse refreshToken(String refreshToken) {
        try {
            DecodedRefreshToken decodedRefreshToken = jwtGenerateUtil.decodeRefreshToken(refreshToken);

            return getAuthResponse(decodedRefreshToken.userId(), decodedRefreshToken.mail(), decodedRefreshToken.roles());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid or expired refresh token", e);
        }
    }

    private AuthResponse getAuthResponse(Long userId, String email, List<Role> roles) {
        var accessToken = jwtGenerateUtil.createAccessToken(userId, email, roles);
        var refreshToken = jwtGenerateUtil.createRefreshToken(userId, email);

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
