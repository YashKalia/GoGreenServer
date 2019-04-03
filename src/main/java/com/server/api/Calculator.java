package com.server.api;

public class Calculator {

    public double vegetarianMeal(Float co){

        double d = co.doubleValue();
        double result = d * 1000;
        result = result/365;
        return result;

    }
}
