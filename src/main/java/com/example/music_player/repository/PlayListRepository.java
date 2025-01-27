package com.example.music_player.repository;

import com.example.music_player.model.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayListRepository extends JpaRepository<PlayList, Long> {

    List<PlayList> findByUserId(Long userId);
}
