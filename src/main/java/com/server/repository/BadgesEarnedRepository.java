package com.server.repository;

import com.server.entity.BadgesEarned;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface BadgesEarnedRepository extends JpaRepository<BadgesEarned,Long> {
    List<BadgesEarned> findByBadgeId(long id);

    List<BadgesEarned> findByUserId(long id);
}
