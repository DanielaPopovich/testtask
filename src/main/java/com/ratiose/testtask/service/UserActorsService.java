package com.ratiose.testtask.service;

import com.ratiose.testtask.entity.Actor;
import com.ratiose.testtask.entity.UserActors;

public interface UserActorsService {

    Actor addFavoriteActor(long userId, String firstName, String lastName);

    UserActors getUserFavoriteActors(long userId);

    void removeActorFromFavorites(long userId, long actorId);

}
