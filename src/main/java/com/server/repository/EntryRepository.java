package com.server.repository;

import com.server.entity.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface EntryRepository extends JpaRepository<Entry,Long> {
    List<Entry> findByFeatureId(long id);

    List<Entry> findByUserId(long id);
}
