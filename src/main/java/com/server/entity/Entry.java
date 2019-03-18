package com.server.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "entry_id")
    private long id;

    @OneToOne()
    @JoinColumn(name = "featureID")
    private Feature feature;

    @Column(name = "date")
    private Date date = new Date();

    @OneToOne
    @JoinColumn(name = "id")
    private User user;

    public Entry() {}

    public Entry(Feature action, User user) {
        this.user = user;
        this.feature = action;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
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

    public Feature getAction() {
        return feature;
    }

    public void setAction(Feature action) {
        this.feature = action;
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
        if (!(obj instanceof Entry)) {
            return false;
        }
        Entry other = (Entry) obj;
        if (id != other.id) {
            return false;
        }
        if (!feature.equals(other.feature)) {
            return false;
        }
        if (!user.equals(other.user)) {
            return false;
        }
        return true;
    }
}
