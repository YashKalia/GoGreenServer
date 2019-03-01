package com.server.entity;

public class Action {
    private int id;
    private String name;
    private int points;

    /**
     * Constructor.
     * @param id the given unique id.
     * @param name the name of the action.
     * @param points the amount of points the action has.
     */
    public Action(int id, String name, int points) {
        this.id = id;
        this.name = name;
        this.points = points;
    }

    public Action(){}

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Action)) {
            return false;
        }
        Action other = (Action) obj;
        if (id != other.id) {
            return false;
        }
        if (!name.equals(other.name)) {
            return false;
        }
        if (points != other.points) {
            return false;
        }
        return true;
    }

}
