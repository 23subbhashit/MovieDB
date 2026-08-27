package com.example.moviedb.dto.genre;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenreResponse {

    private Long id;
    private String name;
}