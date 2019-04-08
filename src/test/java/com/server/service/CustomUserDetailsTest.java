package com.server.service;

import com.server.entity.Role;
import com.server.entity.User;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class CustomUserDetailsTest {

    private User user1;
    private User user2;
    private Set<Role> roles1 = new HashSet<>();

    @InjectMocks
    private CustomUserDetails customUserDetails;

    @InjectMocks
    User user;

    @Before
    public void setup() {

        user1 = new User("user1", "password");
        user2 = new User("user2", "password");

        user1.setId((long) 1);
        user2.setId((long) 2);

        roles1.add(new Role("USER"));
        user1.setRoles(roles1);
        user2.setRoles(roles1);

        customUserDetails.setUser(user1);

    }

    @Test
    public void testGetUser() {

        assertTrue(customUserDetails.getUser().equals(user1));

    }

    @Test
    public void testSetUser() {

        customUserDetails.setUser(user2);

        assertTrue(customUserDetails.getUser().equals(user2));

    }

    @Test
    public void testGetAuthorities() {

        Collection<? extends GrantedAuthority> auth = user1.getRoles().stream()
                .map(role->new SimpleGrantedAuthority("ROLE_"+role.getRole())).collect(Collectors.toList());

        assertEquals(auth, customUserDetails.getAuthorities());

    }

    @Test
    public void testGetPassword() {

        assertEquals(user1.getPassword(), customUserDetails.getPassword());

    }

    @Test
    public void testGetUsername() {

        assertEquals(user1.getUsername(), customUserDetails.getUsername());

    }

    @Test
    public void testIsAccountNonExpired() {

        assertTrue(customUserDetails.isAccountNonExpired());

    }

    @Test
    public void testIsAccountNonLocked() {

        assertTrue(customUserDetails.isAccountNonLocked());

    }

    @Test
    public void testIsCredentialsNonExpired() {

        assertTrue(customUserDetails.isCredentialsNonExpired());

    }

    @Test
    public void testIsEnabled() {

        assertTrue(customUserDetails.isEnabled());

    }

}