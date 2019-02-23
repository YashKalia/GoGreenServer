package com.server.Dao;

import com.server.Entity.Action;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Repository
public class ActionDao {
    private static Map<Integer, Action> actions;
    static{
        actions = new HashMap<Integer, Action>(){
            {
                put(1,new Action(1,"Eating a vegan meal",10));
                put(2,new Action(2,"Planting a tree", 20));
                put(3,new Action(3,"Riding a bike to work",5));
                put(4, new Action(4, "Committed Fraud", Integer.MIN_VALUE));
            }
        };
    }

    public Collection<Action> getAllActions(){
        return this.actions.values();
    }

    public Action getActionById(int id){
        if(this.actions.containsKey(id))
        return this.actions.get(id);
        throw new IllegalArgumentException("Invalid id");
    }

    public void deleteActionById(int id) {
        if(actions.containsKey(id))
            actions.remove(id);
        else {
            throw new IllegalArgumentException("ID not found");
        }
    }

    public void updateAction(Action action){
        Action a = actions.get(action.getId());
        a.setName(action.getName());
        a.setPoints(action.getPoints());
        actions.put(action.getId(),a);
    }

    public void insertAction(Action action) {
        if(!actions.containsKey(action.getId()))
        actions.put(action.getId(), action);
        else {
            throw new IllegalArgumentException("ID already in use");
        }

    }
}
