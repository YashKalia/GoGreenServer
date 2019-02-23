package com.server.Controller;

import com.server.Entity.Action;
import com.server.Service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/actions")
public class ActionController {

    @Autowired
    private ActionService actionService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Action> getAllActions(){
        return actionService.getAllActions();
    }

    @RequestMapping(value = "/{id}", method=RequestMethod.GET)
    public Action getActionById(@PathVariable("id") int id){
        return actionService.getActionById(id);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public void deleteActionByID(@PathVariable("id") int id){
        actionService.deleteActionById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateAction(@RequestBody Action action){
        actionService.updateAction(action);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertAction(@RequestBody Action action){
        actionService.insertAction(action);
    }
}
