package com.ratiose.testtask.repository.impl;

import com.ratiose.testtask.entity.User;
import com.ratiose.testtask.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class UserRepositoryImpl implements UserRepository {

    private Map<Long, User> usersByIds = new HashMap<>();

    private Map<String, User> usersByEmails = new HashMap<>();

    @Override
    public User findByEmail(final String email) {
        return usersByEmails.get(email);
    }

    @Override
    public <S extends User> S save(final S user) {
        user.setId(new Random().nextLong());
        usersByEmails.put(user.getEmail(), user);
        return user;
    }

    @Override
    public <S extends User> Iterable<S> save(final Iterable<S> iterable) {
        return null;
    }

    @Override
    public User findOne(final Long aLong) {
        return null;
    }

    @Override
    public boolean exists(final Long aLong) {
        return false;
    }

    @Override
    public Iterable<User> findAll() {
        return null;
    }

    @Override
    public Iterable<User> findAll(final Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(final Long aLong) {

    }

    @Override
    public void delete(final User user) {

    }

    @Override
    public void delete(final Iterable<? extends User> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
