package com.server.controller;

import com.server.entity.Action;
import com.server.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/actions")
public class ActionController {

    @Autowired
    private ActionService actionService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Action> getAllActions() {
        return actionService.getAllActions();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Action getActionById(@PathVariable("id") int id) {
        return actionService.getActionById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteActionById(@PathVariable("id") int id) {
        actionService.deleteActionById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateAction(@RequestBody Action action) {
        actionService.updateAction(action);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertAction(@RequestBody Action action) {
        actionService.insertAction(action);
    }
}
