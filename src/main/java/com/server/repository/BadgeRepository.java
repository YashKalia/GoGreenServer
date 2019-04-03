package com.server.repository;

import com.server.entity.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface BadgeRepository extends JpaRepository<Badge, Long>  {
    Badge findByBadgeName(String badgeName);

    Badge findById(long badgeId);
}
