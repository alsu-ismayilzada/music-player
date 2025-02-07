package com.example.music_player.controller;

import com.example.music_player.payload.request.MusicRequest;
import com.example.music_player.payload.request.MusicSearchRequest;
import com.example.music_player.payload.response.MusicResponse;
import com.example.music_player.service.MusicService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("${app.api_base_url}/musics")
@RequiredArgsConstructor
public class MusicController {

    private final MusicService musicService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{musicId}")
    public ResponseEntity<MusicResponse> findMusicById(@PathVariable Long musicId) {
        return new ResponseEntity<>(musicService.getMusicById(musicId), OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping()
    public Page<MusicResponse> searchMusics(MusicSearchRequest request, Pageable pageable) {
        return musicService.searchMusics(request, pageable);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created"),
            @ApiResponse(responseCode = "404", description = "Not created")
    })
    @PostMapping(consumes =  "multipart/form-data")
    public ResponseEntity<MusicResponse> createMusic(@RequestParam("file") MultipartFile file, MusicRequest musicRequest) {
        musicRequest.setFile(file);
        return new ResponseEntity<>(musicService.createMusic(musicRequest), CREATED);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully downloaded"),
            @ApiResponse(responseCode = "404", description = "Not downloaded")
    })
    @GetMapping(value = "/download")
    public MusicResponse downloadMusic(@RequestParam("path") String path){
        return musicService.downloadMusic(path);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated"),
            @ApiResponse(responseCode = "404", description = "Not updated")
    })
    @PutMapping("/{musicId}")
    public ResponseEntity<MusicResponse> updateMusic(@PathVariable Long musicId, @RequestBody MusicRequest musicRequest) {
        return new ResponseEntity<>(musicService.updateMusic(musicId, musicRequest), OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Not deleted")
    })
    @DeleteMapping("/{musicId}")
    public ResponseEntity<Void> deleteMusicById(@PathVariable Long musicId) {
        musicService.deleteMusicById(musicId);
        return ResponseEntity.noContent().build();
    }
}
