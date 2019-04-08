package com.server.api;


import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ConnectTest {

    @Test
    public void testRetrieveData() {
        float result = 0;
        try {
            Float co = Connect.retrieveData("result_food_meat");    //getting the data from the API
            result = co * 1000 / 365;   //breaking it down to a meal a day and to kgs instead of tons
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        assertTrue(result == Connect.retrieveData("result_food_meat") * 1000 / 365);
    }

    @Test(expected = NullPointerException.class)
    public void testRetrieveDataUnirestException() {
        float result = 0;
        Float co = Connect.retrieveData("result");
        result = co * 1000 / 365;
    }
}
