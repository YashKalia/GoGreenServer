package com.server.controller;

import com.server.entity.Friends;
import com.server.entity.User;
import com.server.repository.FriendsRepository;
import com.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/friends")
public class FriendsController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FriendsRepository friendsRepository;

    /**
     * This method adds a new user relation.
     * This works as follows:
     * 1. Both of the usernames are searched for in the repository.
     * If either doesn't exist, a new exception is thrown.
     * If the user tries to add himself as a friend, a new exception is thrown.
     * 2. A list of all friends of the first user is retrieved.
     * 3. The set friends pairs are scanned, and if the user has already added that friend,
     * a new error is thrown.
     * 4. Otherwise, the pair is added to the database.
     *
     * @param friends The pairing of the user who sends the request and the user they want to add
     * @return a boolean to indicate whether or not the friend has been added successfully
     */
    @PostMapping(value = "/add")
    public String addFriend(@RequestBody Friends friends) {
        System.out.println(friends);
        User user = userRepository.findByUsername(friends.getUser().getUsername());
        if (!userRepository.existsByUsername(friends.getFriend().getUsername())) {
            return friends.getFriend().getUsername() + " does not exist!";
        }
        User friend = userRepository.findByUsername(friends.getFriend().getUsername());

        if (friends.getFriend().getUsername().equals(friends.getUser().getUsername())) {
            return "You can't be your own friend";
        }

        Set<Friends> allFriends = friendsRepository.findByUserId(user.getId());

        for (Friends f : allFriends) {
            if (f.getFriend().getUsername().equals(friend.getUsername())) {
                return "You already added "
                        + friend.getUsername() + " as a friend!";
            }
        }
        friends.setUser(user);
        friends.setFriend(friend);
        friendsRepository.save(friends);
        return "Friend added successfully";
    }

    /**
     * This method finds all the friends request you have ever sent.
     * It's a set which will make life easier with following methods.
     *
     * @param username the username whose friends to get.
     * @return a set of all usernames of people the user has added
     */
    protected Set<String> getMyFriends(@PathVariable String username) {
        User user = userRepository.findByUsername(username);
        Set<String> result = new HashSet<>();
        Set<Friends> allFriends = friendsRepository.findByUserId(user.getId());
        for (Friends friends : allFriends) {
            result.add(friends.getFriend().getUsername());
        }
        return result;
    }

    /**
     * Joke url, please don't hate.
     * Works as follows:
     * 1. Gets the user from the database to check if it exists
     * 2. finds a all friends the user has added regardless of if they added them back or not
     *
     * @param username the username of the people who's friends request
     *                 should be retrieved.
     * @return the set of all people who added that user as a friend.
     */
    protected Set<String> getPeopleWhoBefriendedMe(@PathVariable String username) {
        User user = userRepository.findByUsername(username);
        Set<String> result = new HashSet<>();
        Set<Friends> allFriends = friendsRepository.findByFriendId(user.getId());
        for (Friends friends : allFriends) {
            result.add(friends.getUser().getUsername());
        }
        return result;
    }

    /**
     * This method is used to get friends who have both added each other.
     * It uses set intersection between the following sets:
     * - all the people who the user has added
     * - all the people who have added the user
     *
     * @param username the user whose friends will be retreived
     * @return a set of all the usernames of people who are both added by and have added the user
     */
    @GetMapping(value = "/getmutualfriends/{username}")
    public Set<String> getMutualFriends(@PathVariable String username) {
        User user = userRepository.findByUsername(username);
        Set<String> result = new HashSet<>(getPeopleWhoBefriendedMe(user.getUsername()));
        result.retainAll(getMyFriends(user.getUsername()));
        return result;
    }

    /**
     * This gets all friends requests that aren't mutual, which have been sent to you.
     * THe method uses set difference between the following two sets, in this order:
     * - the set of all people who have added the user
     * - the set of all people who the user has added.
     *
     * @param username the username whose requests should be gotten.
     * @return a set of usernames of users who have added the user but haven't been added back.
     */
    @GetMapping(value = "/getpendingfriendrequests/{username}")
    public Set<String> getPendingFriendRequests(@PathVariable String username) {
        User user = userRepository.findByUsername(username);
        Set<String> difference = getPeopleWhoBefriendedMe(user.getUsername());
        difference.removeAll(getMyFriends(user.getUsername()));
        return difference;
    }

    /**
     * This method gets all the people who have a pending friend request.
     * It again uses set difference between the following sets, in this order
     * - the set of users who the users has added
     * - the set of users who have added the user back
     *
     * @param username the user whose sent friend request should be retrieved
     * @return a set of usernames who the user has sent a request and who haven't responded
     */
    @GetMapping(value = "/getsentpendingfriendsrequests/{username}")
    public Set<String> getSentPendingFriendRequests(@PathVariable String username) {
        Set<String> difference = new HashSet<>(getMyFriends(username));
        difference.removeAll(getPeopleWhoBefriendedMe(username));
        return difference;
    }
}
