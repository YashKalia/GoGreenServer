package com.server.api;

public class Calculator {

    public double vegetarianMeal(){

        Float co = Connect.retrieveData("result_food_meat");    //getting the data from the API
        double d = co.doubleValue();    //converting float value to double
        double result = d * 1000/365;   //breaking it down to a meal a day and to kgs instead of tons
        return result;

    }

    public double solarPanelInstall(){

        double d;
    }
}
