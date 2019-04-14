package com.server.api;


public class Calculator {

    /**
     * Returns Co2 saved by eating a vegetarian meal.
     *
     * @return Returns Co2 saved by eating a vegetarian meal
     */
    public double vegetarianMeal() {
        double coEmission = 0;
        try {
            //getting the data from the API
            Float co = Connect.retrieveData("result_food_meat");
            //converting float value to double
            double value = co.doubleValue();
            coEmission = value * 1000;
            //breaking it down to a meal a day and to kgs instead of tons
            coEmission = coEmission / 365;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return coEmission;
    }

    /**
     * Returns Co2 saved by installing solar panels.
     *
     * @return Returns Co2 saved by installing solar panels
     */
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

    /**
     * Returns Co2 saved by buying local produce.
     *
     * @return Returns Co2 saved by buying local produce
     */
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

    /**
     * Returns Co2 saved by using lowering temperature of house.
     *
     * @return Returns Co2 saved by using lowering temperature of house
     */
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

    /**
     * Returns Co2 saved by using public transport instead of car.
     *
     * @return Returns Co2 saved by using public transport instead of car
     */
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

    /**
     * Returns Co2 saved by riding bike instead of driving a car.
     *
     * @return Returns Co2 saved by riding bike instead of driving a car
     */
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

    /**
     * Returns Co2 saved by air-drying clothes.
     *
     * @return Returns Co2 saved by air-drying clothes
     */
    public double airDryClothes() {
        double coEmission = 0;
        try {
            double coPerKWh = Connect.retrieveData("input_footprint_housing_gco2_per_kwh")
                    .doubleValue();
            coEmission = 2.8 * coPerKWh;
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return coEmission;
    }

    /**
     * Returns Co2 saved by washing clothes with cold water.
     *
     * @return Returns Co2 saved by washing clothes with cold water
     */
    public double washingCold() {
        double coEmission = 0;
        try {
            double coPerKWh = Connect.retrieveData("input_footprint_housing_gco2_per_kwh")
                    .doubleValue();
            coEmission = 0.3 * coEmission;  // estimated kWh of average wash
            coEmission = coPerKWh * coEmission;
            //lowering the temperature to 30 degrees can save up to 40% electricity
            coEmission = coEmission * 0.4;
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return coEmission;
    }

    /**
     * Returns Co2 saved by buying second-hand clothing.
     *
     * @return Returns Co2 saved by buying second-hand clothing
     */
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

    /**
     * Returns Co2 saved by eating a vegan meal.
     *
     * @return Returns Co2 saved by eating a vegan meal
     */
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

    /**
     * Returns Co2 saved by plating a tree.
     *
     * @return Returns Co2 saved by plating a tree
     */
    public double plantATree() {
        double yearly = 20.3; // according to https://www.grow-trees.com/offset.php
        double coEmission;
        coEmission = yearly / 12;
        return coEmission;

    }

    /**
     * Returns Co2 saved by recycling.
     *
     * @return Returns Co2 saved by recycling
     */
    public double recycleWaste() {
        double yearly = 1090;
        double coEmission;
        coEmission = yearly / 12;
        return coEmission;

    }

}
