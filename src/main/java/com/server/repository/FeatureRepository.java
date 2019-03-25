package com.server.repository;

import com.server.entity.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface FeatureRepository extends JpaRepository<Feature, Long> {
    Feature findByFeatureName(String featureName);

    boolean existsByFeatureName(String featureName);
}
