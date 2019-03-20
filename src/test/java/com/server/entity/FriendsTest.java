package com.server.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FriendsTest {

    private User user1;
    private User user2;
    private User user3;
    Friends u1u2 = new Friends(user1,user2);

    @Before
    public void setup() {

        user1 = new User("user1", "password");
        user2 = new User("user2", "password");
        user3 = new User("user3", "pw");

        user1.setId((long) 1);
        user2.setId((long) 2);

        u1u2.setId((long) 1);
    }

    @Test
    public void testGetId() {
        assertEquals(1,(long)u1u2.getId());
    }

    @Test
    public void testSetId() {
        u1u2.setId((long) 42);

        assertEquals(42,(long)u1u2.getId());
    }
}
