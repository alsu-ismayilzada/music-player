package com.example.music_player.service.impl;

import com.example.music_player.mapper.MusicMapper;
import com.example.music_player.mapper.UserMapper;
import com.example.music_player.model.Music;
import com.example.music_player.payload.request.MusicRequest;
import com.example.music_player.payload.request.MusicSearchRequest;
import com.example.music_player.payload.response.MusicResponse;
import com.example.music_player.repository.MusicRepository;
import com.example.music_player.service.MusicService;
import com.example.music_player.specification.MusicSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MusicServiceImpl implements MusicService {

    private final MusicRepository musicRepository;
    private final MusicMapper musicMapper;
    private final MusicSpecification musicSpecification;
    private final UserMapper userMapper;

    @Override
    public Music findById(Long id){
        return musicRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Data not found"));
    }

    @Override
    public MusicResponse getMusicById(Long id) {
        var music = findById(id);
        return musicMapper.toMusicResponse(music);
    }

    @Override
    public Page<MusicResponse> searchMusics(MusicSearchRequest request, Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id").descending());
        var specification = musicSpecification.searchMusic(request);
        return musicRepository.findAll(specification, pageable).map(musicMapper::toMusicResponse);
    }

    @Override
    public MusicResponse createMusic(MusicRequest musicRequest) {
        var music = musicMapper.toMusicEntity(musicRequest);
        musicRepository.save(music);
        return musicMapper.toMusicResponse(music);
    }

    @Override
    public MusicResponse updateMusic(Long id, MusicRequest musicRequest) {
        var music = findById(id);
        musicMapper.updateMusic(musicRequest, music);
        return musicMapper.toMusicResponse(music);
    }

    @Override
    public void deleteMusicById(Long id) {
        var music = findById(id);
        musicRepository.delete(music);
    }
}
