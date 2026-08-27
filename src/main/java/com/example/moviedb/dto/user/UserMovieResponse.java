package com.example.moviedb.dto.user;

import java.math.BigDecimal;

public class UserMovieResponse {

    private Long id;
    private String title;
    private Integer releaseYear;
    private BigDecimal averageRating;
    private String posterUrl;

    public UserMovieResponse() {
    }

    public UserMovieResponse(
            Long id,
            String title,
            Integer releaseYear,
            BigDecimal averageRating,
            String posterUrl
    ) {
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
        this.averageRating = averageRating;
        this.posterUrl = posterUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public BigDecimal getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(BigDecimal averageRating) {
        this.averageRating = averageRating;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }
}