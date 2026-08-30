package com.example.moviedb.dto.rating;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RatingRequest {

    @NotNull(message = "Rating is required")
    @DecimalMin(
            value = "0.0",
            message = "Rating must be at least 0"
    )
    @DecimalMax(
            value = "5.0",
            message = "Rating must not exceed 5"
    )
    private BigDecimal rating;
}