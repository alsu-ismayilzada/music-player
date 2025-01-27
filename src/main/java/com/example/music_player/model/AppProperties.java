package com.example.music_player.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
@Setter
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private String jwtSecret;
    private String jwtRefreshSecret;
    private Long jwtExpiration;
    private Long jwtRefreshExpiration;
}