package com.company;

import com.mitchtalmadge.asciidata.graph.ASCIIGraph;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What would you like to do:");
        System.out.println("(1) Daily price of specified ticker");
        System.out.println("(2) Other currency/crypto to USD exchange rate");
        System.out.println("(3) Daily price of specified crypto");
        String choice = scanner.next();
        Requests r = new Requests();
        switch (choice) {
            case "1" -> {
                System.out.println("What ticker do you want to see the daily price chart for?");
                System.out.print("$"); //asks user for ticker
                String ticker = scanner.next().toUpperCase();
                double[] data = r.getDoubleDataForTicker(ticker);
                printChart(data, ticker);
            }
            case "2" -> {
                System.out.println("What currency do you want to know the exchange rate for?"); //asks for currency/ticker
                String currency = scanner.next().toUpperCase();
                double amount = r.exchangeCurrencyToUSD(currency);
                System.out.println("Current USD to " + currency + " exchange rate is : " + amount);
            }
            case "3" -> {
                System.out.println("What cryptocurrency would you like to see the daily chart for?");
                String currency = scanner.next().toUpperCase();
                double[] data = r.getDoubleDataForCrypto(currency);
                printChart(data, currency);
            }
        }

    }

    public static void printChart(double[] data, String ticker) {
        System.out.println("TODAY'S PRICE OF $" + ticker); // prints graph header
        int rows = 15;
        System.out.println(ASCIIGraph.fromSeries(data).withNumRows(rows).plot()); // prints graph

    }
}
