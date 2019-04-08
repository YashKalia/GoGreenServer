package com.server.api;

public class Calculator {

    public double vegetarianMeal() {
        double result = 0;
        try {
            Float co = Connect.retrieveData("result_food_meat");    //getting the data from the API
            double d = co.doubleValue();    //converting float value to double
            return d * 1000 / 365;   //breaking it down to a meal a day and to kgs instead of tons
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return result;
    }

    public double solarPanelInstall() {
        double d = 0;

        try {
            d = Connect.retrieveData("input_footprint_housing_gco2_per_kwh").doubleValue();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return 0.4 * 5 * d * 30;
        //on average a size 65 x 39 inch solar panel produces
        // 0.4 kW on an average day the solar panel works for 5-6 hrs

    }

    public double localProduce() {

        double num = 0;
        try {
            num = Connect.retrieveData("result_food_fruitsveg").doubleValue();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return num * 1000 / 365 * 0.11;
        //transportation accounts for only 11% of food related CO2 emissions
    }

    public double temperature() {

        double d;
        try{
            
        }
    }
}
