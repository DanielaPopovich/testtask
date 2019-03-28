package com.ratiose.testtask.repository.impl;

import com.ratiose.testtask.entity.UserActors;
import com.ratiose.testtask.repository.UserActorsRepository;

import java.util.HashMap;
import java.util.Map;

public class UserActorsRepositoryImpl implements UserActorsRepository {

    private Map<Long, UserActors> userActorsMap = new HashMap<>();

    @Override
    public <S extends UserActors> S save(S userActors) {
        return (S) userActorsMap.put(userActors.getUserId(), userActors);
    }

    @Override
    public <S extends UserActors> Iterable<S> save(Iterable<S> iterable) {
        return null;
    }

    @Override
    public UserActors findOne(Long userId) {
        return userActorsMap.get(userId);
    }

    @Override
    public boolean exists(Long aLong) {
        return false;
    }

    @Override
    public Iterable<UserActors> findAll() {
        return null;
    }

    @Override
    public Iterable<UserActors> findAll(Iterable<Long> iterable) {
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
    public void delete(UserActors userActors) {

    }

    @Override
    public void delete(Iterable<? extends UserActors> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void deleteByUserAndActorId(long userId, long actorId) {
        UserActors userActors = userActorsMap.get(userId);
        userActors.getActors().remove(actorId);
    }
}
