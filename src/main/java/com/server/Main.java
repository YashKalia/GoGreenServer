package com.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        Connect.retrieveData("result_food_meat");
        SpringApplication.run(Main.class, args);
    }

}
