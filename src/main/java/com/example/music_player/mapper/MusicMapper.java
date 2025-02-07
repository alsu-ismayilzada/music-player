package com.example.music_player.mapper;

import com.example.music_player.model.Music;
import com.example.music_player.payload.request.MusicRequest;
import com.example.music_player.payload.response.MusicResponse;
import com.example.music_player.service.AlbumService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {AlbumService.class})
public interface MusicMapper {

//    @Mapping(target = "file", ignore = true)
    Music toMusicEntity(MusicRequest request);

    @Mapping(target = "file", ignore = true)
    MusicResponse toMusicResponse(Music music);

//    @Mapping(target = "file", ignore = true)
    void updateMusic(MusicRequest request , @MappingTarget Music music);
}
