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
        feature1.setPoints(10);
        feature1.setCo2(1.2);

        feature2 = new Feature();
        feature2.setId(1);
        feature2.setFeatureName("Eating a vegetarian meal");
        feature2.setPoints(10);
        feature2.setCo2(1.2);

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
    public void testGetFeaturePoints() {

        assertEquals(10, feature1.getPoints());

    }

    @Test
    public void testSetFeaturePoints() {

        feature1.setPoints(15);

        assertEquals(15, feature1.getPoints());

    }

    @Test
    public void testGetFeatureCo2() {

        assertTrue(1.2 == feature1.getCo2());

    }

    @Test
    public void testSetFeatureCo2() {

        feature1.setCo2(1.5);

        assertTrue(1.5 == feature1.getCo2());

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
    public void testEqualsWrongPoints() {

        feature2.setPoints(20);

        assertFalse(feature1.equals(feature2));

    }

    @Test
    public void testEqualsWrongCo2() {

        feature2.setCo2(1.5);

        assertFalse(feature1.equals(feature2));

    }

    @Test
    public void testEqualsWrongObj() {

        assertFalse(feature1.equals("hello"));

    }

}