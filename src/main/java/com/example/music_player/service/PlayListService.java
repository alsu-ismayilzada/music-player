package com.example.music_player.service;

import com.example.music_player.model.PlayList;
import com.example.music_player.payload.request.PlayListRequest;
import com.example.music_player.payload.response.PlayListResponse;

import java.util.List;

public interface PlayListService {

    PlayList findById(Long id);
    PlayListResponse findPlayListResponseById(Long id);
    List<PlayListResponse> findAllPlayListsByUserId(Long userId);
    PlayListResponse createPlayList(PlayListRequest request);
    PlayListResponse updatePlayList(Long id, PlayListRequest request);
}
