package com.client;

import org.apache.commons.io.IOUtils;
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
                getRequst(con);
            } else if (option == 2) {
                putRequest(con, "{ \"id\" : 5, \"name\" :  \"test\" , \"points\" : 13333 }");
            } else if (option == 4) {
                System.out.println("Select an action to delete by ID");
                int action = sc.nextInt();
                deleteRequest(sv,action);
            } else if (option == 5)  {
                break;
            }
        }
    }

    private static void deleteRequest(String url, int action) {
        url += "/" + action;
        try {
            URL ur = new URL(url);
            System.out.println("Trying to delete" + url);
            HttpURLConnection con = (HttpURLConnection) ur.openConnection();
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/json" );
            con.setRequestMethod("DELETE");
            System.out.println(con.getResponseCode());
        } catch (IOException e) {
            System.out.println("Error occurred in deleting request");
        }
    }

    private static void putRequest(HttpURLConnection conn, String json) {
        try {
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            OutputStream os = conn.getOutputStream();
            os.write(json.getBytes("UTF-8"));
            os.close();
            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            String result = IOUtils.toString(in, "UTF-8");
            System.out.println(result);
            System.out.println("result after Reading JSON Response");
            JSONObject myResponse = new JSONObject(result);
            System.out.println("jsonrpc- " + myResponse.getString("jsonrpc"));
            System.out.println("id- " + myResponse.getInt("id"));
            System.out.println("result- " + myResponse.getString("result"));
            in.close();
            conn.disconnect();
        } catch (IOException e) {
            System.out.println(e);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static void getRequst(HttpURLConnection con) {
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
            System.out.println(response.toString());
        } catch (IOException e) {
            System.out.print("Exception in GET request");
        }
    }
}
