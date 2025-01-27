package com.example.music_player.repository;

import com.example.music_player.model.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MusicRepository extends JpaRepository<Music, Long>, JpaSpecificationExecutor<Music> {
}
