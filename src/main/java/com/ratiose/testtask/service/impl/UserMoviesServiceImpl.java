package com.ratiose.testtask.service.impl;

import com.ratiose.testtask.entity.UserMovies;
import com.ratiose.testtask.repository.UserMoviesRepository;
import com.ratiose.testtask.service.UserMoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;

@Service
public class UserMoviesServiceImpl implements UserMoviesService {

    @Autowired
    private UserMoviesRepository userMoviesRepository;

    @Override
    public long saveWatchedMovie(long userId, long movieId) {
        UserMovies userMovies = userMoviesRepository.findOne(userId);
        if (userMovies == null) {
            userMovies = createUserMovies(userId, movieId);
        } else {
            userMovies.getMovieIds().add(movieId);
        }
        userMoviesRepository.save(userMovies);
        return movieId;
    }

    private UserMovies createUserMovies(long userId, long movieId) {
        UserMovies userMovies = new UserMovies();
        userMovies.setUserId(userId);
        userMovies.setMovieIds(new HashSet<>(Collections.singletonList(movieId)));
        return userMovies;
    }

}
