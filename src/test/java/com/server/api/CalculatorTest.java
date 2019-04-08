package com.server.api;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CalculatorTest {

    Calculator calc = new Calculator();

    @Test
    public void testVegetarianMeal() {
        double result = 0;
        try {
            Float co = Connect.retrieveData("result_food_meat");
            double d = co.doubleValue();
            result = d * 1000 / 365;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        assertTrue(result == calc.vegetarianMeal());
    }

    @Test
    public void testSolarPanelInstall() {
        double coEmission = 0;

        try {
            coEmission = Connect.retrieveData("input_footprint_housing_gco2_per_kwh").doubleValue();
            coEmission = 0.4 * 5 * coEmission * 30;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        assertTrue((coEmission) == calc.solarPanelInstall());

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
    public void testPublicInsteadCar() {

        double coEmission = 0;
        try {
            double publicTrans = Connect.retrieveData("result_publictrans_direct").doubleValue();
            double totalTrans = Connect.retrieveData("result_transport_total").doubleValue();
            coEmission = coEmission + totalTrans - publicTrans;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        assertTrue(coEmission == calc.publicInsteadCar());
    }

    @Test
    public void testBikeInsteadCar() {

        double coEmission = 0;
        try {
            coEmission = Connect.retrieveData("result_transport_direct").doubleValue();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        assertTrue(coEmission == calc.bikeInsteadCar());
    }

}
