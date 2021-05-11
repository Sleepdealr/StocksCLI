package com.company;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import javax.swing.plaf.synth.SynthUI;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Requests {
    private String KEY;

    public Requests() { //reads API key from file
        try {
            File myObj = new File("src/com/company/key.txt"); // gets file from relative path in project

            Scanner reader = new Scanner(myObj);
            KEY = reader.nextLine(); //reads single line from file (API key)
            reader.close(); //closes reader
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public double[] getDoubleDataForTicker(String ticker){ // returns stock value in 5 minute intervals for current day for specified ticker
        //formats url with apikey and ticker
        String requestUrl = String.format("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=%s&apikey=%s", ticker, KEY);

        JSONObject json = new JSONObject();

        try {
            json = new JSONObject(IOUtils.toString(new URL(requestUrl), StandardCharsets.UTF_8));
            if(json.has("Error Message")){ // key is only present in errors
                System.out.println("Ticker does not exist");
                return new double[]{Integer.MIN_VALUE}; // returns error value
            }
         } catch (IOException e) {
            e.printStackTrace();
        }

        json = json.getJSONObject("Time Series (Daily)"); // gets all data for the date
        Iterator<String> keys = json.keys(); //creates iterator of all keys(time/date values) to allow for iteration
        JSONObject timeData;
        int cnt = 0;
        double value;
        double[] data = new double[json.length() * 2]; // doubles data for a cleaner looking graph

        while(keys.hasNext()) { //
            String key = keys.next();
            if (json.get(key) instanceof JSONObject) {
                timeData = (JSONObject) json.get(key); // gets the json object for the time
                value = Double.parseDouble(timeData.get("1. open").toString()); // grabs the opening value
                data[cnt] = value;
                data[cnt+1] = value; // doubles data for a cleaner looking graph
                cnt+=2;
            }
        }

        return data;
    }
}
