package com.example.moviedb.dto.movie;

import java.math.BigDecimal;

public class MovieResponse {

    private Long id;
    private String title;
    private String description;
    private Integer releaseYear;
    private Integer durationMinutes;
    private String posterUrl;
    private BigDecimal averageRating;
    private Integer totalRatings;

    public MovieResponse() {
    }

    public MovieResponse(
            Long id,
            String title,
            String description,
            Integer releaseYear,
            Integer durationMinutes,
            String posterUrl,
            BigDecimal averageRating,
            Integer totalRatings) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.durationMinutes = durationMinutes;
        this.posterUrl = posterUrl;
        this.averageRating = averageRating;
        this.totalRatings = totalRatings;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public BigDecimal getAverageRating() {
        return averageRating;
    }

    public Integer getTotalRatings() {
        return totalRatings;
    }
}