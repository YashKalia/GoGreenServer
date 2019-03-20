package com.server.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FriendsTest {

    private User user1;
    private User user2;
    private User user3;
    Friends u1u2;

    @Before
    public void setup() {

        user1 = new User("user1", "password");
        user2 = new User("user2", "password");
        user3 = new User("user3", "pw");

         u1u2 = new Friends(user1,user2);

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

    @Test
    public void testGetUser() {
        assertEquals(user1,u1u2.getUser());
    }

    @Test
    public void testGetFriend() {
        assertEquals(u1u2.getFriend(),user2);
    }

    @Test
    public void testSetFriend() {
        u1u2.setFriend(user3);
        assertEquals(u1u2.getFriend(),user3);
    }

    @Test
    public void testSetUser() {
        u1u2.setUser(user3);
        assertEquals(user3,u1u2.getUser());
    }

    @Test
    public void testAreTheyMyFriendTrue() {
        assertEquals(true, u1u2.areTheyMyFriend(user2));
    }

    @Test(expected = NullPointerException.class)
    public void testAreTheyMyFriendFalse() {
        assertEquals(false,u1u2.areTheyMyFriend(null));
    }

    @Test
    public void testDefaultConstructor() {
        u1u2 = new Friends();
        assertEquals(null,u1u2.getFriend());
        assertEquals(null,u1u2.getUser());
    }
}
