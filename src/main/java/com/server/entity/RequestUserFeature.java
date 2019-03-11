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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RequestUserFeature)) {
            return false;
        }
        RequestUserFeature other = (RequestUserFeature) obj;
        if (!feature.equals(other.feature)) {
            return false;
        }
        if (!user.equals(other.user)) {
            return false;
        }
        return true;
    }

}
