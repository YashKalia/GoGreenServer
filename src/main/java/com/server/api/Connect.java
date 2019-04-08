package com.server.api;


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Connect {
    /**
     *
     * @param str
     * @return
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
            final HashMap<String, Float> a = new HashMap<>();
            saxParser.parse(new ByteArrayInputStream(response.getBody().getBytes()), new org.xml.sax.helpers.DefaultHandler() {

                String within = null;

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    super.startElement(uri, localName, qName, attributes);
                    if (mikell.indexOf(qName) != -1) {
                        within = qName;
                    }
                }

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    super.endElement(uri, localName, qName);
                    if (mikell.indexOf(qName) != -1) {
                        within = null;
                    }
                }

                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    super.characters(ch, start, length);
                    if (within != null)
                        a.put(within, Float.parseFloat(new String(ch, start, length)));
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
