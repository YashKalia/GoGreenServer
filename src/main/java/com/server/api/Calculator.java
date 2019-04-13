package com.server.api;

import javax.validation.constraints.Null;

public class Calculator {

    public double vegetarianMeal() {
        double coEmission = 0;
        try {
            Float co = Connect.retrieveData("result_food_meat");    //getting the data from the API
            double d = co.doubleValue();    //converting float value to double
            coEmission = d * 1000;
            coEmission = coEmission / 365;   //breaking it down to a meal a day and to kgs instead of tons
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return coEmission;
    }

    public double solarPanelInstall() {
        double coEmission = 0;
        try {
            coEmission = Connect.retrieveData("input_footprint_housing_gco2_per_kwh").doubleValue();
            coEmission = coEmission * 0.4;
            coEmission = coEmission * 5;
            coEmission = coEmission * 30;
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return coEmission;
        //on average a size 65 x 39 inch solar panel produces
        // 0.4 kW on an average day the solar panel works for 5-6 hrs

    }

    public double localProduce() {
        double coEmission = 0;
        try {
            coEmission = Connect.retrieveData("result_food_fruitsveg").doubleValue();
            coEmission = coEmission * 1000;
            coEmission = coEmission / 365;
            coEmission = coEmission * 0.11;
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return coEmission;
        //transportation accounts for only 11% of food related CO2 emissions
    }

    public double temperatureLowered() {
        double coEmission = 0;
        try {
            coEmission = Connect.retrieveData("result_natgas_direct").doubleValue();
            coEmission = coEmission * 1000;
            coEmission = coEmission / 365;
            coEmission = coEmission * 0.03;
            // lowering the temperature results in about 3% decrease in gas usage
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return coEmission;
    }

    public double publicInsteadCar() {
        double coEmission = 0;
        try {
            double publicTrans = Connect.retrieveData("result_publictrans_direct").doubleValue();
            double totalTrans = Connect.retrieveData("result_transport_total").doubleValue();
            coEmission = totalTrans - publicTrans;
            coEmission = coEmission / 365;
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return coEmission;
    }

    public double bikeInsteadCar() {
        double coEmission = 0;
        try {
            coEmission = Connect.retrieveData("result_transport_direct").doubleValue();
            coEmission = coEmission / 365;
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return coEmission;
    }

    public double hangDryClothes() {
        double coEmission = 0;
        try {
            double coPerKWh = Connect.retrieveData("input_footprint_housing_gco2_per_kwh").doubleValue();
            coEmission = 2.8 * coPerKWh;
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return coEmission;
    }

    public double washingCold() {
        double coEmission = 0;
        try {
            double coPerKWh = Connect.retrieveData("input_footprint_housing_gco2_per_kwh").doubleValue();
            coEmission = 0.3 * coEmission;  // estimated kWh of average wash
            coEmission = coPerKWh * coEmission;
            coEmission = coEmission * 0.4;  //lowering the temperature to 30 degrees can save up to 40% electricity
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return coEmission;
    }

    public double secondHandClothing() {
        double coEmission = 0;
        try {
            double yearly = Connect.retrieveData("result_goods_clothing").doubleValue();
            yearly = yearly * 1000;
            coEmission = yearly / 54;
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return coEmission;
    }

    public double veganMeal() {
        double coEmission = 0;
        try {
            double meat = Connect.retrieveData("result_food_meat").doubleValue();
            double dairy = Connect.retrieveData("result_food_dairy").doubleValue();
            meat = meat * 1000;
            meat = meat / 365;
            dairy = dairy * 1000;
            dairy = dairy / 365;
            coEmission = meat + dairy;
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return coEmission;
    }

    public double plantATree() {
        double yearly = 20.3; // according to https://www.grow-trees.com/offset.php
        double coEmission;
        coEmission = yearly / 12;
        return coEmission;

    }

}
