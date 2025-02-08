package com.example.music_player.service.impl;

import com.example.music_player.mapper.PlayListMapper;
import com.example.music_player.model.PlayList;
import com.example.music_player.payload.request.PlayListRequest;
import com.example.music_player.payload.response.PlayListResponse;
import com.example.music_player.repository.PlayListRepository;
import com.example.music_player.service.PlayListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayListServiceImpl implements PlayListService {

    private final PlayListRepository playListRepository;
    private final PlayListMapper playListMapper;

    @Override
    public PlayList findById(Long id) {
        return playListRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "PlayList Not Found"));
    }

    @Override
    public PlayListResponse findPlayListResponseById(Long id) {
        var playList = findById(id);
        return playListMapper.toPlayListResponse(playList);
    }

    @Override
    public List<PlayListResponse> findAllPlayListsByUserId(Long userId) {
        return playListRepository.findByUserId(userId).stream()
                .map(playListMapper::toPlayListResponse)
                .toList();
    }

    @Override
    public PlayListResponse createPlayList(PlayListRequest request) {
        var playList = playListMapper.toPlayListEntity(request);
        playListRepository.save(playList);
        return playListMapper.toPlayListResponse(playList);
    }

    @Override
    public PlayListResponse updatePlayList(Long id, PlayListRequest request) {
        var playList = findById(id);
        playListMapper.updatePlayList(request, playList);
        playListRepository.save(playList);
        return playListMapper.toPlayListResponse(playList);
    }
}
