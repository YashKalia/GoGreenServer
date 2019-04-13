package com.server.api;

import com.mashape.unirest.http.HttpResponse;

import com.mashape.unirest.http.Unirest;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Connect {
    /**
     * The API consumption method.
     * It first sends the request to Berkerly, including credentials and basic inputs
     * receives an xml which it then parses and stores the important values within a hashmap,
     * based on the param it chooses what to return
     * @param str name of the requested value as it is referred to within the xml response
     * @return a float value from the API, co2 emission most likely in tonne/year
     */
    public static Float retrieveData(String str) {
        try {
            HttpResponse<String> response = Unirest
                    .get("https://apis.berkeley.edu/coolclimate/footprint-defaults")
                    .basicAuth("a97fb141", "ac7d280537fe7ad2ee838875204f1b46")
                    .queryString("input_location_mode", "1")
                    .queryString("input_location", "90001")
                    .queryString("input_income", "1")
                    .queryString("input_size", "1").asString();
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            ArrayList<String> mikell = new ArrayList<String>();
            mikell.add("result_food_meat");
            mikell.add("input_footprint_housing_gco2_per_kwh");
            mikell.add("result_natgas_direct");
            mikell.add("result_food_fruitsveg");
            mikell.add("result_transport_total");
            mikell.add("result_publictrans_direct");
            mikell.add("result_goods_clothing");
            mikell.add("result_food_dairy");
            final HashMap<String, Float> a = new HashMap<>();
            saxParser.parse(new ByteArrayInputStream(response.getBody().getBytes()), new org.xml.sax.helpers.DefaultHandler() {

                String within = null;

                @Override
                public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
                    super.startElement(uri, localName, name, attributes);
                    if (mikell.indexOf(name) != -1) {
                        within = name;
                    }
                }

                @Override
                public void endElement(String uri, String localName, String name) throws SAXException {
                    super.endElement(uri, localName, name);
                    if (mikell.indexOf(name) != -1) {
                        within = null;
                    }
                }

                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    super.characters(ch, start, length);
                    if (within != null) {
                        a.put(within, Float.parseFloat(new String(ch, start, length)));
                    }
                }
            });
            //System.out.println(a.toString());
            return a.get(str);
        } catch (com.mashape.unirest.http.exceptions.UnirestException a) {
            System.out.println(a.getMessage());
        } catch (javax.xml.parsers.ParserConfigurationException b) {
            System.out.println(b.getCause().toString());
        } catch (java.io.IOException c) {
            System.out.println(c.getLocalizedMessage());
        } catch (org.xml.sax.SAXException d) {
            System.out.println(d.getException().toString());
        }
        return null;
    }
}
