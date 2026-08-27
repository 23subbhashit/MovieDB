package com.example.moviedb.entity;

import java.io.Serializable;
import java.util.Objects;

public class MovieDislikeId implements Serializable {

    private Long userId;
    private Long movieId;

    public MovieDislikeId() {
    }

    public MovieDislikeId(Long userId, Long movieId) {
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

        if (!(o instanceof MovieDislikeId)) {
            return false;
        }

        MovieDislikeId that = (MovieDislikeId) o;

        return Objects.equals(userId, that.userId)
                && Objects.equals(movieId, that.movieId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, movieId);
    }
}