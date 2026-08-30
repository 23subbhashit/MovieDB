package com.example.moviedb.dto.rating;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class RatingResponse {

    private Long id;

    private Long userId;

    private Long movieId;

    private BigDecimal rating;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}