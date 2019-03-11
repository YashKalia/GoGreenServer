package com.server.controller;

import com.server.entity.Entry;
import com.server.entity.Feature;
import com.server.entity.RequestUserFeature;
import com.server.entity.User;
import com.server.repository.EntryRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={EntryController.class})
public class EntryControllerTest {

    private MockMvc mvc;

    @Autowired
    @MockBean
    EntryController entryController;

    @MockBean
    EntryRepository entryRepository;

    private List<Entry> entries = new ArrayList<>();
    private List<Entry> entries2 = new ArrayList<>();
    private List<Entry> entries3 = new ArrayList<>();
    private User user5;
    private User user2;
    private Feature feature1;
    private Feature feature2;
    private RequestUserFeature req1;
    private RequestUserFeature req2;
    private RequestUserFeature req3;
    private Entry entry1;
    private Entry entry2;
    private Entry entry3;

    String entry2AsJSON = "{\n" +
            "\t\"user\": {\n" +
            "        \"id\": 7,\n" +
            "        \"username\": \"user5\",\n" +
            "        \"password\": \"$2a$10$4TsXEnwW6sCLH7TeXmXMGetfmiDaVBL53F8SJQcDqCIIPPVH2pxKe\",\n" +
            "        \"roles\": []\n" +
            "    },\n" +
            "\t\"feature\": {\n" +
            "        \"id\": 2,\n" +
            "        \"featureName\": \"Riding a bike to work\",\n" +
            "        \"featureValue\": 20\n" +
            "    }\n" +
            "}";

    String user5AsJSON = "{\n" +
            "    \"id\": 7,\n" +
            "    \"username\": \"user5\",\n" +
            "    \"password\": \"$2a$10$4TsXEnwW6sCLH7TeXmXMGetfmiDaVBL53F8SJQcDqCIIPPVH2pxKe\",\n" +
            "    \"roles\": []\n" +
            "}";

    String feature2AsJSON = "{\n" +
            "    \"id\": 2,\n" +
            "    \"featureName\": \"Riding a bike to work\",\n" +
            "    \"featureValue\": 20\n" +
            "}";

    @Before
    public void setup() {

        mvc = standaloneSetup(this.entryController).build();

        user5 = new User("user5", "password");
        user5.setId(7);

        user2 = new User("user2", "password");
        user2.setId(9);

        feature1 = new Feature();
        feature1.setId(1);
        feature1.setFeatureName("Eating a vegetarian meal");
        feature1.setFeatureValue(10);

        feature2 = new Feature();
        feature2.setId(2);
        feature2.setFeatureName("Riding a bike to work");
        feature2.setFeatureValue(20);

        req1 = new RequestUserFeature(feature1, user5);
        req2 = new RequestUserFeature(feature2, user5);
        req3 = new RequestUserFeature(feature1, user2);

        entry1 = new Entry(feature1, user5);
        entry2 = new Entry(feature2, user5);
        entry3 = new Entry(feature1, user2);

        entryController.addEntry(req1);
        entryController.addEntry(req3);

        entries.add(entry1);
        entries2.add(entry1);
        entries2.add(entry3);
        entries3.add(entry2);

    }

    @Test
    public void testGetAllEntries() throws Exception {

        given(entryController.getAllEntries())
                .willReturn(entries);

        mvc.perform(MockMvcRequestBuilders.get("/entries/get")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void testAddEntry() throws Exception {

        given(entryController.addEntry(req2))
                .willReturn(entries);

        mvc.perform(MockMvcRequestBuilders.post("/entries/add")
                .content(entry2AsJSON)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void testGetEntriesByUsername() throws Exception {

        given(entryController.getEntriesByUsername(user5))
                .willReturn(entries2);

        mvc.perform(MockMvcRequestBuilders.post("/entries/getbyuser")
                .content(user5AsJSON)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void testGetEntriesByFeature() throws Exception {

        given(entryController.getEntriesByFeature(feature2))
                .willReturn(entries3);

        mvc.perform(MockMvcRequestBuilders.post("/entries/getbyfeature")
                .content(feature2AsJSON)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}
