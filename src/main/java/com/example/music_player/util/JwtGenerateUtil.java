package com.example.music_player.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.music_player.auth.model.Role;
import com.example.music_player.auth.record.DecodedAccessToken;
import com.example.music_player.auth.record.DecodedRefreshToken;
import com.example.music_player.model.AppProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.FORBIDDEN;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtGenerateUtil implements Serializable {

    @Serial
    private static final long serialVersionUID = 5357760619749295601L;
    private static final String ROLES_CLAIM_NAME = "roles";
    private final transient AppProperties appProperties;

    private Algorithm getAlgorithm(String secretKey) {
        return Algorithm.HMAC256(secretKey.getBytes());
    }

    public String createAccessToken(Long userId, String email, List<Role> roles) {
        Algorithm algorithm = getAlgorithm(appProperties.getJwtSecret());

        return JWT.create()
                .withSubject(email)
                .withClaim("id", userId)
                .withClaim(ROLES_CLAIM_NAME, roles.stream().map(Role::getAuthority).toList())
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + appProperties.getJwtExpiration()))
                .sign(algorithm);
    }

    public String createRefreshToken(Long id, String username) {
        Algorithm algorithm = getAlgorithm(appProperties.getJwtRefreshSecret());
        return JWT.create()
                .withSubject(username)
                .withClaim("id", id)
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + appProperties.getJwtRefreshExpiration()))
                .sign(algorithm);
    }

    public DecodedAccessToken decodeAccessToken(String token) {
        Algorithm algorithm = getAlgorithm(appProperties.getJwtSecret());
        DecodedJWT decodedJwt = JWT.require(algorithm).build().verify(token.substring("Bearer ".length()));
        var username = decodedJwt.getSubject();
        var roles = decodedJwt.getClaim(ROLES_CLAIM_NAME).asList(String.class);
        var id = decodedJwt.getClaim("id").asLong();
        return new DecodedAccessToken(id, username, Optional.ofNullable(roles).orElse(Collections.emptyList()));
    }

    public DecodedRefreshToken decodeRefreshToken(String token) {
        try {
            Algorithm algorithm = getAlgorithm(appProperties.getJwtRefreshSecret());
            var decodedJwt = JWT.require(algorithm).build().verify(token);
            var username = decodedJwt.getSubject();
            var id = decodedJwt.getClaim("id").asLong();
            var roles = decodedJwt.getClaim(ROLES_CLAIM_NAME).asList(Role.class);
            return new DecodedRefreshToken(username, id, roles);
        } catch (Exception e) {
            throw new ResponseStatusException(FORBIDDEN, "Session expired");
        }
    }

    public Long getUserId(String token) {
        return decodeAccessToken(token.substring(7)).id();
    }
}