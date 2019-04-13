package com.server.api;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CalculatorTest {

    Calculator calc;

    @Before
    public void setup() {

        calc = new Calculator();

    }

    @Test
    public void testVegetarianMeal() {
        double coEmission = 0;
        try {
            Float co = Connect.retrieveData("result_food_meat");    //getting the data from the API
            double d = co.doubleValue();    //converting float value to double
            coEmission = d * 1000;
            coEmission = coEmission / 365;   //breaking it down to a meal a day and to kgs instead of tons
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        assertTrue(coEmission == calc.vegetarianMeal());
    }

    @Test
    public void solarPanelInstall() {
        double coEmission = 0;

        try {
            Thread.sleep(50);
            coEmission = Connect.retrieveData("input_footprint_housing_gco2_per_kwh").doubleValue();
            coEmission = coEmission * 0.4;
            coEmission = coEmission * 5;
            coEmission = coEmission * 30;
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            System.out.println(e.getCause());
        }
        assertTrue(coEmission == calc.solarPanelInstall());
    }

    @Test
    public void testLocalProduce() {

        double coEmission = 0;
        try {
            coEmission = Connect.retrieveData("result_food_fruitsveg").doubleValue();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        assertTrue((coEmission * 1000 / 365 * 0.11) == calc.localProduce());
    }

    @Test
    public void testTemperatureLowered() {

        double coEmission = 0;
        try {
            coEmission = Connect.retrieveData("result_natgas_direct").doubleValue();
            coEmission = coEmission * 1000 / 365 * 0.03;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        assertTrue(coEmission == calc.temperatureLowered());
    }

    @Test
    public void publicInsteadCar() {

        double coEmission = 0;
        try {
            double publicTrans = Connect.retrieveData("result_publictrans_direct").doubleValue();
            double totalTrans = Connect.retrieveData("result_transport_total").doubleValue();
            coEmission = totalTrans - publicTrans;
            coEmission = coEmission / 365;
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        assertTrue(coEmission == calc.publicInsteadCar());
    }

    @Test
    public void testBikeInsteadCar() {

        double coEmission = 0;
        try {
            coEmission = Connect.retrieveData("result_transport_direct").doubleValue();
            coEmission = coEmission / 365;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        assertTrue(coEmission == calc.bikeInsteadCar());
    }

    @Test
    public void testHangDryClothes() {

        double coEmission = 0;
        try {
            double coPerKWh = Connect.retrieveData("input_footprint_housing_gco2_per_kwh").doubleValue();
            coEmission = 2.8 * coPerKWh;
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        assertTrue(coEmission == calc.hangDryClothes());
    }

    @Test
    public void washingCold() {

        double coEmission = 0;
        try {
            double coPerKWh = Connect.retrieveData("input_footprint_housing_gco2_per_kwh").doubleValue();
            coEmission = 0.3 * coEmission;  // estimated kWh of average wash
            coEmission = coPerKWh * coEmission;
            coEmission = coEmission * 0.4;  //lowering the temperature to 30 degrees can save up to 40% electricity
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        assertTrue(coEmission == calc.washingCold());
    }

    @Test
    public void secondHandClothing() {

        double coEmission = 0;
        try {
            double yearly = Connect.retrieveData("result_goods_clothing").doubleValue();
            yearly = yearly * 1000;
            coEmission = yearly / 54;
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        assertTrue(coEmission == calc.secondHandClothing());
    }

    @Test
    public void veganMeal() {

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
        assertTrue(coEmission == calc.veganMeal());
    }

    @Test
    public void plantATree() {
        double yearly = 20.3; // according to https://www.grow-trees.com/offset.php
        double coEmission;
        coEmission = yearly / 12;
        assertTrue(coEmission == calc.plantATree());

    }

}
