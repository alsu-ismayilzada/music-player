package com.example.music_player.service.impl;

import com.example.music_player.mapper.MusicMapper;
import com.example.music_player.model.Music;
import com.example.music_player.payload.request.MusicRequest;
import com.example.music_player.payload.response.MusicResponse;
import com.example.music_player.repository.MusicRepository;
import com.example.music_player.service.MusicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MusicServiceImpl implements MusicService {

    private final MusicRepository musicRepository;
    private final MusicMapper musicMapper;

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
    public List<MusicResponse> getAllMusicByPlaylistId(Long playlistId) {
        return List.of();
    }

    @Override
    public MusicResponse createMusic(MusicRequest musicRequest) {
        var music = musicMapper.toMusicEntity(musicRequest);

        return null;
    }

    @Override
    public MusicResponse updateMusic(MusicRequest musicRequest) {
        return null;
    }

    @Override
    public void deleteMusicById(Long id) {
        var music = findById(id);
        musicRepository.delete(music);
    }
}
