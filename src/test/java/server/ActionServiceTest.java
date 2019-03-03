package server;

import com.server.entity.Action;
import com.server.service.ActionService;

import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


@RunWith(SpringRunner.class)
@SpringBootTest(classes={com.server.service.ActionService.class, com.server.dao.ActionDao.class})
public class ActionServiceTest {

    @Autowired
    private ActionService actionService;

    @Test
    public void testGetActionById() {

        Action action2 = actionService.getActionById(2);

        assertTrue(action2.getName().equals("Planting a tree"));
    }

    @Test
    public void testGetActionByIdException() {

        assertThrows(IllegalArgumentException.class, () -> {

            actionService.getActionById(15);

        });
    }

    @Test
    public void testGetAllActions() {

        Collection<Action> actions = actionService.getAllActions();

        assertTrue(actions.size() == 4);
    }

    @Test
    public void testDeleteActionById() {

        actionService.deleteActionById(1);

        Collection<Action> actions = actionService.getAllActions();

        assertTrue(actions.size() == 3);

        Action action = new Action(1,"Eating a vegan meal",10);
        actionService.insertAction(action);

    }

    @Test
    public void testInsertAction() {

        Action action = new Action(5,"Installing a solar panel",10000);
        actionService.insertAction(action);

        Action action2 = actionService.getActionById(5);

        assertTrue(action.getName().equals(action2.getName()));

        actionService.deleteActionById(5);

    }

    @Test
    public void testUpdateAction() {

        Action action = new Action(1,"Installing a solar panel",10000);
        actionService.updateAction(action);

        Action action2 = actionService.getActionById(1);

        assertTrue(action.getName().equals(action2.getName()));

        action = new Action(1,"Eating a vegan meal",10);
        actionService.updateAction(action);

    }

}


