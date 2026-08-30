package com.example.moviedb.service;

import com.example.moviedb.dto.recommendation.RecommendationResponse;
import com.example.moviedb.entity.Movie;
import com.example.moviedb.entity.MovieDislike;
import com.example.moviedb.entity.MovieLike;
import com.example.moviedb.entity.Rating;
import com.example.moviedb.mapper.RecommendationMapper;
import com.example.moviedb.repository.MovieDislikeRepository;
import com.example.moviedb.repository.MovieLikeRepository;
import com.example.moviedb.repository.MovieRepository;
import com.example.moviedb.repository.RatingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class RecommendationService {

    private final MovieLikeRepository movieLikeRepository;
    private final MovieDislikeRepository movieDislikeRepository;
    private final RatingRepository ratingRepository;
    private final MovieRepository movieRepository;
    private final RecommendationMapper recommendationMapper;

    public RecommendationService(
            MovieLikeRepository movieLikeRepository,
            MovieDislikeRepository movieDislikeRepository,
            RatingRepository ratingRepository,
            MovieRepository movieRepository,
            RecommendationMapper recommendationMapper
    ) {
        this.movieLikeRepository = movieLikeRepository;
        this.movieDislikeRepository = movieDislikeRepository;
        this.ratingRepository = ratingRepository;
        this.movieRepository = movieRepository;
        this.recommendationMapper = recommendationMapper;
    }

    public List<RecommendationResponse> getRecommendations(
            Long userId
    ) {

        // =========================
        // 1. GET USER LIKES
        // =========================

        List<MovieLike> likes =
                movieLikeRepository.findByUserId(userId);

        if (likes.isEmpty()) {
            return List.of();
        }

        // =========================
        // 2. GET LIKED MOVIE IDS
        // =========================

        Set<Long> likedMovieIds =
                likes.stream()
                        .map(MovieLike::getMovieId)
                        .collect(Collectors.toSet());

        // =========================
        // 3. GET GENRES FROM
        //    LIKED MOVIES
        // =========================

        Set<Long> genreIds =
                likedMovieIds.stream()
                        .map(movieRepository::findById)
                        .filter(java.util.Optional::isPresent)
                        .flatMap(movie ->
                                movie.get().getGenres().stream()
                        )
                        .map(genre -> genre.getId())
                        .collect(Collectors.toSet());

        if (genreIds.isEmpty()) {
            return List.of();
        }

        // =========================
        // 4. FIND MOVIES WITH
        //    SIMILAR GENRES
        // =========================

        List<Movie> candidateMovies =
                movieRepository.findMoviesByGenreIds(
                        genreIds.stream().toList()
                );

        // =========================
        // 5. GET DISLIKED MOVIES
        // =========================

        Set<Long> dislikedMovieIds =
                movieDislikeRepository
                        .findByUserId(userId)
                        .stream()
                        .map(MovieDislike::getMovieId)
                        .collect(Collectors.toSet());

        // =========================
        // 6. GET RATED MOVIES
        // =========================

        Set<Long> ratedMovieIds =
                ratingRepository
                        .findByUserId(userId)
                        .stream()
                        .map(rating -> rating.getMovie().getId())
                        .collect(Collectors.toSet());

        // =========================
        // 7. EXCLUDE
        //
        //    liked
        //    disliked
        //    rated
        // =========================

        return candidateMovies.stream()

                .filter(movie ->
                        !likedMovieIds.contains(
                                movie.getId()
                        )
                )

                .filter(movie ->
                        !dislikedMovieIds.contains(
                                movie.getId()
                        )
                )

                .filter(movie ->
                        !ratedMovieIds.contains(
                                movie.getId()
                        )
                )

                // =========================
                // 8. SORT BY RATING
                // =========================

                .sorted(
                        java.util.Comparator
                                .comparing(
                                        Movie::getAverageRating,
                                        java.util.Comparator
                                                .nullsLast(
                                                        java.util.Comparator
                                                                .reverseOrder()
                                                )
                                )
                )

                // =========================
                // 9. TOP 10
                // =========================

                .limit(10)

                // =========================
                // 10. MAP TO DTO
                // =========================

                .map(recommendationMapper::toResponse)

                .toList();
    }
}