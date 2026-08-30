package com.example.moviedb.mapper;

import com.example.moviedb.dto.rating.RatingResponse;
import com.example.moviedb.entity.Rating;
import org.springframework.stereotype.Component;

@Component
public class RatingMapper {

    public RatingResponse toResponse(Rating rating) {

        return new RatingResponse(
                rating.getId(),
                rating.getUser().getId(),
                rating.getMovie().getId(),
                rating.getRating(),
                rating.getCreatedAt(),
                rating.getUpdatedAt()
        );
    }
}