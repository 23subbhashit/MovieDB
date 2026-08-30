package com.example.moviedb.repository;

import com.example.moviedb.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface RatingRepository
        extends JpaRepository<Rating, Long> {

    Optional<Rating> findByUserIdAndMovieId(
            Long userId,
            Long movieId
    );

    boolean existsByUserIdAndMovieId(
            Long userId,
            Long movieId
    );

    long countByMovieId(Long movieId);

    @Query("""
            SELECT AVG(r.rating)
            FROM Rating r
            WHERE r.movie.id = :movieId
            """)
    BigDecimal findAverageRatingByMovieId(Long movieId);

    List<Rating> findByUserId(Long userId);
}