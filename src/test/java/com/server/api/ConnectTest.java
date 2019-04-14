package com.server.api;


import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ConnectTest {

//    @Test
//    public void testRetrieveData() {
//        float co = 0;
//        try {
//            co = Connect.retrieveData("result_food_meat");
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        }
//        assertTrue(co == Connect.retrieveData("result_food_meat"));
//    }

    @Test(expected = NullPointerException.class)
    public void testRetrieveDataUnirestException() throws InterruptedException {
        float result = 0;
        Thread.sleep(50);
        Float co = Connect.retrieveData("result");
        result = co * 1000 / 365;
    }
}
