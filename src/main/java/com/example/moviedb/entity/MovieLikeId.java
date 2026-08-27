package com.example.moviedb.entity;

import java.io.Serializable;
import java.util.Objects;

public class MovieLikeId implements Serializable {

    private Long userId;
    private Long movieId;

    public MovieLikeId() {
    }

    public MovieLikeId(Long userId, Long movieId) {
        this.userId = userId;
        this.movieId = movieId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (!(o instanceof MovieLikeId)) {
            return false;
        }

        MovieLikeId that = (MovieLikeId) o;

        return Objects.equals(userId, that.userId)
                && Objects.equals(movieId, that.movieId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, movieId);
    }
}