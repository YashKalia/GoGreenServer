package com.server.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "badgesearned")
public class BadgesEarned {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "badges_earned_id")
    private long id;

    @OneToOne()
    @JoinColumn(name = "badge_id")
    private Badge badge;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "date")
    private Date date = new Date();

    public BadgesEarned() {}

    public BadgesEarned(Badge badge, User user) {
        this.user = user;
        this.badge = badge;
    }

    public Badge getBadge() {
        return badge;
    }

    public void setBadge(Badge badge) {
        this.badge = badge;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BadgesEarned)) {
            return false;
        }
        BadgesEarned other = (BadgesEarned) obj;
        if (id != other.id) {
            return false;
        }
        if (!badge.equals(other.badge)) {
            return false;
        }
        if (!user.equals(other.user)) {
            return false;
        }
        return true;
    }
}
