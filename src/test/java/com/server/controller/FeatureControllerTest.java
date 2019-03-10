package com.server.controller;

import com.server.entity.Feature;
import com.server.entity.User;
import com.server.repository.FeatureRepository;
import com.server.repository.UserRepository;

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
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={FeatureController.class})
public class FeatureControllerTest {

    private MockMvc mvc;

    @Autowired
    @MockBean
    FeatureController featureController;

    @MockBean
    FeatureRepository featureRepository;

    private List<Feature> features;
    private Feature feature1;
    private Feature feature2;

    String feature1AsJSON = "{\n" +
            "\t\"featureName\": \"Eating a vegetarian meal\",\n" +
            "\t\"featureValue\": \"10\"\n" +
            "}";

    String feature2AsJSON = "{\n" +
            "\t\"featureName\": \"Riding a bike to work\",\n" +
            "\t\"featureValue\": \"20\"\n" +
            "}";

    @Before
    public void setup() {

        mvc = standaloneSetup(this.featureController).build();

        feature1 = new Feature();
        feature1.setId(1);
        feature1.setFeatureName("Eating a vegetarian meal");
        feature1.setFeatureValue(10);

        feature2 = new Feature();
        feature2.setId(2);
        feature2.setFeatureName("Riding a bike to work");
        feature2.setFeatureValue(20);

        featureController.addFeature(feature1);

        features = featureRepository.findAll();

    }

    @Test
    public void testGetAllFeatures() throws Exception {

        given(featureController.getAllFeatures())
                .willReturn(features);

        mvc.perform(MockMvcRequestBuilders.get("/features/getall")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void testAddFeatureSuccess() throws Exception {

        given(featureController.addFeature(feature2))
                .willReturn(features);


        mvc.perform(MockMvcRequestBuilders.post("/features/add")
                .content(feature2AsJSON)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void testDeleteFeatureSuccess() throws Exception {

        featureController.addFeature(feature2);

        given(featureController.deleteFeatureById((long) 2))
                .willReturn(features);


        mvc.perform(MockMvcRequestBuilders.delete("/features/delete/2")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void testDeleteFeatureFailure() throws Exception {

        given(featureController.deleteFeatureById(null))
                .willReturn(features);

        mvc.perform(MockMvcRequestBuilders.delete("/users/delete/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));

    }
}