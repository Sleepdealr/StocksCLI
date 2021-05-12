package com.company;

import com.mitchtalmadge.asciidata.graph.ASCIIGraph;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Requests r = new Requests();
        Scanner scanner = new Scanner(System.in);
        System.out.println("What would you like to do:");
        System.out.println("(1) Daily price of specified ticker");
        System.out.println("(2) Other currency/crypto to USD exchange rate");
        String choice = scanner.next(), choice2;

        switch (choice) {
            case "1" -> {
                System.out.println("What ticker do you want to know the price for?");
                System.out.print("$"); //asks user for ticker
                String ticker = scanner.next().toUpperCase();
                double[] data = r.getDoubleDataForTicker(ticker);
                if (data[0] == Integer.MIN_VALUE) { // minvalue is error
                    return;
                }
                printChart(data, ticker);
            }
            case "2" -> {
                System.out.println("What currency do you want to know the exchange rate for?"); //asks for currency/ticker
                choice2 = scanner.next().toUpperCase();
                double amount = r.exchangeCurrencyToUSD(choice2);
                if (amount == Integer.MIN_VALUE) { // minvalue is error
                    return;
                }
                System.out.println("Current USD to " + choice2 + " exchange rate is : " + amount);
            }
        }

    }

    public static void printChart(double[] data, String ticker) {
        System.out.println("TODAY'S PRICE OF $" + ticker); // prints graph header
        System.out.println(ASCIIGraph.fromSeries(data).plot()); // prints graph
    }
}
