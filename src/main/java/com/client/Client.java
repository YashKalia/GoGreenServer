package com.client;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
public class Client {
    public static void main(String[] args) {
        try {
            Client.call_me();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void call_me() throws Exception {
        String url = "http://localhost:8080/actions";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        int responseCode = con.getResponseCode();
        System.out.println("Sending GET request to URL " + url);
        System.out.println("Response Code " + responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        //print in String
        System.out.println(response.toString());

    }
}