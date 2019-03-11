package com.server.controller;

import com.server.entity.Feature;
import com.server.repository.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/features")
public class FeatureController {

    @Autowired
    FeatureRepository featureRepository;

    @PostMapping(value = "/add")
    public List<Feature> addFeature(@RequestBody Feature feature) {
        featureRepository.save(feature);
        return featureRepository.findAll();
    }

    @GetMapping(value = "/getall")
    public List<Feature> getAllFeatures() {
        return featureRepository.findAll();
    }

    /**
     * Deletes the feature with the given ID. Will be secured in the future.
     * @param id the id of the feature to be removed
     * @return the list of all features after the feature was deleted (or not if doesn't exist)
     */
    @DeleteMapping(value = "/delete/{id}")
    public List<Feature> deleteFeatureById(Long id) {
        if (featureRepository.findById(id) != null) {
            featureRepository.deleteById(id);
        }
        return featureRepository.findAll();
    }

}
