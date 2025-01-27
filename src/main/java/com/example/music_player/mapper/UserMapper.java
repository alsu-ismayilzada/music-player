package com.example.music_player.mapper;

import com.example.music_player.auth.payload.request.AuthRequest;
import com.example.music_player.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUserEntity(AuthRequest user);
}
