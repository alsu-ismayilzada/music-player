package com.example.music_player.mapper;

import com.example.music_player.model.PlayList;
import com.example.music_player.payload.request.PlayListRequest;
import com.example.music_player.payload.response.PlayListResponse;
import com.example.music_player.service.MusicService;
import com.example.music_player.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = {MusicService.class, UserService.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PlayListMapper {

//    @Mapping(target = "user", ignore = true)
    PlayList toPlayListEntity(PlayListRequest request);

    PlayListResponse toPlayListResponse(PlayList response);

//    @Mapping(target = "user", ignore = true)
    void updatePlayList(PlayListRequest request, @MappingTarget PlayList playList);
}
