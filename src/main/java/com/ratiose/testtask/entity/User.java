package com.ratiose.testtask.entity;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue
    String id;

    @Column(unique=true)
    String email;

    String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
