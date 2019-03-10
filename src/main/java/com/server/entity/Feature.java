package com.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "featuresx")
public class Feature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "featureID")
    private long id;

    @Column(name = "feature_name")
    private String featureName;

    @Column(name = "feature_value")
    private int featureValue;

    public Feature() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public int getFeatureValue() {
        return featureValue;
    }

    public void setFeatureValue(int featureValue) {
        this.featureValue = featureValue;
    }
}
