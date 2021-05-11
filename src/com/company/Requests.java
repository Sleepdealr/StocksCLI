package com.company;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
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

    public void getDataForTicker(String ticker){
        String requestUrl = String.format("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=%s&interval=5min&apikey=%s", ticker, KEY);
        JSONObject json = null;
        try {
            json = new JSONObject(IOUtils.toString(new URL(requestUrl), StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(json.toString());
        System.out.println(json.get("Meta Data"));

    }
}
