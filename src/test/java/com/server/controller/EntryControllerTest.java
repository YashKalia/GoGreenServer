package com.server.controller;

import com.server.api.Calculator;
import com.server.entity.*;
import com.server.repository.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    @Mock
    BadgesEarnedController badgesEarnedController;

    @Mock
    BadgesEarnedRepository badgesEarnedRepository;

    @Mock
    BadgeRepository badgeRepository;

    @Mock
    Calculator calc;

    private User user1;
    private User user2;

    private Feature feature1 = new Feature();
    private Feature feature2 = new Feature();
    private Feature feature3 = new Feature();
    private Feature feature4 = new Feature();
    private Feature feature5 = new Feature();
    private Feature feature6 = new Feature();
    private Feature feature7 = new Feature();
    private Feature feature8 = new Feature();
    private Feature feature9 = new Feature();
    private Feature feature10 = new Feature();
    private Feature feature11=new Feature();
    private Feature feature12=new Feature();
    

    private RequestUserFeature req1;

    private Entry entry1;
    private Entry entry2;
    private Entry entry3;
    private Entry entry4;
    private Entry entry5;
    private Entry entry6;
    private Entry entry7;
    private Entry entry8;
    private Entry entry9;
    private Entry entry10;
    private Entry entry11;
    private Entry entry12;
    
    List<Entry> allEntries = new ArrayList<>();
    List<Entry> entriesUser = new ArrayList<>();
    List<Entry> entriesFeature1 = new ArrayList<>();
    List<Entry> entriesFeature2 = new ArrayList<>();
    List<Entry> entriesFeature3 = new ArrayList<>();
    List<Entry> entriesFeature4 = new ArrayList<>();
    List<Entry> entriesFeature5 = new ArrayList<>();
    List<Entry> entriesFeature6 = new ArrayList<>();
    List<Entry> entriesFeature7 = new ArrayList<>();
    List<Entry> entriesFeature8 = new ArrayList<>();
    List<Entry> entriesFeature9 = new ArrayList<>();
    List<Entry> entriesFeature10 = new ArrayList<>();
    List<Entry> entriesFeature11 = new ArrayList<>();
    List<Entry> entriesFeature12 = new ArrayList<>();
    

    private Badge badge1 = new Badge();
    private Badge badge2;

    private BadgesEarned badgesEarned1 = new BadgesEarned();
    private BadgesEarned badgesEarned2;
    private BadgesEarned badgesEarned3;

    List<BadgesEarned> allBadges = new ArrayList<>();
    List<BadgesEarned> userBadges = new ArrayList<>();

    @Before
    public void setup() {

        badgesEarnedController = new BadgesEarnedController();

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
        
        feature7.setId(7);
        feature7.setFeatureName("Washing your clothes with cold water");
        feature7.setPoints(3);
        feature7.setCo2(0.7);
        
        feature8.setId(8);
        feature8.setFeatureName("Eating a vegan meal");
        feature8.setPoints(15);
        feature8.setCo2(1);
        
        feature9.setId(9);
        feature9.setFeatureName("Planting a tree");
        feature9.setPoints(5);
        feature9.setCo2(5);
        
        feature10.setId(10);
        feature10.setFeatureName("Recycling waste");
        feature10.setPoints(5);
        feature10.setCo2(5);
        
        feature11.setId(11);
        feature11.setFeatureName("Buying second-hand clothing");
        feature11.setPoints(20);
        feature11.setCo2(0.8);
        
        feature12.setId(12);
        feature12.setFeatureName("Air-drying your clothes");
        feature12.setPoints(10);
        feature12.setCo2(1);
        
        req1 = new RequestUserFeature(feature1, user1, 1);

        entry1 = new Entry(feature1, user1);
        entry1.setDate(new Date());
        entry2 = new Entry(feature1, user2);
        entry3 = new Entry(feature2, user1);
        entry3.setDate(new Date());
        entry4 = new Entry(feature3, user2);
        entry5 = new Entry(feature4, user1);
        entry6 = new Entry(feature5, user2);
        entry7 = new Entry(feature7, user1);
        entry8 =new Entry(feature8,user1);
        entry9=new Entry(feature9,user1);
        entry10=new Entry(feature10,user1);
        entry11=new Entry(feature11,user1);
        entry12=new Entry(feature12,user1);

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
        entriesFeature7.add(entry7);
        entriesFeature8.add(entry8);
        entriesFeature9.add(entry9);
        entriesFeature10.add(entry10);
        entriesFeature11.add(entry11);
        entriesFeature12.add(entry12);
        

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

        badgesEarned3 = new BadgesEarned(badge2, user1);
        badgesEarned3.setId(3);

        allBadges.add(badgesEarned1);
        allBadges.add(badgesEarned2);
        allBadges.add(badgesEarned3);

        userBadges.add(badgesEarned1);
        userBadges.add(badgesEarned3);

    }

    @Test
    public void testGetAllEntries() {

        when(entryRepository.findAll()).thenReturn(allEntries);

        assertEquals(allEntries, entryController.getAllEntries());

    }

    @Test
    public void testAddEntryNoUsername() {

        when(userRepository.existsByUsername(req1.getUser().getUsername())).thenReturn(false);

        assertEquals("User " + req1.getUser().getUsername() + " does not exist!", entryController.addEntry(req1));

    }

    @Test
    public void testAddEntryWrongFeatureName() {

        when(userRepository.existsByUsername(req1.getUser().getUsername())).thenReturn(true);

        when(featureRepository.existsByFeatureName(req1.getFeature().getFeatureName())).thenReturn(false);

        assertEquals("Eating a vegetarian meal" + " is not a valid feature!",entryController.addEntry(req1));
    }

    @Test
    public void testAddingEntryCorrectly() {

        List<Badge> badges = new ArrayList();

        when(userRepository.existsByUsername(req1.getUser().getUsername())).thenReturn(true);

        when(featureRepository.existsByFeatureName(req1.getFeature().getFeatureName())).thenReturn(true);

        when(entryRepository.findByUserId(user1.getId())).thenReturn(entriesUser);

        when(featureRepository.findByFeatureName(feature1.getFeatureName())).thenReturn(feature1);

        when(userRepository.findByUsername(user1.getUsername())).thenReturn(user1);

        assertEquals("Entry added successfully",entryController.addEntry(req1));

    }

    @Test
    public void testGetEntriesByUsername() {

        when(userRepository.findByUsername(user1.getUsername())).thenReturn(user1);

        when(entryRepository.findByUserId((long) 1)).thenReturn(entriesUser);

        assertEquals(entriesUser, entryController.getEntriesByUsername(user1.getUsername()));

    }

    @Test
    public void testGetEntriesByFeature() {

        when(featureRepository.findByFeatureName(feature1.getFeatureName())).thenReturn(feature1);

        when(entryRepository.findByFeatureId((long) 1)).thenReturn(entriesFeature1);

        assertEquals(entriesFeature1, entryController.getEntriesByFeature(feature1.getFeatureName()));

    }

    @Test
    public void testGetAllVegetarianMeals() {

        when(entryRepository.findByFeatureId(1)).thenReturn(entriesFeature1);

        when(userRepository.findByUsername(user1.getUsername())).thenReturn(user1);

        assertEquals(1, entryController.getAllVegetarianMeals(user1.getUsername()));

    }

    @Test
    public void testGetAllLocalProduce() {

        when(entryRepository.findByFeatureId(2)).thenReturn(entriesFeature2);

        when(userRepository.findByUsername(user1.getUsername())).thenReturn(user1);

        assertEquals(1, entryController.getAllLocalProduce(user1.getUsername()));

    }

    @Test
    public void testGetAllBikeRides() {

        when(entryRepository.findByFeatureId(3)).thenReturn(entriesFeature3);

        when(userRepository.findByUsername(user2.getUsername())).thenReturn(user2);

        assertEquals(1, entryController.getAllBikeRides(user2.getUsername()));

    }
    
    @Test
    public void testgetcoldwashnumber() {
    	when(entryRepository.findByFeatureId(7)).thenReturn(entriesFeature7);
    	
    	when(userRepository.findByUsername(user1.getUsername())).thenReturn(user1);
    	
    	assertEquals(1,entryController.getcoldwashnumber(user1.getUsername()));
    }
    
    @Test
    public void testgetveganmeal() {
       when(entryRepository.findByFeatureId(8)).thenReturn(entriesFeature8);
    	
    	when(userRepository.findByUsername(user1.getUsername())).thenReturn(user1);
    	
    	assertEquals(1,entryController.getveganmeal(user1.getUsername())); 
    }
    
    @Test
    public void testgettreesplanted() {
       when(entryRepository.findByFeatureId(9)).thenReturn(entriesFeature9);
    	
    	when(userRepository.findByUsername(user1.getUsername())).thenReturn(user1);
    	
    	assertEquals(1,entryController.gettreesplanted(user1.getUsername())); 
    }
    
    @Test
    public void testgetrecycledtimes() {
       when(entryRepository.findByFeatureId(10)).thenReturn(entriesFeature10);
    	
    	when(userRepository.findByUsername(user1.getUsername())).thenReturn(user1);
    	
    	assertEquals(1,entryController.getrecycledtimes(user1.getUsername())); 
    }
    
    @Test
    public void testgetsecondhand() {
    when(entryRepository.findByFeatureId(11)).thenReturn(entriesFeature11);
    	
    	when(userRepository.findByUsername(user1.getUsername())).thenReturn(user1);
    	
    	assertEquals(1,entryController.getsecondhand(user1.getUsername()));  
    }
    
    @Test
    public void testgethangdrying() {
      when(entryRepository.findByFeatureId(12)).thenReturn(entriesFeature12);
    	
    	when(userRepository.findByUsername(user1.getUsername())).thenReturn(user1);
    	
    	assertEquals(1,entryController.gethangdrying(user1.getUsername()));  
    }
    
   
    @Test
    public void testGetAllPublicTransport() {

        when(entryRepository.findByFeatureId(4)).thenReturn(entriesFeature4);

        when(userRepository.findByUsername(user1.getUsername())).thenReturn(user1);

        assertEquals(1, entryController.getAllPublicTransport(user1.getUsername()));

    }

    @Test
    public void testGetAllLoweringTemperature() {

        when(entryRepository.findByFeatureId(5)).thenReturn(entriesFeature5);

        when(userRepository.findByUsername(user2.getUsername())).thenReturn(user2);

        assertEquals(1, entryController.getAllLoweringTemperature(user2.getUsername()));

    }

    @Test
    public void testGetAllSolarPanels() {

        when(entryRepository.findByFeatureId(6)).thenReturn(entriesFeature6);

        when(userRepository.findByUsername(user1.getUsername())).thenReturn(user1);

        assertEquals(1, entryController.getAllSolarPanels(user1.getUsername()));

    }

    @Test
    public void testGetEntriesByUserAndFeature() {

        when(entryRepository.findByFeatureId(1)).thenReturn(entriesFeature1);

        when(userRepository.findByUsername(user1.getUsername())).thenReturn(user1);

        assertEquals(1, entryController.getEntriesByUserAndFeature(user1.getUsername(), 1));

    }

    @Test
    public void testGetTotalCo2() {

        when(userRepository.findByUsername(user1.getUsername())).thenReturn(user1);

        when(entryRepository.findByUserId(1)).thenReturn(entriesUser);

        assertTrue(5.5 == entryController.getTotalCo2(user1.getUsername()));

    }

    @Test
    public void testGetWeekCo2AllMatch() {

        when(userRepository.findByUsername(user1.getUsername())).thenReturn(user1);

        when(entryRepository.findByUserId(1)).thenReturn(entriesUser);

        String w = new SimpleDateFormat("w").format(new java.util.Date());

        assertTrue(5.5 == entryController.getWeekCo2(user1.getUsername(), w));

    }

    @Test
    public void testGetWeekCo2NoMatch() throws ParseException {

        when(userRepository.findByUsername(user1.getUsername())).thenReturn(user1);

        entriesUser.get(0).setDate(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-01"));
        entriesUser.get(1).setDate(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-02"));

        when(entryRepository.findByUserId(1)).thenReturn(entriesUser);

        String w = new SimpleDateFormat("w").format(new java.util.Date());

        assertTrue(0 == entryController.getWeekCo2(user1.getUsername(), w));

    }

    @Test
    public void testGetMonthCo2AllMatch() {

        when(userRepository.findByUsername(user1.getUsername())).thenReturn(user1);

        when(entryRepository.findByUserId(1)).thenReturn(entriesUser);

        String m = new SimpleDateFormat("MM").format(new java.util.Date());

        assertTrue(5.5 == entryController.getMonthCo2(user1.getUsername(), m));

    }

    @Test
    public void testGetMonthCo2NoMatch() throws ParseException {

        when(userRepository.findByUsername(user1.getUsername())).thenReturn(user1);

        entriesUser.get(0).setDate(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-01"));
        entriesUser.get(1).setDate(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-02"));

        when(entryRepository.findByUserId(1)).thenReturn(entriesUser);

        String m = new SimpleDateFormat("MM").format(new java.util.Date());

        assertTrue(0 == entryController.getMonthCo2(user1.getUsername(), m));

    }

    @Test
    public void testCheckBadgesAddInitialBadge() {

        when(entryRepository.findByUserId(user1.getId())).thenReturn(new ArrayList<Entry>());

        assertTrue(1 == entryController.checkBadges(user1, feature1));

    }

    @Test
    public void testCheckBadgesAddBronzeBadge() {

        List<Entry> ent = new ArrayList<>();
        Entry e = new Entry(feature1, user1);

        for (int i = 1; i < 5; i++) {

            e.setId(i);
            ent.add(e);

        }

        when(entryRepository.findByUserId(user1.getId())).thenReturn(ent);

        assertTrue(2 == entryController.checkBadges(user1, feature1));

    }

    @Test
    public void testCheckBadgesAddSilverBadge() {

        List<Entry> ent = new ArrayList<>();
        Entry e = new Entry(feature1, user1);

        for (int i = 1; i < 20; i++) {

            e.setId(i);
            ent.add(e);

        }

        when(entryRepository.findByUserId(user1.getId())).thenReturn(ent);

        assertTrue(3 == entryController.checkBadges(user1, feature1));

    }

    @Test
    public void testCheckBadgesAddGoldBadge() {

        List<Entry> ent = new ArrayList<>();
        Entry e = new Entry(feature1, user1);

        for (int i = 1; i < 50; i++) {

            e.setId(i);
            ent.add(e);

        }

        when(entryRepository.findByUserId(user1.getId())).thenReturn(ent);

        assertTrue(4 == entryController.checkBadges(user1, feature1));

    }

    @Test
    public void testCheckBadgesNoBadgeAdded() {

        List<Entry> ent = new ArrayList<>();
        Entry e = new Entry(feature1, user1);
        Entry e2 = new Entry(feature2, user1);
        ent.add(e2);

        for (int i = 1; i < 3; i++) {

            e.setId(i);
            ent.add(e);

        }

        when(entryRepository.findByUserId(user1.getId())).thenReturn(ent);

        assertTrue(0 == entryController.checkBadges(user1, feature1));

    }

    @Test
    public void testGetCo2ApiProduce() {

        Feature newFe = new Feature();
        newFe.setId(2);
        newFe.setFeatureName("Buying local produce");
        newFe.setPoints(10);
        entryController.getCo2Api(newFe);

        assertTrue(feature2.getCo2() != newFe.getCo2());

    }

    @Test
    public void testGetCo2ApiBike() {

        Feature newFe = new Feature();
        newFe.setId(3);
        newFe.setFeatureName("Using bike instead of car");
        newFe.setPoints(20);
        entryController.getCo2Api(newFe);

        assertTrue(feature3.getCo2() != newFe.getCo2());

    }

    @Test
    public void testGetCo2ApiPublic() {

        Feature newFe = new Feature();
        newFe.setId(4);
        newFe.setFeatureName("Using public transport instead of car");
        newFe.setPoints(15);
        entryController.getCo2Api(newFe);

        assertTrue(feature4.getCo2() != newFe.getCo2());

    }

    @Test
    public void testGetCo2ApiTemperature() {

        Feature newFe = new Feature();
        newFe.setId(5);
        newFe.setFeatureName("Lowering the temperature of your home");
        newFe.setPoints(10);
        entryController.getCo2Api(newFe);

        assertTrue(feature5.getCo2() != newFe.getCo2());

    }

    @Test
    public void testGetCo2ApiSolar() {

        Feature newFe = new Feature();
        newFe.setId(6);
        newFe.setFeatureName("Installing solar panels");
        newFe.setPoints(100);
        entryController.getCo2Api(newFe);

        assertTrue(feature6.getCo2() != newFe.getCo2());

    }

    @Test
    public void testGetCo2ApiColdWater() {

        Feature newFe = new Feature();
        newFe.setId(7);
        newFe.setFeatureName("Washing your clothes with cold water");
        newFe.setPoints(3);
        entryController.getCo2Api(newFe);

        assertTrue(feature7.getCo2() != newFe.getCo2());

    }

    @Test
    public void testGetCo2ApiVegan() {

        Feature newFe = new Feature();
        newFe.setId(8);
        newFe.setFeatureName("Eating a vegan meal");
        newFe.setPoints(15);
        entryController.getCo2Api(newFe);

        assertTrue(feature8.getCo2() != newFe.getCo2());

    }

    @Test
    public void testGetCo2ApiTree() {

        Feature newFe = new Feature();
        newFe.setId(9);
        newFe.setFeatureName("Planting a tree");
        newFe.setPoints(5);
        entryController.getCo2Api(newFe);

        assertTrue(feature9.getCo2() != newFe.getCo2());

    }

    @Test
    public void testGetCo2ApiRecycling() {

        Feature newFe = new Feature();
        newFe.setId(10);
        newFe.setFeatureName("Recycling waste");
        newFe.setPoints(5);
        entryController.getCo2Api(newFe);

        assertTrue(feature10.getCo2() != newFe.getCo2());

    }

    @Test
    public void testGetCo2ApiClothing() {

        Feature newFe = new Feature();
        newFe.setId(11);
        newFe.setFeatureName("Buying second-hand clothing");
        newFe.setPoints(20);
        entryController.getCo2Api(newFe);

        assertTrue(feature11.getCo2() != newFe.getCo2());

    }

    @Test
    public void testGetCo2ApiAirDrying() {

        Feature newFe = new Feature();
        newFe.setId(12);
        newFe.setFeatureName("Air-drying your clothes");
        newFe.setPoints(10);
        entryController.getCo2Api(newFe);

        assertTrue(feature12.getCo2() != newFe.getCo2());

    }

    @Test
    public void testGetCo2ApiNoMatch() {

        Feature newFe = new Feature();
        newFe.setId(13);
        newFe.setFeatureName("Travel by train instead of plane");
        newFe.setPoints(50);
        entryController.getCo2Api(newFe);

        assertTrue(feature12.getCo2() != newFe.getCo2());

    }

}

