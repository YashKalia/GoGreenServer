package com.server.controller;

import com.server.entity.User;
import com.server.repository.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={UserController.class})
public class UserControllerTest {

    @Autowired
    UserController userController;

    @MockBean
    UserRepository userRepository;

    List<User> users = new ArrayList<>();
    private User user1;
    private User user2;
    private User user3;
    private User user4;

    @Before
    public void setup() {

        user1 = new User("user1", "password");
        user1.setId(1);
        user2 = new User("user2", "password");
        user2.setId(2);
        user3 = new User("user3", "password");
        user3.setId(3);
        user4 = new User("user4", null);
        user4.setId(4);

        users.add(user1);
        users.add(user2);
        users.add(user3);


    }

    @Test
    public void testGetAllUsers() {

        when(userRepository.findAll()).thenReturn(users);

        assertEquals(users, userController.getAllUsers());

    }

    @Test
    public void testGetOneUser() {

        when(userRepository.findByUsername(user1.getUsername())).thenReturn(user1);

        assertEquals(user1, userController.getOneUser(user1));

    }

    @Test
    public void testAddUserSuccess() {

        when(userRepository.findAll()).thenReturn(users);

        userController.addUser(user1);
        userController.addUser(user2);

        assertEquals(users, userController.addUser(user3));

    }

    @Test
    public void testAddUserFailure() {

        when(userRepository.findByUsername("user1")).thenReturn(user1);

        assertNull(userController.addUser(user1));

    }

    @Test
    public void testDeleteUserSuccess() {

        users.remove(0);

        when(userRepository.findByUsername(user1.getUsername())).thenReturn(user1);

        when(userRepository.findAll()).thenReturn(users);

        assertEquals(users, userController.deleteUser(user1));

    }

    @Test
    public void testDeleteUserFailure() {

        when(userController.deleteUser(user4)).thenReturn(null);

        assertNull(userController.deleteUser(user4));

    }

    @Test
    public void testVerify() {

        BCryptPasswordEncoder encoder = mock(BCryptPasswordEncoder.class);

        User user5 = new User("user1", "password");
        user5.setId(1);

        when(userRepository.findByUsername(user1.getUsername())).thenReturn(user1);

        when(userRepository.existsByUsername(user1.getUsername())).thenReturn(true);

        when(userRepository.existsByUsername(user1.getUsername()) &&
                encoder.matches(user1.getPassword(), user5.getPassword())).thenReturn(true);

        assertEquals(false, userController.Verify(user1));

    }



}