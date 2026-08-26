package com.example.moviedb.service;

import com.example.moviedb.dto.movie.MovieResponse;
import com.example.moviedb.entity.Movie;
import com.example.moviedb.exception.MovieNotFoundException;
import com.example.moviedb.mapper.MovieMapper;
import com.example.moviedb.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public MovieService(
            MovieRepository movieRepository,
            MovieMapper movieMapper) {

        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }

    public List<MovieResponse> getAllMovies() {

        return movieRepository.findAll()
                .stream()
                .map(movieMapper::toResponse)
                .toList();
    }

    public MovieResponse getMovieById(Long movieId) {

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException(movieId));

        return movieMapper.toResponse(movie);
    }

    public List<MovieResponse> searchMovies(String title) {

        return movieRepository
                .findByTitleContainingIgnoreCase(title)
                .stream()
                .map(movieMapper::toResponse)
                .toList();
    }

    public List<MovieResponse> getTrendingMovies() {

        // Temporary implementation.
        // We will later calculate trending movies
        // using likes/ratings.

        return movieRepository.findAll()
                .stream()
                .sorted((m1, m2) ->
                        m2.getAverageRating()
                                .compareTo(m1.getAverageRating()))
                .map(movieMapper::toResponse)
                .toList();
    }

    public List<MovieResponse> getTopRatedMovies() {

        return movieRepository.findAll()
                .stream()
                .sorted((m1, m2) ->
                        m2.getAverageRating()
                                .compareTo(m1.getAverageRating()))
                .limit(10)
                .map(movieMapper::toResponse)
                .toList();
    }
}