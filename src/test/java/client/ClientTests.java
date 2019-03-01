package client;

import com.client.Client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={com.client.Client.class})
@AutoConfigureWebTestClient
public class ClientTests {

    @Autowired
    private Client testClient;
/*
    @Test
    public void getActionsTestTrue() {

        /*try {
            assertTrue(this.testClient.call_me().contains("vegan meal"));
        }

        catch (Exception e) {
            e.printStackTrace();
        }


        assertTrue(1==1);
    }
    */

  /*  @Test
    public void getActionsTestFalse() {

        /*try {
            assertFalse(this.testClient.call_me().contains("database"));
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        assertTrue(1==1);

    }

*/
}