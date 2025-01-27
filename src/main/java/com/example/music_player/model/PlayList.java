package com.example.music_player.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "play_lists")
public class PlayList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String title;

    @ManyToMany
    @JoinTable(name = "play_list_musics",
            joinColumns = @JoinColumn(name = "play_list_id"),
            inverseJoinColumns = @JoinColumn(name = "music_id")
    )
    List<Music> musics;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    User user;

}
