package com.server.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "friends")
public class Friends {
    /**
     * The user adds the friend and not the other way around.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "friend_id", referencedColumnName = "user_id")
    private User friend;

    public Friends(User user, User friend) {
        this.user = user;
        this.friend = friend;
    }

    public Friends() {
    }

    public boolean areTheyMyFriend(User user) {
        return this.getFriend().getUsername().equals(user.getUsername());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }
}
