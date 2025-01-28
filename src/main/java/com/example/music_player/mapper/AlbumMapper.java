package com.example.music_player.mapper;


import com.example.music_player.model.Album;
import com.example.music_player.payload.request.AlbumRequest;
import com.example.music_player.payload.response.AlbumResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AlbumMapper {

    Album toEntity(AlbumRequest request);

    AlbumResponse toResponse(Album album);

    void updateAlbum(AlbumRequest request,@MappingTarget Album album);
}
