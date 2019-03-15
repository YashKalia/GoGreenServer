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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={EntryController.class})
public class EntryControllerTest {

    @Autowired
    EntryController entryController;

    @MockBean
    EntryRepository entryRepository;

    @MockBean
    UserRepository userRepository;

    @MockBean
    FeatureRepository featureRepository;

    private User user1;
    private User user2;

    private Feature feature1 = new Feature();
    private Feature feature2 = new Feature();

    private RequestUserFeature req1;

    private Entry entry1;
    private Entry entry2;
    private Entry entry3;

    List<Entry> allEntries = new ArrayList<>();
    List<Entry> entriesUser = new ArrayList<>();
    List<Entry> entriesFeature = new ArrayList<>();

    @Before
    public void setup() {

        user1 = new User("user1", "password");
        user1.setId(1);
        user2 = new User("user2", "password");
        user2.setId(2);

        feature1.setId(1);
        feature1.setFeatureName("Eating a vegetarian meal");
        feature1.setFeatureValue(10);

        feature2.setId(2);
        feature2.setFeatureName("Riding a bike to work");
        feature2.setFeatureValue(20);

        req1 = new RequestUserFeature(feature1, user1);

        entry1 = new Entry(feature1, user1);
        entry2 = new Entry(feature1, user2);
        entry3 = new Entry(feature2, user1);

        allEntries.add(entry1);
        allEntries.add(entry2);
        allEntries.add(entry3);

        entriesUser.add(entry1);
        entriesUser.add(entry3);

        entriesFeature.add(entry1);
        entriesFeature.add(entry2);

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

        when(entryRepository.findByFeatureId((long) 1)).thenReturn(entriesFeature);

        assertEquals(entriesFeature, entryController.getEntriesByFeature(feature1));

    }

    @Test
    public void testGetAllVegetarianMeals() {

        when(entryRepository.findByFeatureId(1)).thenReturn(entriesFeature);

        when(userRepository.findByUsername(user1.getUsername())).thenReturn(user1);

        assertEquals(1, entryController.getAllVegetarianMeals(user1));

    }

}

