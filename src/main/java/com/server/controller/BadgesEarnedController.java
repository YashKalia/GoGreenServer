package com.server.controller;

import com.server.entity.Badge;
import com.server.entity.BadgesEarned;
import com.server.entity.Feature;
import com.server.entity.RequestUserFeature;
import com.server.entity.User;
import com.server.repository.BadgeRepository;
import com.server.repository.BadgesEarnedRepository;
import com.server.repository.FeatureRepository;
import com.server.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/badgesearned")
public class BadgesEarnedController {

    @Autowired
    BadgesEarnedRepository badgesEarnedRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FeatureRepository featureRepository;

    @Autowired
    BadgeRepository badgeRepository;

    /**
     * The method creates and adds new BadgeEarned to the database.
     *
     * @param requestUserFeature the custom entity containing a username, feature, and badgeCode.
     * @return the list of all badges
     */
    protected List<BadgesEarned> addBadge(@RequestBody RequestUserFeature requestUserFeature) {

        User user = requestUserFeature.getUser();
        User userF = userRepository.findByUsername(user.getUsername());
        Feature feature = requestUserFeature.getFeature();
        Feature featureF = featureRepository.findByFeatureName(feature.getFeatureName());
        int badgeCode = requestUserFeature.getBadgeCode();
        long id = featureF.getId() * 10 + badgeCode;
        Badge badge = badgeRepository.findById(id);

        BadgesEarned badgesEarned = new BadgesEarned(badge, userF);
        badgesEarnedRepository.save(badgesEarned);

        return badgesEarnedRepository.findAll();

    }

    /**
     * This method finds all the badges you have ever earned.
     *
     * @param username the username whose badges to get
     * @return a list of all badges you have ever earned
     */
    @GetMapping(value = "/getmybadges/{username}")
    protected List<BadgesEarned> getMyBadges(@PathVariable String username) {
        User user = userRepository.findByUsername(username);
        return badgesEarnedRepository.findByUserId(user.getId());
    }

}
