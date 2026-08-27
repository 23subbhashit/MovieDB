package com.example.moviedb.mapper;

import com.example.moviedb.dto.user.UserMovieResponse;
import com.example.moviedb.entity.Movie;
import org.springframework.stereotype.Component;

@Component
public class UserMovieMapper {

    public UserMovieResponse toResponse(Movie movie) {

        return new UserMovieResponse(
                movie.getId(),
                movie.getTitle(),
                movie.getReleaseYear(),
                movie.getAverageRating(),
                movie.getPosterUrl()
        );
    }
}