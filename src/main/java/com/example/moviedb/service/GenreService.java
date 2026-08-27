package com.example.moviedb.service;

import com.example.moviedb.dto.genre.GenreMovieResponse;
import com.example.moviedb.dto.genre.GenreResponse;
import com.example.moviedb.entity.Genre;
import com.example.moviedb.exception.GenreNotFoundException;
import com.example.moviedb.mapper.GenreMapper;
import com.example.moviedb.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    public List<GenreResponse> getAllGenres() {

        return genreRepository.findAll()
                .stream()
                .map(genreMapper::toResponse)
                .toList();
    }

    public List<GenreMovieResponse> getMoviesByGenre(Long genreId) {

        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new GenreNotFoundException(genreId));

        return genre.getMovies()
                .stream()
                .map(genreMapper::toMovieResponse)
                .toList();
    }
}