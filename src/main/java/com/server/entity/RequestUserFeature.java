package com.server.entity;

public class RequestUserFeature {
    private User user;
    private Feature feature;
    private int badgeCode;

    public RequestUserFeature() {}

    public RequestUserFeature(Feature feature, User user) {
        this.user = user;
        this.feature = feature;
    }

    /**
     * Alternativa constructor made for BadgesEarnedController.
     *
     * @param feature The feature relevant for the badge request
     * @param user The user relevant for the badge request
     * @param badgeCode The badgeCode relevant for the badge request
     */
    public RequestUserFeature(Feature feature, User user, int badgeCode) {
        this.user = user;
        this.feature = feature;
        this.badgeCode = badgeCode;
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

    public int getBadgeCode() {
        return badgeCode;
    }

    public void setBadgeCode(int badgeCode) {
        this.badgeCode = badgeCode;
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
