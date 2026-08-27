package com.example.moviedb.repository;

import com.example.moviedb.entity.MovieDislike;
import com.example.moviedb.entity.MovieDislikeId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieDislikeRepository
        extends JpaRepository<MovieDislike, MovieDislikeId> {

    boolean existsByUserIdAndMovieId(Long userId, Long movieId);

    void deleteByUserIdAndMovieId(Long userId, Long movieId);

    List<MovieDislike> findByUserId(Long userId);
}