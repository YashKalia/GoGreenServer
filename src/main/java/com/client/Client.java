package com.client;

import com.server.entity.Action;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
/**
 *Explanation of how stuff works right now:
 *it's an infinite loop in which you can select actions to do various http requests
        1=get all
        2=post something (hardcoded right now)
        3=put something WIP
        4= delete something
        5=exit WIP
        Still working on consistency throughout
        I'm aware of the org.json.josnexception, more research needed.
         **/

public class Client {

    /**
     * The main method.
     * @param args it's args.
     */
    public static void main(String[] args) {
        try {
            Client.call_me();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The method where everything else happens.
     * @throws Exception cannot be more specific.
     */
    public static void call_me() throws Exception {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String sv = "http://localhost:8080/actions";
            URL url = new URL(sv);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            int option = sc.nextInt();
            if (option == 1) {
                System.out.println(getRequst(con).toString());
            } else if (option == 2) {
                System.out.println(postRequest(con,new Action(5,"writing code",12)));
            } else if (option == 4) {
                System.out.println("Select an action to delete by ID");
                int action = sc.nextInt();
                deleteRequest(sv,action);
            } else if (option == 5)  {
                break;
            } else if (option == 6) {
                System.out.println("Select an action to get by ID");
                int action = sc.nextInt();
                URL newurl = new URL(sv+"/"+action);
                System.out.println(getRequest(newurl));
            }
        }
    }

    /**
     * Deletes an action. Returns an exception if there is no action. Will be fixed with a custom JSON object later
     * @param url the url of the repository where ALL actions are located
     * @param action the action to be deleted
     * @return the action that was deleted
     */
    private static JSONObject deleteRequest(String url, int action) {
        url += "/" + action;
        JSONObject res = new JSONObject();
        try {
            URL ur = new URL(url);
            res = (JSONObject) getRequest(ur);
            System.out.println("Trying to delete " + res.toString());
            HttpURLConnection con = (HttpURLConnection) ur.openConnection();
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/json" );
            con.setRequestMethod("DELETE");
            System.out.println(con.getResponseCode());
        } catch (IOException e) {
            System.out.println("Error occurred in deleting request");
        }
        return res;
    }

    /**
     * Puts a new action in an unused id slot.
     * @param conn the connection
     * @param js the action to be inserted
     * @return the action at the index with the given id AFTER the insertion (testing purposes)
     */
    private static JSONObject postRequest(HttpURLConnection conn, Action js) {
        JSONObject result = new JSONObject(js);
        try {
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            OutputStream os = conn.getOutputStream();
            os.write(result.toString().getBytes("UTF-8"));
            os.close();
            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            String res = IOUtils.toString(in, "UTF-8");
            String ur = conn.getURL().toString()+"/"+js.getId();
            URL url = new URL(ur);
           // result = new JSONObject(getRequest(url));
            result = getRequest(url);
            conn.disconnect();
        } catch (IOException e) {
            System.out.println(e);
        }
        return result;
    }

    /**
     * Gets all the actions.
     * @param con the HTTP connection
     * @return an array of JSON Objects with all the actions in the 'database'
     */
    private static JSONArray getRequst(HttpURLConnection con) {
        JSONArray myResult = null;
        try {
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            System.out.println("Response Code " + responseCode);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            myResult = new JSONArray(response.toString());
        } catch (IOException e) {
            System.out.print("Exception in GET request");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return myResult;
    }

    /**
     * Gets a specific action.
     * @param url the url of the action to be retrieved
     * @return the action in JSON format.
     */
    public static JSONObject getRequest(URL url){
        JSONObject res = new JSONObject();
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            String inputLine;
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            res = new JSONObject(response.toString());
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return res;
    }
}
