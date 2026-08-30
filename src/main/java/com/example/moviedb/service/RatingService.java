package com.example.moviedb.service;

import com.example.moviedb.entity.Movie;
import com.example.moviedb.entity.Rating;
import com.example.moviedb.entity.User;
import com.example.moviedb.exception.MovieNotFoundException;
import com.example.moviedb.exception.RatingNotFoundException;
import com.example.moviedb.exception.UserNotFoundException;
import com.example.moviedb.repository.MovieRepository;
import com.example.moviedb.repository.RatingRepository;
import com.example.moviedb.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class RatingService {

    private final RatingRepository ratingRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    public RatingService(
            RatingRepository ratingRepository,
            MovieRepository movieRepository,
            UserRepository userRepository
    ) {
        this.ratingRepository = ratingRepository;
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }

    // =========================
    // CREATE RATING
    // =========================

    public Rating createRating(
            Long userId,
            Long movieId,
            BigDecimal ratingValue
    ) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found: " + userId
                        )
                );

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() ->
                        new MovieNotFoundException(movieId)
                );

        // Prevent duplicate rating
        if (ratingRepository.existsByUserIdAndMovieId(
                userId,
                movieId
        )) {

            throw new IllegalStateException(
                    "User has already rated this movie"
            );
        }

        Rating rating = new Rating();

        rating.setUser(user);
        rating.setMovie(movie);
        rating.setRating(ratingValue);

        Rating savedRating =
                ratingRepository.save(rating);

        updateMovieRating(movie);

        return savedRating;
    }

    // =========================
    // UPDATE RATING
    // =========================

    public Rating updateRating(
            Long userId,
            Long movieId,
            BigDecimal ratingValue
    ) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found: " + userId
                        )
                );

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() ->
                        new MovieNotFoundException(movieId)
                );

        Rating rating =
                ratingRepository
                        .findByUserIdAndMovieId(
                                userId,
                                movieId
                        )
                        .orElseThrow(() ->
                                new RatingNotFoundException(
                                        movieId
                                )
                        );

        rating.setRating(ratingValue);

        Rating updatedRating =
                ratingRepository.save(rating);

        updateMovieRating(movie);

        return updatedRating;
    }

    // =========================
    // DELETE RATING
    // =========================

    public void deleteRating(
            Long userId,
            Long movieId
    ) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found: " + userId
                        )
                );

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() ->
                        new MovieNotFoundException(movieId)
                );

        Rating rating =
                ratingRepository
                        .findByUserIdAndMovieId(
                                userId,
                                movieId
                        )
                        .orElseThrow(() ->
                                new RatingNotFoundException(
                                        movieId
                                )
                        );

        ratingRepository.delete(rating);

        updateMovieRating(movie);
    }

    // =========================
    // UPDATE MOVIE RATING
    // =========================

    private void updateMovieRating(Movie movie) {

        BigDecimal averageRating =
                ratingRepository
                        .findAverageRatingByMovieId(
                                movie.getId()
                        );

        long totalRatings =
                ratingRepository.countByMovieId(
                        movie.getId()
                );

        if (averageRating == null) {

            movie.setAverageRating(
                    BigDecimal.ZERO
            );

        } else {

            movie.setAverageRating(
                    averageRating
            );
        }

        movie.setTotalRatings(
                (int) totalRatings
        );

        movieRepository.save(movie);
    }
}