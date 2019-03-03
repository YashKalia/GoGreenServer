package com.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

public class User {

    @Id
    @Column(name="userID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int id;

    @Column(name="username")
    String username;

    @Column(name="password")
    String password;

    public User() {
        super();
    }
    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

}
