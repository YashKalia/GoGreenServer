package com.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Client_testing {
    public static void main(String[] args){
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader bfr = new BufferedReader(isr);
            System.out.print(bfr.readLine());
        }
        catch (IOException e){
            ;
        }
    }
}
