package com.server.controller;

import com.server.entity.*;
import com.server.repository.*;
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
public class BadgesEarnedControllerTest {

    @InjectMocks
    BadgesEarnedController badgesEarnedController;

    @Mock
    BadgesEarnedRepository badgesEarnedRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    FeatureRepository featureRepository;

    @Mock
    BadgeRepository badgeRepository;

    private User user1;
    private User user2;

    private Feature feature1 = new Feature();
    private Feature feature2 = new Feature();

    private RequestUserFeature req1;

    private Badge badge1 = new Badge();
    private Badge badge2;

    private BadgesEarned badgesEarned1 = new BadgesEarned();
    private BadgesEarned badgesEarned2;

    List<BadgesEarned> allBadges = new ArrayList<>();
    List<BadgesEarned> userBadges = new ArrayList<>();

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

        req1 = new RequestUserFeature(feature1, user1, 1);

        badge1.setId(11);
        badge1.setBadgeName("First Vegetarian Meal Eaten");
        badge1.setPointsNeeded(10);

        badge2 = new Badge("First Local Produce Bought", 10);
        badge2.setId(21);

        badgesEarned1.setId(1);
        badgesEarned1.setBadge(badge1);
        badgesEarned1.setUser(user1);

        badgesEarned2 = new BadgesEarned(badge2, user2);
        badgesEarned2.setId(2);

        allBadges.add(badgesEarned1);
        allBadges.add(badgesEarned2);

        userBadges.add(badgesEarned1);

    }

    @Test
    public void testAddBadge() {

        when(userRepository.findByUsername(user1.getUsername())).thenReturn(user1);

        when(featureRepository.findByFeatureName(feature1.getFeatureName())).thenReturn(feature1);

        when(badgeRepository.findById(badge1.getId())).thenReturn(badge1);

        when(badgesEarnedRepository.findAll()).thenReturn(allBadges);

        assertEquals(allBadges, badgesEarnedController.addBadge(req1));

    }

    @Test
    public void testGetBadgesByUsername() {

        when(userRepository.findByUsername(user1.getUsername())).thenReturn(user1);

        when(badgesEarnedRepository.findByUserId(user1.getId())).thenReturn(userBadges);

        assertEquals(userBadges, badgesEarnedController.getMyBadges("user1"));

    }

}
