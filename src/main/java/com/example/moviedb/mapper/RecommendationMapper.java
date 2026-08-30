package com.example.moviedb.mapper;

import com.example.moviedb.dto.recommendation.RecommendationResponse;
import com.example.moviedb.entity.Movie;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RecommendationMapper {

    public RecommendationResponse toResponse(Movie movie) {

        return new RecommendationResponse(
                movie.getId(),
                movie.getTitle(),
                movie.getDescription(),
                movie.getReleaseYear(),
                movie.getDurationMinutes(),
                movie.getPosterUrl(),
                movie.getAverageRating(),
                movie.getTotalRatings(),
                movie.getGenres()
                        .stream()
                        .map(genre -> genre.getName())
                        .collect(Collectors.toList())
        );
    }
}