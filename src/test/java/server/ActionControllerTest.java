package server;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.server.Controller.ActionController;
import com.server.Entity.Action;
import com.server.Service.ActionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringRunner.class)
@SpringBootTest(classes={com.server.Controller.ActionController.class, com.server.Service.ActionService.class})
public class ActionControllerTest {

    MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    ActionController actionController;

    @MockBean
    ActionService actionService;

    private List<Action> actions;

    @Before
    public void setup() throws Exception {

        this.mockMvc = standaloneSetup(this.actionController).build();

        Action action1 = new Action(1,"Eating a vegan meal",10);
        Action action2 = new Action(2,"Planting a tree", 20);
        Action action3 = new Action(3,"Riding a bike to work",5);
        Action action4 = new Action(4,"Committed Fraud", Integer.MIN_VALUE);

        actions = new ArrayList<>();
        actions.add(action1);
        actions.add(action2);
        actions.add(action3);
        actions.add(action4);

    }

    @Test
    public void testGetAllActionsTest() throws Exception {

        // Mocking service
        when(actionService.getAllActions()).thenReturn(actions);

        mockMvc.perform(get("http://localhost:8080/actions").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("Eating a vegan meal")))
                .andExpect(jsonPath("$[1].name", is("Planting a tree")))
                .andExpect(jsonPath("$[2].name", is("Riding a bike to work")))
                .andExpect(jsonPath("$[3].name", is("Committed Fraud")));
    }

    @Test
    public void testGetActionByIdTest() throws Exception {

        given(actionService.getActionById(1))
                .willReturn(new Action(1,"Eating a vegan meal",10));
        mockMvc.perform(get("http://localhost:8080/actions/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1,\"name\":\"Eating a vegan meal\",\"points\":10}"));
    }

}
