package com.example.moviedb.mapper;

import com.example.moviedb.dto.movie.MovieResponse;
import com.example.moviedb.dto.movie.MovieSummaryResponse;
import com.example.moviedb.entity.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {

    public MovieResponse toResponse(Movie movie) {

        return new MovieResponse(
                movie.getId(),
                movie.getTitle(),
                movie.getDescription(),
                movie.getReleaseYear(),
                movie.getDurationMinutes(),
                movie.getPosterUrl(),
                movie.getAverageRating(),
                movie.getTotalRatings()
        );
    }

    public MovieSummaryResponse toSummaryResponse(Movie movie) {

        return new MovieSummaryResponse(
                movie.getId(),
                movie.getTitle(),
                movie.getReleaseYear(),
                movie.getPosterUrl(),
                movie.getAverageRating()
        );
    }
}