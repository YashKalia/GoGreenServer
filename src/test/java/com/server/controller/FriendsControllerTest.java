package com.server.controller;

import com.server.entity.Friends;
import com.server.entity.User;
import com.server.repository.FriendsRepository;
import com.server.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class FriendsControllerTest {
    @InjectMocks
    FriendsController friendsController;

    @Mock
    FriendsRepository friendsRepository;

    @Mock
    UserRepository userRepository;

    private User user1;
    private User user2;
    private User user3;
    private User user4;
    private Friends u1u2;
    private Friends u2u1;
    private Friends u1u3;
    private Friends u3u2;
    private Friends u3u4;
    private Friends u2u4;

    public void setup() {
        user1 = new User("user1", "password");
        user1.setId(1);
        user2 = new User("user2", "password");
        user2.setId(2);
        user3 = new User("user3", "password");
        user3.setId(3);
        user4 = new User("user4", "password");
        user4.setId(4);

        u1u2 = new Friends(user1, user2);
        u2u1 = new Friends(user2, user1);
        u1u3 = new Friends(user1, user3);
        u3u2 = new Friends(user3, user2);
        u3u4 = new Friends(user3, user4);
        u2u4 = new Friends(user2, user4);
    }

    @Test
    public void testAdd() {
        friendsController.addFriend(u1u2);

        assertEquals(user2.getUsername(),friendsController.getMutualFriends(user1.getUsername()));
    }
}
