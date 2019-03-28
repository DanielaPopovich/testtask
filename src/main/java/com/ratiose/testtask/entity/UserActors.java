package com.ratiose.testtask.entity;

import java.util.Map;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserActors {

    @Id
    @GeneratedValue
    private Long userId;

    @ElementCollection
    private Map<Long, Actor> actors;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Map<Long, Actor> getActors() {
        return actors;
    }

    public void setActors(Map<Long, Actor> actors) {
        this.actors = actors;
    }
}
