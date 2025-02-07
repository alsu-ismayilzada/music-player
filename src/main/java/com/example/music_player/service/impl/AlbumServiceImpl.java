package com.example.music_player.service.impl;

import com.example.music_player.MusicPlayerApplication;
import com.example.music_player.mapper.AlbumMapper;
import com.example.music_player.model.Album;
import com.example.music_player.payload.request.AlbumRequest;
import com.example.music_player.payload.response.AlbumResponse;
import com.example.music_player.repository.AlbumRepository;
import com.example.music_player.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final AlbumMapper albumMapper;
    private final MusicPlayerApplication musicPlayerApplication;

    @Override
    public Album findById(Long id) {
        return albumRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Album not found"));
    }

    @Override
    public AlbumResponse findAlbumResponseById(Long id) {
        var album = findById(id);
        return albumMapper.toResponse(album);
    }

    @Override
    public Page<AlbumResponse> findAllAlbums(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id").descending());
        return albumRepository.findAll(pageable).map(albumMapper::toResponse);
    }

    @Override
    public AlbumResponse createAlbum(AlbumRequest request) {
        var album = albumMapper.toEntity(request);
        albumRepository.save(album);
        return albumMapper.toResponse(album);
    }

    @Override
    public AlbumResponse updateAlbum(Long id, AlbumRequest request) {
        var album = findById(id);
        albumMapper.updateAlbum(request, album);
        albumRepository.save(album);
        return albumMapper.toResponse(album);
    }

    @Override
    public void deleteAlbum(Long id) {
        var album = findById(id);
        albumRepository.delete(album);
    }
}
