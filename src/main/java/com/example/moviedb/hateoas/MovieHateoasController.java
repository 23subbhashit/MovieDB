package com.example.moviedb.hateoas;

import com.example.moviedb.dto.movie.MovieResponse;
import com.example.moviedb.service.MovieService;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/hateoas")
public class MovieHateoasController {

    private final MovieService movieService;

    public MovieHateoasController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies/{movieId}")
    public EntityModel<MovieResponse> getMovie(
            @PathVariable Long movieId) {

        MovieResponse movie =
                movieService.getMovieById(movieId);

        return EntityModel.of(
                movie,

                linkTo(
                        methodOn(MovieHateoasController.class)
                                .getMovie(movieId)
                ).withSelfRel()
        );
    }
}