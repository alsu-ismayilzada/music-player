package com.example.music_player.controller;

import com.example.music_player.payload.request.PlayListRequest;
import com.example.music_player.payload.response.PlayListResponse;
import com.example.music_player.service.PlayListService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("${app.api_base_url}/playlists")
@RequiredArgsConstructor
public class PlayListController {

    private final PlayListService playListService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{playListId}")
    public ResponseEntity<PlayListResponse> findPlayListById(@PathVariable Long playListId) {
        return new ResponseEntity<>(playListService.findPlayListResponseById(playListId), OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PlayListResponse>> findAllPlayListsByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(playListService.findAllPlayListsByUserId(userId), OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created"),
            @ApiResponse(responseCode = "404", description = "Not created")
    })
    @PostMapping()
    public ResponseEntity<PlayListResponse> createPlayList(@RequestBody PlayListRequest request) {
        return new ResponseEntity<>(playListService.createPlayList(request), CREATED);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated"),
            @ApiResponse(responseCode = "404", description = "Not updated")
    })
    @PutMapping("/{playListId}")
    public ResponseEntity<PlayListResponse> updatePlayList(@PathVariable Long playListId, @RequestBody PlayListRequest request) {
        return new ResponseEntity<>(playListService.updatePlayList(playListId, request), OK);
    }
}
