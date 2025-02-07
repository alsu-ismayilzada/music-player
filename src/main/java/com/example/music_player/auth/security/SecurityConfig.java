package com.example.music_player.auth.security;

import com.example.music_player.auth.filter.AuthorizationFilter;
import com.example.music_player.auth.filter.CustomAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@CrossOrigin
@EnableMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class SecurityConfig {

    private final CustomAuthenticationProvider authProvider;
    private final AuthorizationFilter authorizationFilter;

//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.authenticationProvider(authProvider)
                .getSharedObject(AuthenticationManagerBuilder.class).build();
    }

    private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "v2/api-docs",
            "/swagger-resources",
            "swagger-resources",
            "/swagger-resources/**",
            "swagger-resources/**",
            "/configuration/ui",
            "configuration/ui",
            "/configuration/security",
            "configuration/security",
            "/swagger-ui.html",
            "swagger-ui.html",
            "webjars/**",
            "/api/**",
            "/public/**",
            "*/public/**",
            // -- Swagger UI v3
            "/v3/api-docs/**",
            "v3/api-docs/**",
            "*/swagger-ui/**",
            "swagger-ui/**",
            "/albums/**",
            "/musics/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(authorize -> authorize.requestMatchers(AUTH_WHITELIST).permitAll());
        http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/**").permitAll());
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(Customizer.withDefaults());
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authenticationProvider(authProvider);
        http.addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList(ALLOWED_ORIGINS));
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "PATCH", "DELETE"));
        configuration.setAllowCredentials(true);
        configuration.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
