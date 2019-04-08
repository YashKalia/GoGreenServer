package com.server.api;

public class Calculator {

    public double vegetarianMeal() {
        double result = 0;
        try {
            Float co = Connect.retrieveData("result_food_meat");    //getting the data from the API
            double d = co.doubleValue();    //converting float value to double
            result = d * 1000 / 365;   //breaking it down to a meal a day and to kgs instead of tons
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return result;
    }

    public double solarPanelInstall() {
        double coEmission = 0;

        try {
            coEmission = Connect.retrieveData("input_footprint_housing_gco2_per_kwh").doubleValue();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return 0.4 * 5 * coEmission * 30;
        //on average a size 65 x 39 inch solar panel produces
        // 0.4 kW on an average day the solar panel works for 5-6 hrs

    }

    public double localProduce() {

        double coEmission = 0;
        try {
            coEmission = Connect.retrieveData("result_food_fruitsveg").doubleValue();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return coEmission * 1000 / 365 * 0.11;
        //transportation accounts for only 11% of food related CO2 emissions
    }

    public double temperatureLowered() {

        double coEmission = 0;
        try {
            coEmission = Connect.retrieveData("result_natgas_direct").doubleValue();
            coEmission = coEmission * 1000 / 365 * 0.03;
            // lowering the temperature results in about 3% decrease in gas usage
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return coEmission;
    }

    public double publicInsteadCar() {

        double coEmission = 0;
        try {
            double publicTrans = Connect.retrieveData("result_publictrans_direct").doubleValue();
            double totalTrans = Connect.retrieveData("result_transport_total").doubleValue();
            coEmission = coEmission + totalTrans - publicTrans;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return coEmission;
    }

    public double bikeInsteadCar() {

        double coEmission = 0;
        try {
            coEmission = Connect.retrieveData("result_transport_direct").doubleValue();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return coEmission;
    }
}
