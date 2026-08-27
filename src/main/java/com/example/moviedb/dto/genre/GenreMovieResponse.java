package com.example.moviedb.dto.genre;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenreMovieResponse {

    private Long id;
    private String title;
    private Integer releaseYear;
    private BigDecimal averageRating;
    private String posterUrl;
}