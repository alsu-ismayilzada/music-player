package com.example.music_player.service;

import com.example.music_player.model.Music;
import com.example.music_player.payload.request.MusicRequest;
import com.example.music_player.payload.request.MusicSearchRequest;
import com.example.music_player.payload.response.MusicResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MusicService {

    Music findById(Long id);
    MusicResponse getMusicById(Long id);
    Page<MusicResponse> searchMusics(MusicSearchRequest request, Pageable pageable);
    MusicResponse createMusic(MusicRequest musicRequest);
    MusicResponse updateMusic(Long id, MusicRequest musicRequest);
    void deleteMusicById(Long id);
}
