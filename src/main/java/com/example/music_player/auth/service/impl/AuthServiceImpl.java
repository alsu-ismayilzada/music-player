package com.example.music_player.auth.service.impl;

import com.example.music_player.auth.payload.request.AuthRequest;
import com.example.music_player.auth.payload.request.AuthResponse;
import com.example.music_player.auth.record.DecodedRefreshToken;
import com.example.music_player.auth.service.AuthService;
import com.example.music_player.mapper.UserMapper;
import com.example.music_player.repository.UserRepository;
import com.example.music_player.util.JwtGenerateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
           var accessToken = jwtGenerateUtil.createAccessToken(user.getId(), user.getEmail(), user.getRoles());
           var refreshToken = jwtGenerateUtil.createRefreshToken(user.getId(), user.getEmail());
           var response = new AuthResponse();
           response.setAccessToken(accessToken);
           response.setRefreshToken(refreshToken);
           return response;
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already in use");
        }
    }

    @Override
    public AuthResponse userLogin(String token) {

        return null;
    }

    @Override
    public AuthResponse refreshToken(String refreshToken) {
        try {
            DecodedRefreshToken decodedRefreshToken = jwtGenerateUtil.decodeRefreshToken(refreshToken);

            String newAccessToken = jwtGenerateUtil.createAccessToken(decodedRefreshToken.userId(), decodedRefreshToken.mail(), decodedRefreshToken.roles());

            AuthResponse response = new AuthResponse();
            response.setAccessToken(newAccessToken);
            return response;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid or expired refresh token", e);
        }
    }
}
