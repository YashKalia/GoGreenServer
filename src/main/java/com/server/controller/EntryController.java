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
     * @param re the custom entity
     * @return the list of all features
     */
    @PostMapping(value = "/add")
    public List<Entry> addEntry(@RequestBody RequestUserFeature re) {
        Feature feature = re.getFeature();
        User user = re.getUser();
        User us = userRepository.findByUsername(user.getUsername());
        Feature fe = featureRepository.findByFeatureName(feature.getFeatureName());
        Entry en = new Entry(fe,us);
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

    @PostMapping(value = "/getvegetarianmeals")
    public int getAllVegetarianMeals(@RequestBody User user) {
        List<Entry> entries = entryRepository.findByFeatureId(1);
        int i=0;
        long id = userRepository.findByUsername(user.getUsername()).getId();
        for(Entry e: entries) {
            if(e.getUser().getId()==id)
                i++;
        }
        return i;
    }

    @GetMapping(value = "/get")
    public List<Entry> getAllEntries() {
        return entryRepository.findAll();
    }

}
