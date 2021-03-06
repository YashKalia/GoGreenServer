package com.server.repository;

import com.server.entity.Friends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
@EnableJpaRepositories
public interface FriendsRepository extends JpaRepository<Friends, Long> {
    Set<Friends> findByUserId(long id);

    Set<Friends> findByFriendId(long id);
}
