package com.example.music_player.service;

import com.example.music_player.model.Album;
import com.example.music_player.payload.request.AlbumRequest;
import com.example.music_player.payload.response.AlbumResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AlbumService {

    Album findById(Long id);
    AlbumResponse findAlbumResponseById(Long id);
    Page<AlbumResponse> findAllAlbums(Pageable pageable);
    AlbumResponse createAlbum(AlbumRequest request);
    AlbumResponse updateAlbum(Long id, AlbumRequest request);
    void deleteAlbum(Long id);
}
