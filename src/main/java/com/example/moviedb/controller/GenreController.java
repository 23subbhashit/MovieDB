package com.example.moviedb.controller;

import com.example.moviedb.dto.genre.GenreMovieResponse;
import com.example.moviedb.dto.genre.GenreResponse;
import com.example.moviedb.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    public List<GenreResponse> getAllGenres() {
        return genreService.getAllGenres();
    }

    @GetMapping("/{genreId}/movies")
    public List<GenreMovieResponse> getMoviesByGenre(
            @PathVariable Long genreId
    ) {
        return genreService.getMoviesByGenre(genreId);
    }
}