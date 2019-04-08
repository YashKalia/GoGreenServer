package com.server.service;

import com.server.entity.User;

import com.server.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomUserDetailsServiceTest {

    private User user1;
    private User user2;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @Mock
    UserRepository userRepository;


    @Before
    public void setup() {

        user1 = new User("user1", "password");
        user2 = new User("user2", "password");

        user1.setId((long) 1);
        user2.setId((long) 2);

    }

    @Test
    public void testLoadUserByUsernameSuccess() {

        when(userRepository.existsByUsername("user1")).thenReturn(true);
        when(userRepository.findByUsername("user1")).thenReturn(user1);

        assertEquals("user1", customUserDetailsService.loadUserByUsername("user1").getUsername());

    }

    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByUsernameFailure() {

        when(userRepository.existsByUsername("user2")).thenReturn(false);

        customUserDetailsService.loadUserByUsername("user2");

    }

}