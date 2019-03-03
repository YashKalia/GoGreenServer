package client;

import com.client.Client;
import com.server.entity.Action;

import org.json.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={com.client.Client.class})
@AutoConfigureWebTestClient
public class ClientTests {

    @Autowired
    Client testClient;

    private String sv = "http://localhost:8080/actions";

    private ByteArrayOutputStream byteArrayOutputStream;
    private PrintStream console;

    @Before
    public void setup() {

        byteArrayOutputStream = new ByteArrayOutputStream();
        console = System.out;

        testClient = new Client();

    }

    @Test
    public void testGetAllRequest() throws IOException, JSONException {

        URL url = new URL(sv);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        JSONArray result = Client.getAllRequest(con);

        assertEquals("Eating a vegan meal", result.getJSONObject(0).getString("name"));

    }

    @Test
    public void testGetRequest() throws IOException, JSONException {

        URL newurl = new URL(sv + "/" + 2);

        JSONObject result = Client.getRequest(newurl);

        assertTrue(result.getString("name").equals("Planting a tree"));

    }

    @Test
    public void testPostRequest() throws IOException, JSONException {

        URL url = new URL(sv);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        JSONObject result = Client.postRequest(con, new Action(5,"Writing code",12));

        assertTrue(result.getString("name").equals("Writing code"));

        Client.deleteRequest(sv, 5);

    }

    @Test
    public void testDeleteRequest() throws IOException, JSONException {

        JSONObject result = Client.deleteRequest(sv, 1);

        assertTrue(result.getString("name").equals("Eating a vegan meal"));

        URL url = new URL(sv);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        Client.postRequest(con, new Action(1,"Eating a vegan meal",10));

    }

    @Test
    public void testCallMeGetAll() throws Exception {

        String result = "Response Code 200\n" +
                "[{\"name\":\"Eating a vegan meal\",\"id\":1,\"points\":10},{\"name\":\"Planting a tree\",\"id\":2,\"points\":20},{\"name\":\"Riding a bike to work\",\"id\":3,\"points\":5},{\"name\":\"Committed Fraud\",\"id\":4,\"points\":-2147483648}]\n";

        runTest("1 5", "com.client.Client");
        assertThat(byteArrayOutputStream.toString(), is(result));

    }

    @Test
    public void testCallMePost() throws Exception {

        String result = "{\"name\":\"Writing code\",\"id\":5,\"points\":12}\n";

        runTest("2 5", "com.client.Client");
        assertThat(byteArrayOutputStream.toString(), is(result));

        Client.deleteRequest(sv, 5);

    }

    @Test
    public void testCallMeDeleteById() throws Exception {

        URL url = new URL(sv);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        Client.postRequest(con, new Action(5,"Writing code",12));

        String result = "Select an action to delete by ID\nTrying to delete {\"name\":\"Writing code\",\"id\":5,\"points\":12}\n200\n";

        runTest("4 5 5", "com.client.Client");
        assertThat(byteArrayOutputStream.toString(), is(result));

    }

    @Test
    public void testCallMeGetById() throws Exception {

        String result = "Select an action to get by ID\n{\"name\":\"Eating a vegan meal\",\"id\":1,\"points\":10}\n";
        runTest("6 1 5", "com.client.Client");
        assertThat(byteArrayOutputStream.toString(), is(result));

    }

    private void runTest(final String data, final String className) throws Exception {

        final InputStream input = new ByteArrayInputStream(data.getBytes("UTF-8"));
        final InputStream old = System.in;

        try {
            System.setOut(new PrintStream(byteArrayOutputStream));
            System.setIn(input);

            final Class<?> cls = Class.forName(className);
            final Method meth = cls.getDeclaredMethod("main", String[].class);
            final String[] params = new String[]{};
            meth.invoke(null, (Object) params);

        } finally {
            System.setOut(console);
            System.setIn(old);
        }
    }

}