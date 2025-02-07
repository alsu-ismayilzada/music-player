package com.example.music_player.repository;

import com.example.music_player.model.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface MusicRepository extends JpaRepository<Music, Long>, JpaSpecificationExecutor<Music> {

    Optional<Music> findByPath(String path);
}
