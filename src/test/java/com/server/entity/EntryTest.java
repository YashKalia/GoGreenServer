package com.server.entity;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class EntryTest {

    private User user1;
    private User user2;

    private Feature feature1;
    private Feature feature2;

    private Entry entry1;
    private Entry entry2;
    private Date date = new Date();

    @Before
    public void setup() {

        user1 = new User("user1", "password");
        user1.setId((long) 1);
        user2 = new User("user2", "password");
        user2.setId((long) 2);

        feature1 = new Feature();
        feature1.setId(1);
        feature1.setFeatureName("Eating a vegetarian meal");
        feature1.setFeatureValue(10);

        feature2 = new Feature();
        feature2.setId(2);
        feature2.setFeatureName("Riding a bike to work");
        feature2.setFeatureValue(20);

        entry1 = new Entry(feature1, user1);
        entry1.setId(1);
        entry1.setDate(date);

        entry2 = new Entry(feature1, user1);
        entry2.setId(1);

    }

    @Test
    public void testGetEntryId() {

        assertEquals(1, entry1.getId());

    }

    @Test
    public void testSetEntryId() {

        entry1.setId(15);

        assertEquals(15, entry1.getId());

    }

    @Test
    public void testGetFeature() {

        assertEquals(feature1, entry1.getFeature());

    }

    @Test
    public void testSetFeature() {

        entry1.setFeature(feature2);

        assertEquals(feature2, entry1.getFeature());

    }

    @Test
    public void testGetUser() {

        assertEquals(user1, entry1.getUser());

    }

    @Test
    public void testSetUser() {

        entry1.setUser(user2);

        assertEquals(user2, entry1.getUser());

    }

    @Test
    public void testGetAction() {

        assertEquals(feature1, entry1.getAction());

    }

    @Test
    public void testSetAction() {

        entry1.setAction(feature2);

        assertEquals(feature2, entry1.getAction());

    }

    @Test
    public void testGetDate() {

        assertEquals(date, entry1.getDate());

    }

    @Test
    public void testSetDate() {

        date = new Date();
        entry1.setDate(date);

        assertEquals(date, entry1.getDate());

    }

    @Test
    public void testEqualsSameMemLoc() {

        Entry entry3 = entry1;

        assertTrue(entry1.equals(entry3));

    }

    @Test
    public void testEqualsTrue() {

        assertTrue(entry1.equals(entry2));

    }

    @Test
    public void testEqualsWrongId() {

        entry2.setId(2);

        assertFalse(entry1.equals(entry2));

    }

    @Test
    public void testEqualsWrongFeature() {

        entry2.setFeature(feature2);

        assertFalse(entry1.equals(entry2));

    }

    @Test
    public void testEqualsWrongUser() {

        entry2.setUser(user2);

        assertFalse(entry1.equals(entry2));

    }

    @Test
    public void testEqualsWrongObj() {

        assertFalse(entry1.equals("hello"));

    }

}
