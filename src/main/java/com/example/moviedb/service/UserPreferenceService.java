package com.example.moviedb.service;

import com.example.moviedb.dto.user.UserMovieResponse;
import com.example.moviedb.entity.MovieDislike;
import com.example.moviedb.entity.MovieLike;
import com.example.moviedb.exception.MovieNotFoundException;
import com.example.moviedb.mapper.UserMovieMapper;
import com.example.moviedb.repository.MovieDislikeRepository;
import com.example.moviedb.repository.MovieLikeRepository;
import com.example.moviedb.repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserPreferenceService {

    private final MovieLikeRepository movieLikeRepository;
    private final MovieDislikeRepository movieDislikeRepository;
    private final MovieRepository movieRepository;
    private final UserMovieMapper userMovieMapper;

    public UserPreferenceService(
            MovieLikeRepository movieLikeRepository,
            MovieDislikeRepository movieDislikeRepository,
            MovieRepository movieRepository,
            UserMovieMapper userMovieMapper
    ) {
        this.movieLikeRepository = movieLikeRepository;
        this.movieDislikeRepository = movieDislikeRepository;
        this.movieRepository = movieRepository;
        this.userMovieMapper = userMovieMapper;
    }

    // =========================
    // LIKE MOVIE
    // =========================

    public void likeMovie(Long userId, Long movieId) {

        movieRepository.findById(movieId)
                .orElseThrow(() ->
                        new MovieNotFoundException(movieId));

        // If the user disliked the movie,
        // remove the dislike first.
        movieDislikeRepository.deleteByUserIdAndMovieId(
                userId,
                movieId
        );

        // Prevent duplicate likes.
        if (!movieLikeRepository.existsByUserIdAndMovieId(
                userId,
                movieId
        )) {

            MovieLike movieLike =
                    new MovieLike(userId, movieId);

            movieLikeRepository.save(movieLike);
        }
    }

    // =========================
    // REMOVE LIKE
    // =========================

    public void removeLike(Long userId, Long movieId) {

        movieLikeRepository.deleteByUserIdAndMovieId(
                userId,
                movieId
        );
    }

    // =========================
    // DISLIKE MOVIE
    // =========================

    public void dislikeMovie(Long userId, Long movieId) {

        movieRepository.findById(movieId)
                .orElseThrow(() ->
                        new MovieNotFoundException(movieId));

        // If the user liked the movie,
        // remove the like first.
        movieLikeRepository.deleteByUserIdAndMovieId(
                userId,
                movieId
        );

        // Prevent duplicate dislikes.
        if (!movieDislikeRepository.existsByUserIdAndMovieId(
                userId,
                movieId
        )) {

            MovieDislike movieDislike =
                    new MovieDislike(userId, movieId);

            movieDislikeRepository.save(movieDislike);
        }
    }

    // =========================
    // REMOVE DISLIKE
    // =========================

    public void removeDislike(Long userId, Long movieId) {

        movieDislikeRepository.deleteByUserIdAndMovieId(
                userId,
                movieId
        );
    }

    // =========================
    // GET LIKED MOVIES
    // =========================

    @Transactional(readOnly = true)
    public List<UserMovieResponse> getLikedMovies(Long userId) {

        return movieLikeRepository.findByUserId(userId)
                .stream()
                .map(like -> movieRepository.findById(like.getMovieId())
                        .orElseThrow(() ->
                                new MovieNotFoundException(
                                        like.getMovieId()
                                )))
                .map(userMovieMapper::toResponse)
                .toList();
    }

    // =========================
    // GET DISLIKED MOVIES
    // =========================

    @Transactional(readOnly = true)
    public List<UserMovieResponse> getDislikedMovies(Long userId) {

        return movieDislikeRepository.findByUserId(userId)
                .stream()
                .map(dislike -> movieRepository.findById(dislike.getMovieId())
                        .orElseThrow(() ->
                                new MovieNotFoundException(
                                        dislike.getMovieId()
                                )))
                .map(userMovieMapper::toResponse)
                .toList();
    }
}