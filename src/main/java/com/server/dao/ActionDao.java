package com.server.dao;

import com.server.entity.Action;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Repository
public class ActionDao {
    private static Map<Integer, Action> actions;

    static {
        actions = new HashMap<Integer, Action>() {
            {
                put(1,new Action(1,"Eating a vegan meal",10));
                put(2,new Action(2,"Planting a tree", 20));
                put(3,new Action(3,"Riding a bike to work",5));
                put(4,new Action(4,"Committed Fraud", Integer.MIN_VALUE));
            }
        };
    }

    public Collection<Action> getAllActions() {
        return this.actions.values();
    }

    /** Returns the action of the given id, throws an exception if the id doesn't exist.
     * @param id action to insert.
     */
    public Action getActionById(int id) {
        if (this.actions.containsKey(id)) {
            return this.actions.get(id);
        }
        throw new IllegalArgumentException("Invalid id");
    }

    /** Deletes the action with the given id, if it exists.
     * Throws an exception otherwise.
     * @param id action to delete.
     */
    public void deleteActionById(int id) {
        if (actions.containsKey(id)) {
            actions.remove(id);
        } else {
            throw new IllegalArgumentException("ID not found");
        }
    }

    /** Updates the values an action into the map.
     * @param action to be edited.
     */
    public void updateAction(Action action) {
        Action action1 = actions.get(action.getId());
        action1.setName(action.getName());
        action.setPoints(action.getPoints());
        actions.put(action.getId(),action);
    }

    /** Inserts an action into the map.
     * @param action action to insert.
     */
    public void insertAction(Action action) {
        if (!actions.containsKey(action.getId())) {
            actions.put(action.getId(), action);
        } else {
            throw new IllegalArgumentException("ID already in use");
        }

    }
}
