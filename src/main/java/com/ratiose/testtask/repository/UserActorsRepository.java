package com.ratiose.testtask.repository;

import com.ratiose.testtask.entity.UserActors;
import org.springframework.data.repository.CrudRepository;

public interface UserActorsRepository extends CrudRepository<UserActors, Long> {

    void deleteByUserAndActorId(long userId, long actorId);

}
