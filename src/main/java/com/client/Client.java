package com.client;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
public class Client {
    public static void main(String[] args) {
        try {
            Client.call_me();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void call_me() throws Exception {
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("Enter the url to retrieve the information on the server or 'default'\nType exit to terminate");
            String line = sc.nextLine();
            String url;
            if (line.equals("default"))
                url = "http://localhost:8080/actions";
            else if(line.equals("exit")) break;
            else url = line;
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
            System.out.println(response.toString());
        }
        //print in String


    }
}