package com.server.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class RequestUserFeatureTest {

    private User user1;
    private User user2;

    private Feature feature1 = new Feature();
    private Feature feature2 = new Feature();

    private RequestUserFeature entry1;
    private RequestUserFeature entry2;

    @Before
    public void setup() {

        user1 = new User("user1", "password");
        user1.setId((long) 1);
        user2 = new User("user2", "password");
        user2.setId((long) 2);

        feature1.setId(1);
        feature1.setFeatureName("Eating a vegetarian meal");
        feature1.setPoints(10);
        feature1.setCo2(1.2);

        feature2.setId(2);
        feature2.setFeatureName("Riding a bike to work");
        feature2.setPoints(20);
        feature2.setCo2(5.1);

        entry1 = new RequestUserFeature(feature1, user1, 1);
        entry2 = new RequestUserFeature(feature1, user1);

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
    public void testGetBadgeCode() {

        assertTrue(1 == entry1.getBadgeCode());

    }

    @Test
    public void testSetBadgeCode() {

        entry1.setBadgeCode(2);

        assertTrue(2 == entry1.getBadgeCode());

    }

    @Test
    public void testEqualsSameMemLoc() {

        RequestUserFeature entry3 = entry1;

        assertTrue(entry1.equals(entry3));

    }

    @Test
    public void testEqualsTrue() {

        assertTrue(entry1.equals(entry2));

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

    @Test
    public void testDefaultConstructor() {
        entry1 = new RequestUserFeature();
        assertEquals(null,entry1.getUser());
        assertEquals(null,entry1.getFeature());
    }

}
