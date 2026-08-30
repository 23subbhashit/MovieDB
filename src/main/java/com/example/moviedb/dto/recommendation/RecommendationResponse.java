package com.example.moviedb.dto.recommendation;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
public class RecommendationResponse {

    private Long id;

    private String title;

    private String description;

    private Integer releaseYear;

    private Integer durationMinutes;

    private String posterUrl;

    private BigDecimal averageRating;

    private Integer totalRatings;

    private List<String> genres;
}