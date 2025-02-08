package com.example.music_player.mapper;

import com.example.music_player.auth.payload.request.AuthRequest;
import com.example.music_player.model.User;
import com.example.music_player.payload.response.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUserEntity(AuthRequest user);

    UserResponse toUserResponse(User user);
}
