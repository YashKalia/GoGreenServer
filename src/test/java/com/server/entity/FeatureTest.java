package com.server.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class FeatureTest {

    private Feature feature1;
    private Feature feature2;

    @Before
    public void setup() {

        feature1 = new Feature();
        feature1.setId(1);
        feature1.setFeatureName("Eating a vegetarian meal");
        feature1.setFeatureValue(10);

        feature2 = new Feature();
        feature2.setId(1);
        feature2.setFeatureName("Eating a vegetarian meal");
        feature2.setFeatureValue(10);

    }

    @Test
    public void testGetFeatureId() {

        assertEquals(1, feature1.getId());

    }

    @Test
    public void testSetFeatureId() {

        feature1.setId(15);

        assertEquals(15, feature1.getId());

    }

    @Test
    public void testGetFeatureName() {

        assertEquals("Eating a vegetarian meal", feature1.getFeatureName());

    }

    @Test
    public void testSetFeatureName() {

        feature1.setFeatureName("Riding a bike to work");

        assertEquals("Riding a bike to work", feature1.getFeatureName());

    }

    @Test
    public void testGetFeatureValue() {

        assertEquals(10, feature1.getFeatureValue());

    }

    @Test
    public void testSetFeatureValue() {

        feature1.setFeatureValue(15);

        assertEquals(15, feature1.getFeatureValue());

    }

    @Test
    public void testEqualsSameMemLoc() {

        Feature feature3 = feature1;

        assertTrue(feature1.equals(feature3));

    }

    @Test
    public void testEqualsTrue() {

        assertTrue(feature1.equals(feature2));

    }

    @Test
    public void testEqualsWrongId() {

        feature2.setId(2);

        assertFalse(feature1.equals(feature2));

    }

    @Test
    public void testEqualsWrongName() {

        feature2.setFeatureName("Riding a bike to work");

        assertFalse(feature1.equals(feature2));

    }

    @Test
    public void testEqualsWrongValue() {

        feature2.setFeatureValue(20);

        assertFalse(feature1.equals(feature2));

    }

    @Test
    public void testEqualsWrongObj() {

        assertFalse(feature1.equals("hello"));

    }

}