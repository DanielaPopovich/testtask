package com.ratiose.testtask.service.impl;

import com.ratiose.testtask.entity.Actor;
import com.ratiose.testtask.entity.UserActors;
import com.ratiose.testtask.exception.RequestException;
import com.ratiose.testtask.repository.UserActorsRepository;
import com.ratiose.testtask.service.UserActorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class UserActorsServiceImpl implements UserActorsService {

    @Autowired
    private UserActorsRepository userActorsRepository;

    @Override
    public Actor addFavoriteActor(long userId, String firstName, String lastName) {
        Actor actor = new Actor();
        actor.setFirstName(firstName);
        actor.setLastName(lastName);

        UserActors userActors = userActorsRepository.findOne(userId);
        if (userActors == null) {
            userActors = createUserActors(userId, actor);
        } else {
            actor.setActorId(new Random().nextLong());
            userActors.getActors().put(actor.getActorId(), actor);
        }
        userActorsRepository.save(userActors);
        return actor;
    }

    @Override
    public UserActors getUserFavoriteActors(long userId) {
        UserActors userActors = userActorsRepository.findOne(userId);
        if (userActors == null || userActors.getActors().isEmpty()) {
            throw new RequestException(HttpStatus.NOT_FOUND, "There is no favorite actors for your user");
        }
        return userActors;
    }

    @Override
    public void removeActorFromFavorites(long userId, long actorId) {
        Actor actor = getUserFavoriteActorById(userId, actorId);
        userActorsRepository.deleteByUserAndActorId(userId, actor.getActorId());
    }

    private Actor getUserFavoriteActorById(long userId, long actorId) {
        UserActors userActors = userActorsRepository.findOne(userId);
        Actor actor = userActors.getActors().entrySet().stream()
            .filter(entry -> entry.getKey().equals(actorId))
            .map(Map.Entry::getValue).findFirst().orElse(null);
        if (actor == null) {
            throw new RequestException(HttpStatus.NOT_FOUND,
                String.format("There is no favorite actor with specified id: %s", actorId));
        }
        return actor;
    }

    private UserActors createUserActors(long userId, Actor actor) {
        UserActors userActors = new UserActors();
        userActors.setUserId(userId);
        actor.setActorId(new Random().nextLong());
        userActors.setActors(new HashMap<>(Collections.singletonMap(actor.getActorId(), actor)));
        return userActors;
    }

}
