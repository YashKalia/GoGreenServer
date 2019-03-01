package server;

import com.server.dao.ActionDao;
import com.server.entity.Action;

import org.junit.*;
import java.util.Collection;

import static org.junit.Assert.*;


public class ActionDaoTest {

    private ActionDao actions;
    private Action action;
    private Action action2;

    @Before
    public void setup() {

        actions = new ActionDao();
        action = new Action(1,"Eating a vegan meal",10);
        action2 = new Action(5,"Installing a solar panel",10000);

    }

    @Test
    public void testGetAllActions() {

        Collection<Action> actions2 = actions.getAllActions();

        assertTrue(actions2.size() == 4);

    }

    @Test
    public void testGetActionById() {

        assertTrue(actions.getActionById(1).equals(action));

    }

    @Test
    public void testGetActionByIdException() {

        assertThrows(IllegalArgumentException.class, () -> {

            actions.getActionById(15);

        });

    }

    @Test
    public void testDeleteActionById() {

        actions.deleteActionById(1);

        assertThrows(IllegalArgumentException.class, () -> {

            actions.getActionById(1);

        });

    }

    @Test
    public void testDeleteActionByIdException() {

        assertThrows(IllegalArgumentException.class, () -> {

            actions.deleteActionById(15);

        });

    }

    @Test
    public void testUpdateAction() {

        action2.setId(2);
        actions.updateAction(action2);

        assertEquals(action2, actions.getActionById(2));

    }

    @Test
    public void testInsertAction() {

        actions.insertAction(action2);

        assertEquals(action2, actions.getActionById(5));

    }

    @Test
    public void testInsertActionException() {

        action2.setId(1);

        assertThrows(IllegalArgumentException.class, () -> {

            actions.insertAction(action2);

        });

    }

}
