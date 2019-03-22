package com.server.controller;

import com.server.entity.Entry;
import com.server.entity.Feature;
import com.server.entity.RequestUserFeature;
import com.server.entity.User;
import com.server.repository.EntryRepository;
import com.server.repository.FeatureRepository;
import com.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/entries")
public class EntryController {

    @Autowired
    EntryRepository entryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FeatureRepository featureRepository;

    /**
     * RequestUserFeature is a custom class made of 1 user and 1 feature.
     * It is necessary because 1 user and 1 feature cannot both be sent under 1 request.
     * The method finds the full details of the user and the feature from the database
     * (including details that aren't obvious to the client such as the id is NOT required)
     * (only a username and the name of the feature are)
     * then creates a new entity with those parameters and adds it to the database.
     * The list of all entries is returned for testing and displaying purposes.
     *
     * @param re the custom entity
     * @return the list of all features
     */
    @PostMapping(value = "/add")
    public List<Entry> addEntry(@RequestBody RequestUserFeature re) {
        Feature feature = re.getFeature();
        User user = re.getUser();
        User us = userRepository.findByUsername(user.getUsername());
        Feature fe = featureRepository.findByFeatureName(feature.getFeatureName());
        Entry en = new Entry(fe, us);
        entryRepository.save(en);
        return entryRepository.findAll();
    }

    @PostMapping(value = "/getbyuser")
    public List<Entry> getEntriesByUsername(@RequestBody User user) {
        long id = userRepository.findByUsername(user.getUsername()).getId();
        return entryRepository.findByUserId(id);
    }

    @PostMapping(value = "/getbyfeature")
    public List<Entry> getEntriesByFeature(@RequestBody Feature feature) {
        long id = featureRepository.findByFeatureName(feature.getFeatureName()).getId();
        return entryRepository.findByFeatureId(id);
    }

    /**
     * Checks the number of vegetarian meals a certain user has had.
     * @param user The parameter sent through a request, the user whose entries should be retrieved
     * @return the integer as the number of entries that contain a meal and the given user
     */
    @PostMapping(value = "/getvegetarianmeals")
    public int getAllVegetarianMeals(@RequestBody User user) {
        return getEntriesByUserAndFeature(user, 1);
    }

    /**
     * Retrieves how many times a user has bought local produce.
     * @param user The user whose entries should be retrieved
     * @return int Total number of times the specified user has used a bike instead of a car
     */
    @PostMapping(value = "/getlocalproduce")
    public int getAllLocalProduce(@RequestBody User user) {
        return getEntriesByUserAndFeature(user, 2);
    }

    /**
     * Retrieves how many times a user has used a bike instead of a car.
     * @param user The user whose entries should be retrieved
     * @return int Total number of times the specified user has used a bike instead of a car
     */
    @PostMapping(value = "/getbikerides")
    public int getAllBikeRides(@RequestBody User user) {
        return getEntriesByUserAndFeature(user, 3);
    }

    /**
     * Retrieves how many times a user has used public transport instead of a car.
     * @param user The user whose entries should be retrieved
     * @return int Total number of times the user has used public transport instead of a car
     */
    @PostMapping(value = "/getpublictransport")
    public int getAllPublicTransport(@RequestBody User user) {
        return getEntriesByUserAndFeature(user, 4);
    }

    /**
     * Retrieves how many times a user has lowered the temperature of their home.
     * @param user The user whose entries should be retrieved
     * @return int Total number of times user has lowered the house temperature
     */
    @PostMapping(value = "/getloweringtemperature")
    public int getAllLoweringTemperature(@RequestBody User user) {
        return getEntriesByUserAndFeature(user, 5);
    }

    /**
     * Retrieves how many times a user has installed a solar panel.
     * @param user The user whose entries should be retrieved
     * @return int Total number of times the specified user has installed a solar panel
     */
    @PostMapping(value = "/getsolarpanels")
    public int getAllSolarPanels(@RequestBody User user) {
        return getEntriesByUserAndFeature(user, 6);
    }

    /**
     * Retrieves all entries from a user that contains a certain feature.
     * @param user featureId The user whose entries should be retrieved, the feature ID
     * @return int Total number of times the user has added an entry with the specified feature
     */
    public int getEntriesByUserAndFeature(User user, int featureId) {
        long userId = userRepository.findByUsername(user.getUsername()).getId();
        List<Entry> entries = entryRepository.findByFeatureId(featureId);
        int total = 0;
        for (Entry entry : entries) {
            if (entry.getUser().getId() == userId) {
                total++;
            }
        }
        return total;
    }

    /**
     * Retrieves how much CO2 the user has saved in total.
     * @param user The user whose CO2 emissions should be calculated
     * @return int Total amount (in grams) of CO2 emissions that the user has saved
     */
    @PostMapping(value = "/gettotalco2")
    public double getTotalCo2(@RequestBody User user) {

        long userId = userRepository.findByUsername(user.getUsername()).getId();
        List<Entry> entries = entryRepository.findByUserId(userId);
        double total = 0;
        for (Entry entry : entries) {
            total += entry.getFeature().getCo2();
        }
        return total;

    }

    @GetMapping(value = "/get")
    public List<Entry> getAllEntries() {
        return entryRepository.findAll();
    }

}
