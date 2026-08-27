package com.example.moviedb.repository;

import com.example.moviedb.entity.MovieLike;
import com.example.moviedb.entity.MovieLikeId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieLikeRepository
        extends JpaRepository<MovieLike, MovieLikeId> {

    boolean existsByUserIdAndMovieId(Long userId, Long movieId);

    void deleteByUserIdAndMovieId(Long userId, Long movieId);

    List<MovieLike> findByUserId(Long userId);
}