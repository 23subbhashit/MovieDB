package com.example.moviedb.exception;

public class RatingNotFoundException
        extends RuntimeException {

    public RatingNotFoundException(Long movieId) {

        super(
                "Rating not found for movie: "
                        + movieId
        );
    }
}