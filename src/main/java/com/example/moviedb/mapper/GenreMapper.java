package com.example.moviedb.mapper;

import com.example.moviedb.dto.genre.GenreMovieResponse;
import com.example.moviedb.dto.genre.GenreResponse;
import com.example.moviedb.entity.Genre;
import com.example.moviedb.entity.Movie;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper {

    public GenreResponse toResponse(Genre genre) {
        return GenreResponse.builder()
                .id(genre.getId())
                .name(genre.getName())
                .build();
    }

    public GenreMovieResponse toMovieResponse(Movie movie) {
        return GenreMovieResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .releaseYear(movie.getReleaseYear())
                .averageRating(movie.getAverageRating())
                .posterUrl(movie.getPosterUrl())
                .build();
    }
}