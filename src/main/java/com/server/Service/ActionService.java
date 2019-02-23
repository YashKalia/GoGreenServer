package com.server.Service;

import com.server.Dao.ActionDao;
import com.server.Entity.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ActionService {

    @Autowired
    private ActionDao actionDao;

    public Collection<Action> getAllActions(){
        return actionDao.getAllActions();
    }

    public Action getActionById(int id){
        return actionDao.getActionById(id);
    }

    public void deleteActionById(int id) {
        actionDao.deleteActionById(id);
    }

    public void updateAction(Action action){
        actionDao.updateAction(action);
    }

    public void insertAction(Action action) {
        actionDao.insertAction(action);
    }
}
