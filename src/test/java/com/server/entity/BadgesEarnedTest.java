package com.server.entity;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class BadgesEarnedTest {

    private User user1;
    private User user2;

    private Badge badge1 = new Badge();
    private Badge badge2;

    private BadgesEarned badgesEarned1 = new BadgesEarned();
    private BadgesEarned badgesEarned2;
    private Date date = new Date();

    @Before
    public void setup() {

        user1 = new User("user1", "password");
        user1.setId((long) 1);
        user2 = new User("user2", "password");
        user2.setId((long) 2);

        badge1.setId(11);
        badge1.setBadgeName("First Vegetarian Meal Eaten");
        badge1.setPointsNeeded(10);

        badge2 = new Badge("First Local Produce Bought", 10);
        badge2.setId(21);

        badgesEarned1.setId(1);
        badgesEarned1.setBadge(badge1);
        badgesEarned1.setUser(user1);
        badgesEarned1.setDate(date);

        badgesEarned2 = new BadgesEarned(badge1, user1);
        badgesEarned2.setId(1);
        badgesEarned2.setDate(date);

    }

    @Test
    public void testGetEntryId() {

        assertEquals(1, badgesEarned1.getId());

    }

    @Test
    public void testSetEntryId() {

        badgesEarned1.setId(15);

        assertEquals(15, badgesEarned1.getId());

    }

    @Test
    public void testGetBadge() {

        assertEquals(badge1, badgesEarned1.getBadge());

    }

    @Test
    public void testSetBadge() {

        badgesEarned1.setBadge(badge2);

        assertEquals(badge2, badgesEarned1.getBadge());

    }

    @Test
    public void testGetUser() {

        assertEquals(user1, badgesEarned1.getUser());

    }

    @Test
    public void testSetUser() {

        badgesEarned1.setUser(user2);

        assertEquals(user2, badgesEarned1.getUser());

    }

    @Test
    public void testGetDate() {

        assertEquals(date, badgesEarned1.getDate());

    }

    @Test
    public void testSetDate() {

        date = new Date();
        badgesEarned1.setDate(date);

        assertEquals(date, badgesEarned1.getDate());

    }

    @Test
    public void testEqualsSameMemLoc() {

        BadgesEarned badgesEarned3 = badgesEarned1;

        assertTrue(badgesEarned1.equals(badgesEarned3));

    }

    @Test
    public void testEqualsTrue() {

        assertTrue(badgesEarned1.equals(badgesEarned2));

    }

    @Test
    public void testEqualsWrongId() {

        badgesEarned2.setId(2);

        assertFalse(badgesEarned1.equals(badgesEarned2));

    }

    @Test
    public void testEqualsWrongBadge() {

        badgesEarned2.setBadge(badge2);

        assertFalse(badgesEarned1.equals(badgesEarned2));

    }

    @Test
    public void testEqualsWrongUser() {

        badgesEarned2.setUser(user2);

        assertFalse(badgesEarned1.equals(badgesEarned2));

    }

    @Test
    public void testEqualsWrongObj() {

        assertFalse(badgesEarned1.equals("hello"));

    }

}
