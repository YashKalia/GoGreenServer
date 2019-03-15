package com.server.service;

import com.server.entity.User;

import com.server.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {com.server.service.CustomUserDetailsService.class, com.server.service.CustomUserDetails.class})
public class CustomUserDetailsServiceTest {

    private User user1;
    private User user2;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @MockBean
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

        when(userRepository.findByUsername("user1")).thenReturn(user1);

        assertEquals("user1", customUserDetailsService.loadUserByUsername("user1").getUsername());

    }

    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByUsernameFailure() {

        when(userRepository.findByUsername("user2")).thenReturn(null);

        customUserDetailsService.loadUserByUsername("user2");

    }

}