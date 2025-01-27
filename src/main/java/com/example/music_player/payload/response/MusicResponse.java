package com.example.music_player.payload.response;

import com.example.music_player.model.Album;
import lombok.Data;

@Data
public class MusicResponse {

   private Long id;
   private String title;
   private String artist;
   private Album album;
   private String genre;
   private String path;
   private byte[] poster;
   private int duration;
}
