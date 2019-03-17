package com.server.controller;

import com.server.entity.Feature;
import com.server.repository.FeatureRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FeatureControllerTest {

    @InjectMocks
    FeatureController featureController;

    @Mock
    FeatureRepository featureRepository;

    private List<Feature> features = new ArrayList<>();
    private Feature feature1 = new Feature();
    private Feature feature2 = new Feature();
    private Feature feature3 = new Feature();

    @Before
    public void setup() {

        feature1.setId(1);
        feature1.setFeatureName("Eating a vegetarian meal");
        feature1.setFeatureValue(10);

        feature2.setId(2);
        feature2.setFeatureName("Riding a bike to work");
        feature2.setFeatureValue(20);

        feature3.setId(3);
        feature3.setFeatureName("Installing a solar panel");
        feature3.setFeatureValue(2000);

        features.add(feature1);
        features.add(feature2);

    }

    @Test
    public void testGetAllFeatures() {

        when(featureRepository.findAll())
                .thenReturn(features);

        assertEquals(features, featureController.getAllFeatures());

    }

    @Test
    public void testAddFeatureSuccess() {

        when(featureRepository.findAll())
                .thenReturn(features);

        assertEquals(features, featureController.addFeature(feature1));

    }

    @Test
    public void testDeleteFeatureSuccess() {

        features.remove(0);

        when(featureRepository.findById((long) 1))
                .thenReturn(java.util.Optional.ofNullable(feature1));

        assertEquals(new ArrayList<>(), featureController.deleteFeatureById((long) 1));

    }

    @Test
    public void testDeleteFeatureFailure() {

        when(featureRepository.findById((long) 3))
                .thenReturn(null);

        assertEquals(new ArrayList<>(), featureController.deleteFeatureById((long) 3));

    }
}
