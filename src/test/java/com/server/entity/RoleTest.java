package com.server.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class RoleTest {

    private Role role1;
    private Role role2;

    @Before
    public void setup() {

        role1 = new Role("USER");
        role1.setId(1);

        role2 = new Role("USER");
        role2.setId(1);

    }

    @Test
    public void testGetRoleId() {

        assertEquals(1, role1.getId());

    }

    @Test
    public void testSetRoleId() {

        role1.setId(15);

        assertEquals(15, role1.getId());

    }

    @Test
    public void testGetRole() {

        assertEquals("USER", role1.getRole());

    }

    @Test
    public void testSetRole() {

        role1.setRole("ADMIN");

        assertEquals("ADMIN", role1.getRole());

    }

    @Test
    public void testEqualsSameMemLoc() {

        Role role3 = role1;

        assertTrue(role1.equals(role3));

    }

    @Test
    public void testEqualsTrue() {

        assertTrue(role2.equals(role1));

    }

    @Test
    public void testEqualsWrongId() {

        role2.setId(2);

        assertFalse(role1.equals(role2));

    }

    @Test
    public void testEqualsWrongRole() {

        role2.setRole("ADMIN");

        assertFalse(role1.equals(role2));

    }

    @Test
    public void testEqualsWrongObj() {

        assertFalse(role1.equals("hello"));

    }

}
