package com.example.music_player.specification;

import com.example.music_player.model.Album;
import com.example.music_player.model.Music;
import com.example.music_player.payload.request.MusicSearchRequest;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MusicSpecification {

    public Specification<Music> searchMusic(MusicSearchRequest request) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();
            Join<Music, Album> albumJoin = root.join("album", JoinType.INNER);

            if (StringUtils.isNotBlank(request.getTitle())) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("title")),
                        "%" + request.getTitle().toLowerCase() + "%"
                ));
            }

            if (StringUtils.isNotBlank(request.getArtist())) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("artist")),
                        "%" + request.getArtist().toLowerCase() + "%"
                ));
            }

            if (StringUtils.isNotBlank(request.getAlbumTitle())) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(albumJoin.get("title")),
                        "%" + request.getAlbumTitle().toLowerCase() + "%"
                ));
            }

            if (StringUtils.isNotBlank(request.getGenre())) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("genre")),
                        "%" + request.getGenre().toLowerCase() + "%"
                ));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
