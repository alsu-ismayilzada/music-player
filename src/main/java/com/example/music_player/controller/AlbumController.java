package com.example.music_player.controller;

import com.example.music_player.payload.request.AlbumRequest;
import com.example.music_player.payload.response.AlbumResponse;
import com.example.music_player.service.AlbumService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("${app.api_base_url}/albums")
@RequiredArgsConstructor
public class AlbumController {

    private final AlbumService albumService;

//    @Operation(summary = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully founded"),
            @ApiResponse(responseCode = "404", description = "Not founded")
    })
    @GetMapping("/{albumId}")
    public ResponseEntity<AlbumResponse> findAlbumResponse(@PathVariable Long albumId) {
        return new ResponseEntity<>(albumService.findAlbumResponseById(albumId), OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully founded"),
            @ApiResponse(responseCode = "404", description = "Not founded")
    })
    @GetMapping()
    public Page<AlbumResponse> findAllAlbums(Pageable pageable) {
        return albumService.findAllAlbums(pageable);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created"),
            @ApiResponse(responseCode = "404", description = "Not created")
    })
    @PostMapping()
    public ResponseEntity<AlbumResponse> createAlbum(@RequestBody AlbumRequest request) {
        return new ResponseEntity<>(albumService.createAlbum(request), CREATED);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated"),
            @ApiResponse(responseCode = "404", description = "Not updated")
    })
    @PutMapping("/{albumId}")
    public ResponseEntity<AlbumResponse> updateAlbum(@PathVariable Long albumId, @RequestBody AlbumRequest request) {
        return new ResponseEntity<>(albumService.updateAlbum(albumId, request), OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully updated"),
            @ApiResponse(responseCode = "404", description = "Not updated")
    })
    @DeleteMapping("/{albumId}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Long albumId) {
        albumService.deleteAlbum(albumId);
        return ResponseEntity.noContent().build();
    }


}
