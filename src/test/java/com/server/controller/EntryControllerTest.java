package com.server.controller;

import com.server.entity.Entry;
import com.server.entity.Feature;
import com.server.entity.RequestUserFeature;
import com.server.entity.User;
import com.server.repository.EntryRepository;
import com.server.repository.FeatureRepository;
import com.server.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EntryControllerTest {

    @InjectMocks
    EntryController entryController;

    @Mock
    EntryRepository entryRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    FeatureRepository featureRepository;

    private User user1;
    private User user2;

    private Feature feature1 = new Feature();
    private Feature feature2 = new Feature();
    private Feature feature3 = new Feature();
    private Feature feature4 = new Feature();
    private Feature feature5 = new Feature();
    private Feature feature6 = new Feature();

    private RequestUserFeature req1;

    private Entry entry1;
    private Entry entry2;
    private Entry entry3;
    private Entry entry4;
    private Entry entry5;
    private Entry entry6;
    private Entry entry7;

    List<Entry> allEntries = new ArrayList<>();
    List<Entry> entriesUser = new ArrayList<>();
    List<Entry> entriesFeature1 = new ArrayList<>();
    List<Entry> entriesFeature2 = new ArrayList<>();
    List<Entry> entriesFeature3 = new ArrayList<>();
    List<Entry> entriesFeature4 = new ArrayList<>();
    List<Entry> entriesFeature5 = new ArrayList<>();
    List<Entry> entriesFeature6 = new ArrayList<>();

    @Before
    public void setup() {

        user1 = new User("user1", "password");
        user1.setId(1);
        user2 = new User("user2", "password");
        user2.setId(2);

        feature1.setId(1);
        feature1.setFeatureName("Eating a vegetarian meal");
        feature1.setPoints(10);
        feature1.setCo2(1.2);

        feature2.setId(2);
        feature2.setFeatureName("Buying local produce");
        feature2.setPoints(10);
        feature2.setCo2(4.3);

        feature3.setId(3);
        feature3.setFeatureName("Using bike instead of car");
        feature3.setPoints(20);
        feature3.setCo2(5.1);

        feature4.setId(4);
        feature4.setFeatureName("Using public transport instead of car");
        feature4.setPoints(15);
        feature4.setCo2(3.1);

        feature5.setId(5);
        feature5.setFeatureName("Lowering the temperature of your home");
        feature5.setPoints(10);
        feature5.setCo2(4.6);

        feature6.setId(6);
        feature6.setFeatureName("Installing solar panels");
        feature6.setPoints(100);
        feature6.setCo2(101.3);

        req1 = new RequestUserFeature(feature1, user1);

        entry1 = new Entry(feature1, user1);
        entry2 = new Entry(feature1, user2);
        entry3 = new Entry(feature2, user1);
        entry4 = new Entry(feature3, user2);
        entry5 = new Entry(feature4, user1);
        entry6 = new Entry(feature5, user2);
        entry7 = new Entry(feature6, user1);

        allEntries.add(entry1);
        allEntries.add(entry2);
        allEntries.add(entry3);

        entriesUser.add(entry1);
        entriesUser.add(entry3);

        entriesFeature1.add(entry1);
        entriesFeature1.add(entry2);
        entriesFeature2.add(entry3);
        entriesFeature3.add(entry4);
        entriesFeature4.add(entry5);
        entriesFeature5.add(entry6);
        entriesFeature6.add(entry7);

    }

    @Test
    public void testGetAllEntries() {

        when(entryRepository.findAll()).thenReturn(allEntries);

        assertEquals(allEntries, entryController.getAllEntries());

    }

    @Test
    public void testAddEntry() {

        when(entryRepository.findAll()).thenReturn(allEntries);

        assertEquals(allEntries, entryController.addEntry(req1));

    }

    @Test
    public void testGetEntriesByUsername() {

        when(userRepository.findByUsername(user1.getUsername())).thenReturn(user1);

        when(entryRepository.findByUserId((long) 1)).thenReturn(entriesUser);

        assertEquals(entriesUser, entryController.getEntriesByUsername(user1));

    }

    @Test
    public void testGetEntriesByFeature() {

        when(featureRepository.findByFeatureName(feature1.getFeatureName())).thenReturn(feature1);

        when(entryRepository.findByFeatureId((long) 1)).thenReturn(entriesFeature1);

        assertEquals(entriesFeature1, entryController.getEntriesByFeature(feature1));

    }

    @Test
    public void testGetAllVegetarianMeals() {

        when(entryRepository.findByFeatureId(1)).thenReturn(entriesFeature1);

        when(userRepository.findByUsername(user1.getUsername())).thenReturn(user1);

        assertEquals(1, entryController.getAllVegetarianMeals(user1));

    }

    @Test
    public void testGetAllLocalProduce() {

        when(entryRepository.findByFeatureId(2)).thenReturn(entriesFeature2);

        when(userRepository.findByUsername(user1.getUsername())).thenReturn(user1);

        assertEquals(1, entryController.getAllLocalProduce(user1));

    }

    @Test
    public void testGetAllBikeRides() {

        when(entryRepository.findByFeatureId(3)).thenReturn(entriesFeature3);

        when(userRepository.findByUsername(user2.getUsername())).thenReturn(user2);

        assertEquals(1, entryController.getAllBikeRides(user2));

    }

    @Test
    public void testGetAllPublicTransport() {

        when(entryRepository.findByFeatureId(4)).thenReturn(entriesFeature4);

        when(userRepository.findByUsername(user1.getUsername())).thenReturn(user1);

        assertEquals(1, entryController.getAllPublicTransport(user1));

    }

    @Test
    public void testGetAllLoweringTemperature() {

        when(entryRepository.findByFeatureId(5)).thenReturn(entriesFeature5);

        when(userRepository.findByUsername(user2.getUsername())).thenReturn(user2);

        assertEquals(1, entryController.getAllLoweringTemperature(user2));

    }

    @Test
    public void testGetAllSolarPanels() {

        when(entryRepository.findByFeatureId(6)).thenReturn(entriesFeature6);

        when(userRepository.findByUsername(user1.getUsername())).thenReturn(user1);

        assertEquals(1, entryController.getAllSolarPanels(user1));

    }

    @Test
    public void testGetEntriesByUserAndFeature() {

        when(entryRepository.findByFeatureId(1)).thenReturn(entriesFeature1);

        when(userRepository.findByUsername(user1.getUsername())).thenReturn(user1);

        assertEquals(1, entryController.getEntriesByUserAndFeature(user1, 1));

    }

    @Test
    public void testGetTotalCo2() {

        when(userRepository.findByUsername(user1.getUsername())).thenReturn(user1);

        when(entryRepository.findByUserId(1)).thenReturn(entriesUser);

        assertTrue(5.5 == entryController.getTotalCo2(user1));

    }

}

