package com.example.music_player.mapper;

import com.example.music_player.model.Music;
import com.example.music_player.payload.request.MusicRequest;
import com.example.music_player.payload.response.MusicResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface MusicMapper {

    Music toMusicEntity(MusicRequest request);
    MusicResponse toMusicResponse(Music music);
    void updateMusic(MusicRequest request , @MappingTarget Music music);
}
