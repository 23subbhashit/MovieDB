package com.example.moviedb.controller;

import com.example.moviedb.dto.rating.RatingRequest;
import com.example.moviedb.dto.rating.RatingResponse;
import com.example.moviedb.entity.Rating;
import com.example.moviedb.mapper.RatingMapper;
import com.example.moviedb.service.RatingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/movies")
public class RatingController {

    private final RatingService ratingService;
    private final RatingMapper ratingMapper;

    public RatingController(
            RatingService ratingService,
            RatingMapper ratingMapper
    ) {
        this.ratingService = ratingService;
        this.ratingMapper = ratingMapper;
    }

    // =========================
    // CREATE RATING
    // =========================

    @PostMapping("/{movieId}/rating")
    public ResponseEntity<RatingResponse> createRating(
            @PathVariable Long movieId,
            @Valid @RequestBody RatingRequest request,
            Authentication authentication
    ) {

        Long userId = Long.parseLong(
                authentication.getName()
        );

        Rating rating =
                ratingService.createRating(
                        userId,
                        movieId,
                        request.getRating()
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ratingMapper.toResponse(rating)
                );
    }

    // =========================
    // UPDATE RATING
    // =========================

    @PutMapping("/{movieId}/rating")
    public ResponseEntity<RatingResponse> updateRating(
            @PathVariable Long movieId,
            @Valid @RequestBody RatingRequest request,
            Authentication authentication
    ) {

        Long userId = Long.parseLong(
                authentication.getName()
        );

        Rating rating =
                ratingService.updateRating(
                        userId,
                        movieId,
                        request.getRating()
                );

        return ResponseEntity.ok(
                ratingMapper.toResponse(rating)
        );
    }

    // =========================
    // DELETE RATING
    // =========================

    @DeleteMapping("/{movieId}/rating")
    public ResponseEntity<Void> deleteRating(
            @PathVariable Long movieId,
            Authentication authentication
    ) {

        Long userId = Long.parseLong(
                authentication.getName()
        );

        ratingService.deleteRating(
                userId,
                movieId
        );

        return ResponseEntity.noContent().build();
    }
}