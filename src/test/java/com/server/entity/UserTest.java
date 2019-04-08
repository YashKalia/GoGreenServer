package com.server.entity;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class UserTest {

    private User user1;
    private User user2;
    private Set<Role> roles = new HashSet<>();
    private Set<Role> roles2 = new HashSet<>();

    @Before
    public void setup() {

        user1 = new User("user1", "password");
        user2 = new User("user1", "password");

        user1.setId((long) 1);
        user2.setId((long) 1);

        roles.add(new Role("USER"));
        user1.setRoles(roles);
        user2.setRoles(roles);

        roles2.add(new Role("USER"));
        roles2.add(new Role("ADMIN"));

    }

    @Test
    public void testGetUserId() {

        assertEquals(1, user1.getId());

    }

    @Test
    public void testConstructorWithUserObject() {
        User user = new User(user1);
        assertEquals(user1,user);
    }

    @Test
    public void testSetUserId() {

        user1.setId((long) 15);

        assertEquals(15, user1.getId());

    }

    @Test
    public void testGetUsername() {

        assertEquals("user1", user1.getUsername());

    }

    @Test
    public void testSetUsername() {

        user1.setUsername("user15");

        assertEquals("user15", user1.getUsername());

    }

    @Test
    public void testGetPassword() {

        assertEquals("password", user1.getPassword());

    }

    @Test
    public void testSetPassword() {

        user1.setPassword("newPassword");

        assertEquals("newPassword", user1.getPassword());

    }

    @Test
    public void testGetRoles() {

        assertEquals(roles, user1.getRoles());

    }

    @Test
    public void testSetRoles() {

        user1.setRoles(roles2);

        assertEquals(roles2, user1.getRoles());

    }

    @Test
    public void testEqualsSameMemLoc() {

        User user3 = user1;

        assertTrue(user1.equals(user3));

    }

    @Test
    public void testEqualsTrue() {

        assertTrue(user1.equals(user2));

    }

    @Test
    public void testEqualsWrongId() {

        user2.setId(2);

        assertFalse(user1.equals(user2));

    }

    @Test
    public void testEqualsWrongUsername() {

        user2.setUsername("user2");

        assertFalse(user1.equals(user2));

    }

    @Test
    public void testEqualsWrongPassword() {

        user2.setPassword("newpassword");

        assertFalse(user1.equals(user2));

    }

    @Test
    public void testEqualsWrongObject() {

        assertFalse(user1.equals("hello"));

    }

    @Test
    public void testDefaultConstructor() {
        user1 = new User();
        assertEquals(null,user1.getUsername());
        assertEquals(null,user1.getPassword());
        assertEquals(null,user1.getRoles());
    }

}
