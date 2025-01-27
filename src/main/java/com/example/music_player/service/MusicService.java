package com.example.music_player.service;

import com.example.music_player.payload.request.MusicRequest;
import com.example.music_player.payload.response.MusicResponse;

import java.util.List;

public interface MusicService {

    MusicResponse getMusicById(Long id);
    List<MusicResponse> getAllMusicByPlaylistId(Long playlistId);
    MusicResponse createMusic(MusicRequest musicRequest);
    MusicResponse updateMusic(MusicRequest musicRequest);
    void deleteMusicById(Long id);
}
