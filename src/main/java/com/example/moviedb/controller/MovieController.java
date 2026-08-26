package com.example.moviedb.controller;

import com.example.moviedb.dto.movie.MovieResponse;
import com.example.moviedb.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<MovieResponse>> getAllMovies() {

        return ResponseEntity.ok(
                movieService.getAllMovies()
        );
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<MovieResponse> getMovieById(
            @PathVariable Long movieId) {

        return ResponseEntity.ok(
                movieService.getMovieById(movieId)
        );
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieResponse>> searchMovies(
            @RequestParam String title) {

        return ResponseEntity.ok(
                movieService.searchMovies(title)
        );
    }

    @GetMapping("/trending")
    public ResponseEntity<List<MovieResponse>> getTrendingMovies() {

        return ResponseEntity.ok(
                movieService.getTrendingMovies()
        );
    }

    @GetMapping("/top-rated")
    public ResponseEntity<List<MovieResponse>> getTopRatedMovies() {

        return ResponseEntity.ok(
                movieService.getTopRatedMovies()
        );
    }
}