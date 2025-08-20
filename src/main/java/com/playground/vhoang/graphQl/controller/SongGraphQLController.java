package com.playground.vhoang.graphQl.controller;


import com.playground.vhoang.graphQl.graphql.dto.CreateSongInput;
import com.playground.vhoang.graphQl.graphql.dto.UpdateSongInput;
import com.playground.vhoang.graphQl.model.Song;
import com.playground.vhoang.graphQl.repository.SongRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Controller
@Transactional
public class SongGraphQLController {

    private final SongRepository songRepository;

    public SongGraphQLController(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @QueryMapping
    public List<Song> songs() {
        return songRepository.findAll();
    }

    @QueryMapping
    public Song song(@Argument Integer id) {
        return songRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Song not found: " + id));
    }

    @MutationMapping
    public Song createSong(@Argument("createSongInput") CreateSongInput input) {
        Song s = new Song(input.getTitle());
        return songRepository.save(s);
    }

    @MutationMapping
    public Song updateSong(@Argument Integer id, @Argument("updateSongInput") UpdateSongInput input) {
        Song song = songRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Song not found: " + id));
        if (input.getTitle() != null) {
            song.setTitle(input.getTitle());
        }
        return songRepository.save(song);
    }

    @MutationMapping
    public boolean deleteSong(@Argument Integer id) {
        boolean exists = songRepository.existsById(id);
        if (exists) {
            songRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
