package com.playground.vhoang.graphQl.repository;

import com.playground.vhoang.graphQl.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Integer> {
}
