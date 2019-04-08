package com.server.controller;

import com.server.entity.Badge;
import com.server.repository.BadgeRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BadgeControllerTest {

    @InjectMocks
    BadgeController badgeController;

    @Mock
    BadgeRepository badgeRepository;

    private List<Badge> badges = new ArrayList<>();
    private Set<String> publicBadges = new HashSet<>();
    private Badge badge1 = new Badge();
    private Badge badge2;
    private Badge badge3 = new Badge();

    @Before
    public void setup() {

        badge1.setId(11);
        badge1.setBadgeName("First Vegetarian Meal Eaten");
        badge1.setPointsNeeded(10);

        badge2 = new Badge("Bronze Vegetarian Meals", 50);
        badge2.setId(12);

        badge3 = new Badge("First Solar Panel Installed", 100);
        badge3.setId(61);

        badges.add(badge1);
        badges.add(badge2);
        badges.add(badge3);

        publicBadges.add(badge1.getBadgeName());
        publicBadges.add(badge2.getBadgeName());
        publicBadges.add(badge3.getBadgeName());

    }

    @Test
    public void testGetAllBadges() {

        when(badgeRepository.findAll())
                .thenReturn(badges);

        assertEquals(badges, badgeController.getAllBadges());

    }


    @Test
    public void testGetAllBadgesPublic() {

        when(badgeRepository.findAll())
                .thenReturn(badges);

        assertEquals(publicBadges, badgeController.getAllBadgesPublic());

    }
}
