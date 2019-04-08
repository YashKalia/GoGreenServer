package com.server.controller;

import com.server.entity.Badge;
import com.server.repository.BadgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/badges")
public class BadgeController {

    @Autowired
    BadgeRepository badgeRepository;

    protected List<Badge> getAllBadges() {

        return badgeRepository.findAll();
    }

    /**
     * This method gets you all the badges available.
     *
     * @return a set with name of all badges
     */
    @GetMapping(value = "/getallbadges")
    protected Set<String> getAllBadgesPublic() {
        List<Badge> badges = getAllBadges();
        Set<String> result = new HashSet<>();
        for (Badge b : badges) {
            result.add(b.getBadgeName());
        }
        return result;
    }

}
