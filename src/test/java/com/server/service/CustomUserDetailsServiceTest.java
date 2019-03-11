package com.server.service;

import com.server.config.SecurityConfiguration;
import com.server.controller.UserController;
import com.server.entity.Role;
import com.server.entity.User;

import com.server.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {com.server.service.CustomUserDetailsService.class, com.server.service.CustomUserDetails.class})
public class CustomUserDetailsServiceTest {

    private MockMvc mvc;

    private UserDetails userDetails;
    private User user1;
    private User user2;
    private Set<Role> roles1 = new HashSet<>();
    private List<User> users;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    @MockBean
    UserController userController;

    @MockBean
    private CustomUserDetails customUserDetails;

    @MockBean
    SecurityConfiguration securityConfiguration;

    @MockBean
    UserRepository userRepository;

    @Before
    public void setup() {

        mvc = standaloneSetup(this.userController).build();

        user1 = new User("user1", "password");
        user2 = new User("user2", "password");

        user1.setId((long) 1);
        user2.setId((long) 2);

        roles1.add(new Role("USER"));
        user1.setRoles(roles1);
        user2.setRoles(roles1);

        userController.addUser(user1);

        users = userRepository.findAll();

    }

    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByUsername() {

        customUserDetailsService.loadUserByUsername("user1");

    }

}