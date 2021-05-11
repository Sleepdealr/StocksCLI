package com.company;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

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

    public Requests() {
        try {
            File myObj = new File("C:\\Importiant Stuff\\Misc\\StocksCLI\\src\\com\\company\\key.txt");
            Scanner myReader = new Scanner(myObj);
            KEY = myReader.nextLine();
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public double[] getDoubleDataForTicker(String ticker){
        String requestUrl = String.format("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=%s&apikey=%s", ticker, KEY);
        JSONObject json = null;
        try {
            json = new JSONObject(IOUtils.toString(new URL(requestUrl), StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }

        json = json.getJSONObject("Time Series (Daily)");
        Iterator<String> keys = json.keys();
        JSONObject timeData;
        int cnt = 0;
        double value;
        double[] data = new double[json.length() * 2];
        while(keys.hasNext()) {
            String key = keys.next();
            if (json.get(key) instanceof JSONObject) {
                timeData = (JSONObject) json.get(key);
                value = Double.parseDouble(timeData.get("1. open").toString());
                if(value == 0){
                    return data;
                }
                data[cnt] = value;
                data[cnt+1] = value;
                cnt+=2;
            }
        }

        return data;

    }
}
