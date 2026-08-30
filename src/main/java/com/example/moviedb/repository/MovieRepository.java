package com.example.moviedb.repository;

import com.example.moviedb.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByTitleContainingIgnoreCase(String title);

    @Query("""
            SELECT DISTINCT m
            FROM Movie m
            JOIN m.genres g
            WHERE g.id IN :genreIds
            ORDER BY m.averageRating DESC
            """)
    List<Movie> findMoviesByGenreIds(
            @Param("genreIds") List<Long> genreIds
    );
}