package com.server.entity;

public class RequestUserFeature {
    private User user;
    private Feature feature;

    public RequestUserFeature() {}

    public RequestUserFeature(Feature feature, User user) {
        this.user = user;
        this.feature = feature;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }
}
