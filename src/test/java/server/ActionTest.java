package server;

import com.server.Entity.Action;

import org.junit.*;

import static org.junit.Assert.*;

public class ActionTest {

    private Action action;

    @Before
    public void setup() {

        action = new Action();

        action.setId(1);
        action.setName("Eating a vegan meal");
        action.setPoints(10);

    }

    @Test
    public void testGetId() {

        assertTrue(action.getId() == 1);

    }

    @Test
    public void testGetName() {

        String name = "Eating a vegan meal";

        assertTrue(action.getName().equals(name));

    }

    @Test
    public void testGetPoints() {

        assertTrue(action.getPoints() == 10);

    }

    @Test
    public void testSetId() {

        action.setId(2);

        assertTrue(action.getId() == 2);

    }

    @Test
    public void testSetName() {

        String name = "Planting a tree";
        action.setName(name);

        assertTrue(action.getName().equals(name));

    }

    @Test
    public void testSetPoints() {

        action.setPoints(200);

        assertTrue(action.getPoints() == 200);

    }

    @Test
    public void testEqualsSameMemLoc() {

        Action action2 = action;

        assertTrue(action.equals(action2));

    }

    @Test
    public void testEqualsTrue() {

        Action action2 = new Action(1,"Eating a vegan meal",10);

        assertTrue(action.equals(action2));

    }

    @Test
    public void testEqualsWrongId() {

        Action action2 = new Action(2,"Eating a vegan meal",10);

        assertFalse(action.equals(action2));

    }

    @Test
    public void testEqualsWrongName() {

        Action action2 = new Action(1,"Eating a vegetarian meal",10);

        assertFalse(action.equals(action2));

    }

    @Test
    public void testEqualsWrongPoints() {

        Action action2 = new Action(1,"Eating a vegan meal",100);

        assertFalse(action.equals(action2));

    }

    @Test
    public void testEqualsWrongObject() {

        String name = "Eating a vegan meal";

        assertFalse(action.equals(name));

    }

}
