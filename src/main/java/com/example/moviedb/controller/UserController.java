package com.example.moviedb.controller;

import com.example.moviedb.dto.user.UserMovieResponse;
import com.example.moviedb.service.UserPreferenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserPreferenceService userPreferenceService;

    public UserController(
            UserPreferenceService userPreferenceService
    ) {
        this.userPreferenceService = userPreferenceService;
    }

    // =========================
    // LIKE
    // =========================

    @PostMapping("/movies/{movieId}/like")
    public ResponseEntity<Void> likeMovie(
            @PathVariable Long movieId,
            Authentication authentication
    ) {

        Long userId = Long.parseLong(
                authentication.getName()
        );

        userPreferenceService.likeMovie(
                userId,
                movieId
        );

        return ResponseEntity.ok().build();
    }

    // =========================
    // REMOVE LIKE
    // =========================

    @DeleteMapping("/movies/{movieId}/like")
    public ResponseEntity<Void> removeLike(
            @PathVariable Long movieId,
            Authentication authentication
    ) {

        Long userId = Long.parseLong(
                authentication.getName()
        );

        userPreferenceService.removeLike(
                userId,
                movieId
        );

        return ResponseEntity.noContent().build();
    }

    // =========================
    // DISLIKE
    // =========================

    @PostMapping("/movies/{movieId}/dislike")
    public ResponseEntity<Void> dislikeMovie(
            @PathVariable Long movieId,
            Authentication authentication
    ) {

        Long userId = Long.parseLong(
                authentication.getName()
        );

        userPreferenceService.dislikeMovie(
                userId,
                movieId
        );

        return ResponseEntity.ok().build();
    }

    // =========================
    // REMOVE DISLIKE
    // =========================

    @DeleteMapping("/movies/{movieId}/dislike")
    public ResponseEntity<Void> removeDislike(
            @PathVariable Long movieId,
            Authentication authentication
    ) {

        Long userId = Long.parseLong(
                authentication.getName()
        );

        userPreferenceService.removeDislike(
                userId,
                movieId
        );

        return ResponseEntity.noContent().build();
    }

    // =========================
    // GET LIKES
    // =========================

    @GetMapping("/users/me/likes")
    public ResponseEntity<List<UserMovieResponse>> getLikedMovies(
            Authentication authentication
    ) {

        Long userId = Long.parseLong(
                authentication.getName()
        );

        return ResponseEntity.ok(
                userPreferenceService.getLikedMovies(userId)
        );
    }

    // =========================
    // GET DISLIKES
    // =========================

    @GetMapping("/users/me/dislikes")
    public ResponseEntity<List<UserMovieResponse>> getDislikedMovies(
            Authentication authentication
    ) {

        Long userId = Long.parseLong(
                authentication.getName()
        );

        return ResponseEntity.ok(
                userPreferenceService.getDislikedMovies(userId)
        );
    }
}