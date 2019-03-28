package com.ratiose.testtask.repository.impl;

import com.ratiose.testtask.entity.UserMovies;
import com.ratiose.testtask.repository.UserMoviesRepository;

import java.util.HashMap;
import java.util.Map;

public class UserMoviesRepositoryImpl implements UserMoviesRepository {

    private Map<Long, UserMovies> userMovies = new HashMap<>();

    @Override
    public <S extends UserMovies> S save(S userMovie) {
        return (S) userMovies.put(userMovie.getUserId(), userMovie);
    }

    @Override
    public <S extends UserMovies> Iterable<S> save(Iterable<S> iterable) {
        return null;
    }

    @Override
    public UserMovies findOne(Long userId) {
        return userMovies.get(userId);
    }

    @Override
    public boolean exists(Long aLong) {
        return false;
    }

    @Override
    public Iterable<UserMovies> findAll() {
        return null;
    }

    @Override
    public Iterable<UserMovies> findAll(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void delete(UserMovies userMovies) {

    }

    @Override
    public void delete(Iterable<? extends UserMovies> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
