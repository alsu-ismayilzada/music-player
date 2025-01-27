package com.example.music_player.mapper;

import com.example.music_player.model.PlayList;
import com.example.music_player.payload.request.PlayListRequest;
import org.mapstruct.Mapper;

@Mapper
public interface PlayListMapper {

    PlayList toPlayListEntity(PlayListRequest request);
}
