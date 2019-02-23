package com.server.Entity;

public class Action {
    private int id;
    private String name;
    private int points;

    public Action(int id, String name, int points) {
        this.id = id;
        this.name = name;
        this.points = points;
    }

    public Action(){};

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
