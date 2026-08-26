package com.example.moviedb.dto.movie;

import java.math.BigDecimal;

public class MovieSummaryResponse {

    private Long id;
    private String title;
    private Integer releaseYear;
    private String posterUrl;
    private BigDecimal averageRating;

    public MovieSummaryResponse(
            Long id,
            String title,
            Integer releaseYear,
            String posterUrl,
            BigDecimal averageRating) {

        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
        this.posterUrl = posterUrl;
        this.averageRating = averageRating;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public BigDecimal getAverageRating() {
        return averageRating;
    }
}