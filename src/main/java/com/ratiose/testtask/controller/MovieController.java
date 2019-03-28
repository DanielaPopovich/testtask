package com.ratiose.testtask.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import com.ratiose.testtask.entity.User;
import com.ratiose.testtask.exception.RequestException;
import com.ratiose.testtask.service.UserMoviesService;
import com.ratiose.testtask.service.UserService;
import com.ratiose.testtask.service.tmdb.TmdbApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMoviesService userMoviesService;

    @Autowired
    private TmdbApi tmdbApi;

    @RequestMapping(value = "/popular", method = POST)
    public ResponseEntity popular(@RequestParam String email,
                                  @RequestParam String password) {
        try {
            userService.findUser(email, password);
            String popularMovies = tmdbApi.popularMovies();
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(popularMovies);
        } catch (RequestException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
        }
    }

    @RequestMapping(value = "/watched", method = POST)
    public ResponseEntity markMovieAsWatched(@RequestParam String email,
                                             @RequestParam String password,
                                             @RequestParam long movieId) {
        try {
            User user = userService.findUser(email, password);
            long watchedMovieId = userMoviesService.saveWatchedMovie(user.getId(), movieId);
            return ResponseEntity.status(HttpStatus.OK).body(watchedMovieId);
        } catch (RequestException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
        }
    }

}
