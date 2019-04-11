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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    @Autowired
    BadgesEarnedController badgesEarnedController = new BadgesEarnedController();

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
    public String addEntry(@RequestBody RequestUserFeature re) {
        Feature feature = re.getFeature();
        User user = re.getUser();
        User us;
        Feature fe;
        if (userRepository.existsByUsername(user.getUsername())) {
            us = userRepository.findByUsername(user.getUsername());
        } else {
            return "User " + user.getUsername() + " does not exist!";
        }

        if (featureRepository.existsByFeatureName(feature.getFeatureName())) {
            fe = featureRepository.findByFeatureName(feature.getFeatureName());
        } else {
            return feature.getFeatureName()
                    + " is not a valid feature!";
        }
        Entry en = new Entry(fe, us);
        checkBadges(us, fe);
        entryRepository.save(en);
        return "Entry added successfully";
    }

    //@GetMapping(value = "/getbyuser/{username}")
    protected List<Entry> getEntriesByUsername(@PathVariable String username) {
        long id = userRepository.findByUsername(username).getId();
        return entryRepository.findByUserId(id);
    }

    //@GetMapping(value = "/getbyfeature/{feature}")
    protected List<Entry> getEntriesByFeature(@PathVariable String feature) {
        long id = featureRepository.findByFeatureName(feature).getId();
        return entryRepository.findByFeatureId(id);
    }

    /**
     * Checks the number of vegetarian meals a certain user has had.
     *
     * @param username Parameter sent through a request, user whose entries should be retrieved
     * @return the integer as the number of entries that contain a meal and the given user
     */
    @GetMapping(value = "/getvegetarianmeals/{username}")
    public int getAllVegetarianMeals(@PathVariable String username) {
        return getEntriesByUserAndFeature(username, 1);
    }

    /**
     * Retrieves how many times a user has bought local produce.
     *
     * @param username The user whose entries should be retrieved
     * @return int Total number of times the specified user has used a bike instead of a car
     */
    @GetMapping(value = "/getlocalproduce/{username}")
    public int getAllLocalProduce(@PathVariable String username) {
        return getEntriesByUserAndFeature(username, 2);
    }

    /**
     * Retrieves how many times a user has used a bike instead of a car.
     *
     * @param username The user whose entries should be retrieved
     * @return int Total number of times the specified user has used a bike instead of a car
     */
    @GetMapping(value = "/getbikerides/{username}")
    public int getAllBikeRides(@PathVariable String username) {
        return getEntriesByUserAndFeature(username, 3);
    }

    /**
     * Retrieves how many times a user has used public transport instead of a car.
     *
     * @param username The user whose entries should be retrieved
     * @return int Total number of times the user has used public transport instead of a car
     */
    @GetMapping(value = "/getpublictransport/{username}")
    public int getAllPublicTransport(@PathVariable String username) {
        return getEntriesByUserAndFeature(username, 4);
    }
    
    /**
     * Retrieves how many times a user has lowered the temperature of their home.
     *
     * @param username The user whose entries should be retrieved
     * @return int Total number of times user has lowered the house temperature
     */
    @GetMapping(value = "/getloweringtemperature/{username}")
    public int getAllLoweringTemperature(@PathVariable String username) {
        return getEntriesByUserAndFeature(username,5);
    }

    /**
     * Retrieves how many times a user has installed a solar panel.
     *
     * @param username The user whose entries should be retrieved
     * @return int Total number of times the specified user has installed a solar panel
     */
    @GetMapping(value = "/getsolarpanels/{username}")
    public int getAllSolarPanels(@PathVariable String username) {
        return getEntriesByUserAndFeature(username,6);
    }
    
    /**Returns the number of cold washes instead of hot washes the user has done.
     * 
     * @param username The user whose entries should be retrieved.
     * @return the number of cold washes by that user.
     */
    @GetMapping(value = "/coldwash/{username}")
    public int getcoldwashnumber(@PathVariable String username) {
    	return getEntriesByUserAndFeature(username,7);
    }
    
    /**Returns the number of low flow showerheads installed.
     * 
     * @param username username The user whose entries should be retrieved.
     * @return the number of low flow showers installed by the user.
     */
    @GetMapping(value = "/veganmeal/{username}")
    public int getveganmeal(@PathVariable String username) {
    	return getEntriesByUserAndFeature(username,8);
    }
    
    /**Returns the number of trees planted by user.
     * 
     * @param username  username username The user whose entries should be retrieved.
     * @return the number of trees planted by the user.
     */
    @GetMapping(value="/planttree/{username}")
    public int gettreesplanted(@PathVariable String username) {
    	return  getEntriesByUserAndFeature(username,9);
    }
    
    /**Returns the number of times user has recycled his waste.
     * 
     * @param username username username The user whose entries should be retrieved.
     * @return int containing number of times recycled.
     */
    @GetMapping(value="/recycle/{username}")
    public int getrecycledtimes(@PathVariable String username){
    	return getEntriesByUserAndFeature(username,10);
    }
    
    /**
     * Retrieves all entries from a user that contains a certain feature.
     *
     * @param username featureId The user whose entries should be retrieved, the feature ID
     * @return int Total number of times the user has added an entry with the specified feature
     */
    protected int getEntriesByUserAndFeature(String username, int featureId) {
        long userId = userRepository.findByUsername(username).getId();
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
     *
     * @param username The user whose CO2 emissions should be calculated
     * @return int Total amount (in grams) of CO2 emissions that the user has saved
     */
    @GetMapping(value = "/gettotalco2/{username}")
    public double getTotalCo2(@PathVariable String username) {
        long userId = userRepository.findByUsername(username).getId();
        List<Entry> entries = entryRepository.findByUserId(userId);
        double total = 0;
        for (Entry entry : entries) {
            total += entry.getFeature().getCo2();
        }
        return total;
    }

    /**
     * Retrieves how much CO2 the user has saved in a certain week.
     *
     * @param username The user whose CO2 emissions should be calculated
     * @param week     The number of the week (1-52) in the Gregorian calendar
     * @return int Total amount (in grams) of CO2 emissions that the user has saved
     */
    @GetMapping(value = "/getweekco2/{username}/{week}")
    public double getWeekCo2(@PathVariable("username") String username,
                             @PathVariable("week") String week) {
        long userId = userRepository.findByUsername(username).getId();
        List<Entry> entries = entryRepository.findByUserId(userId);
        double total = 0;
        for (Entry entry : entries) {
            String entryWeek = new SimpleDateFormat("w").format(entry.getDate());
            if (entryWeek.equals(week)) {
                total += entry.getFeature().getCo2();
            }
        }
        return total;
    }

    /**
     * Retrieves how much CO2 the user has saved in a certain month.
     *
     * @param username The user whose CO2 emissions should be calculated
     * @param month    The number of the month (01-12) in the Gregorian calendar
     * @return int Total amount (in grams) of CO2 emissions that the user has saved
     */
    @GetMapping(value = "/getmonthco2/{username}/{month}")
    public double getMonthCo2(@PathVariable("username") String username,
                              @PathVariable("month") String month) {
        long userId = userRepository.findByUsername(username).getId();
        List<Entry> entries = entryRepository.findByUserId(userId);
        double total = 0;
        for (Entry entry : entries) {
            String entryMonth = new SimpleDateFormat("MM").format(entry.getDate());
            if (entryMonth.equals(month)) {
                total += entry.getFeature().getCo2();
            }
        }
        return total;
    }

    protected List<Entry> getAllEntries() {
        return entryRepository.findAll();
    }

    /**
     * This method automates adding badges for users in the database whenever a new entry is added.
     * 1 = initial badge
     * 2 = bronze badge
     * 3 = silver badte
     * 4 = gold badge
     * 0 = no badge
     * @param user    The user who might earn a badge
     * @param feature The feature for which the badge might be earned
     * @return int Code for which badge type was earned:
     */
    protected int checkBadges(User user, Feature feature) {
        List<Entry> allEntries = entryRepository.findByUserId(user.getId());
        List<Entry> featureEntries = new ArrayList<>();
        for (Entry entry : allEntries) {
            if (entry.getFeature().getId() == feature.getId()) {
                featureEntries.add(entry);
            }
        }
        if (featureEntries.size() == 0) {
            badgesEarnedController.addBadge(new RequestUserFeature(feature, user, 1));
            return 1;
        } else if (featureEntries.size() == 4) {
            badgesEarnedController.addBadge(new RequestUserFeature(feature, user, 2));
            return 2;
        } else if (featureEntries.size() == 19) {
            badgesEarnedController.addBadge(new RequestUserFeature(feature, user, 3));
            return 3;
        } else if (featureEntries.size() == 49) {
            badgesEarnedController.addBadge(new RequestUserFeature(feature, user, 4));
            return 4;
        }
        return 0;
    }
    
     

}
