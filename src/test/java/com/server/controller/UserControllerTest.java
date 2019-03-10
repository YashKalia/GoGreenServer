package com.server.controller;

import com.server.entity.User;
import com.server.repository.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={UserController.class})
public class UserControllerTest {

    private MockMvc mvc;

    @Autowired
    @MockBean
    UserController userController;

    @MockBean
    UserRepository userRepository;

    private List<User> users;
    private User user1;
    private User user2;
    private User user3;

    String user1AsJSON = "{\n" +
            "\t\"username\": \"user1\",\n" +
            "\t\"password\": \"password\",\n" +
            "\t\"roles\": [{\n" +
            "\t\t\"role\": \"USER\"\n" +
            "\t}]\n" +
            "}";

    String user2AsJSON = "{\n" +
            "\t\"username\": \"user2\",\n" +
            "\t\"password\": \"password\",\n" +
            "\t\"roles\": [{\n" +
            "\t\t\"role\": \"USER\"\n" +
            "\t}]\n" +
            "}";

    String user3AsJSON = "{\n" +
            "\t\"username\": \"user3\",\n" +
            "\t\"password\": \"password\",\n" +
            "\t\"roles\": [{\n" +
            "\t\t\"role\": \"USER\"\n" +
            "\t}]\n" +
            "}";

    @Before
    public void setup() {

        mvc = standaloneSetup(this.userController).build();

        user1 = new User("user1", "password");
        user2 = new User("user2", "password");
        user3 = new User("user3", "password");

        users = userRepository.findAll();

        userController.addUser(user1);
        userController.addUser(user2);

    }

    @Test
    public void testGetAllUsers() throws Exception {

        given(userController.getAllUsers())
                .willReturn(users);

        mvc.perform(MockMvcRequestBuilders.get("/users/getall")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void testAddUserSuccess() throws Exception {

        given(userController.addUser(user3))
                .willReturn(users);


        mvc.perform(MockMvcRequestBuilders.post("/users/register")
                .content(user3AsJSON)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void testAddUserFailure() throws Exception {

        given(userController.addUser(user1))
                .willReturn(null);


        mvc.perform(MockMvcRequestBuilders.post("/users/register")
                .content(user1AsJSON)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void testDeleteUserSuccess() throws Exception {

        given(userController.deleteUser(user2))
                .willReturn(users);


        mvc.perform(MockMvcRequestBuilders.delete("/users/delete")
                .content(user2AsJSON)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void testDeleteUserFailure() throws Exception {

        given(userController.deleteUser(user1))
                .willReturn(null);

        mvc.perform(MockMvcRequestBuilders.delete("/users/delete")
                .content(user1AsJSON)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}
