package com.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "badge")
public class Badge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "badge_id")
    private long id;

    @Column(name = "badge_name")
    @NotNull
    private String badgeName;

    @Column(name = "points_needed")
    @NotNull
    private int pointsNeeded;

    public Badge() {}

    public Badge(String badgeName, int pointsNeeded) {
        this.badgeName = badgeName;
        this.pointsNeeded = pointsNeeded;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBadgeName() {
        return badgeName;
    }

    public void setBadgeName(String badgeName) {
        this.badgeName = badgeName;
    }

    public int getPointsNeeded() {
        return pointsNeeded;
    }

    public void setPointsNeeded(int points) {
        this.pointsNeeded = points;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Badge)) {
            return false;
        }
        Badge other = (Badge) obj;
        if (id != other.id) {
            return false;
        }
        if (!badgeName.equals(other.badgeName)) {
            return false;
        }
        if (pointsNeeded != other.pointsNeeded) {
            return false;
        }
        return true;
    }
}
