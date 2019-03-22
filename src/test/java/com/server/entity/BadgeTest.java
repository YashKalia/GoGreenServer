package com.server.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class BadgeTest {

    private Badge badge1 = new Badge();
    private Badge badge2;

    @Before
    public void setup() {

        badge1.setId(11);
        badge1.setBadgeName("First Vegetarian Meal Eaten");
        badge1.setPointsNeeded(10);

        badge2 = new Badge("First Vegetarian Meal Eaten", 10);
        badge2.setId(11);

    }

    @Test
    public void testGetBadgeId() {

        assertEquals(11, badge1.getId());

    }

    @Test
    public void testSetBadgeId() {

        badge1.setId(15);

        assertEquals(15, badge1.getId());

    }

    @Test
    public void testGetBadgeName() {

        assertEquals("First Vegetarian Meal Eaten", badge1.getBadgeName());

    }

    @Test
    public void testSetBadgeName() {

        badge1.setBadgeName("First Local Produce Bought");

        assertEquals("First Local Produce Bought", badge1.getBadgeName());

    }

    @Test
    public void testGetPointsNeeded() {

        assertEquals(10, badge1.getPointsNeeded());

    }

    @Test
    public void testSetPointsNeeded() {

        badge1.setPointsNeeded(15);

        assertEquals(15, badge1.getPointsNeeded());

    }

    @Test
    public void testEqualsSameMemLoc() {

        Badge badge3 = badge1;

        assertTrue(badge1.equals(badge3));

    }

    @Test
    public void testEqualsTrue() {

        assertTrue(badge1.equals(badge2));

    }

    @Test
    public void testEqualsWrongId() {

        badge2.setId(2);

        assertFalse(badge1.equals(badge2));

    }

    @Test
    public void testEqualsWrongName() {

        badge2.setBadgeName("Riding a bike to work");

        assertFalse(badge1.equals(badge2));

    }

    @Test
    public void testEqualsWrongPoints() {

        badge2.setPointsNeeded(20);

        assertFalse(badge1.equals(badge2));

    }

    @Test
    public void testEqualsWrongObj() {

        assertFalse(badge1.equals("hello"));

    }

}
