package com.example.moviedb.controller;

import com.example.moviedb.dto.recommendation.RecommendationResponse;
import com.example.moviedb.service.RecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/me")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(
            RecommendationService recommendationService
    ) {
        this.recommendationService = recommendationService;
    }

    // =========================
    // GET RECOMMENDATIONS
    // =========================

    @GetMapping("/recommendations")
    public ResponseEntity<List<RecommendationResponse>>
    getRecommendations(
            Authentication authentication
    ) {

        Long userId = Long.parseLong(
                authentication.getName()
        );

        return ResponseEntity.ok(
                recommendationService.getRecommendations(
                        userId
                )
        );
    }
}