package com.server.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "userx")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long user_id;
    private String username;
    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="user_role", joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Role> roles;

    public User(){}

    public User(long user_id, String username, String password) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
