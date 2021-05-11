package com.company;

import com.mitchtalmadge.asciidata.graph.ASCIIGraph;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Requests r = new Requests();
        Scanner scanner = new Scanner(System.in);
        System.out.println("What ticker do you want to know the price for?");
        System.out.print("$"); //asks user for ticker
        String ticker = scanner.next();
        double[] data = r.getDoubleDataForTicker(ticker);
        if(data[0] == Integer.MIN_VALUE){ // minvalue is error
            return;
        }
        printChart(data , ticker);
    }

    public static void printChart(double[] data , String ticker){
        System.out.println("TODAY'S PRICE OF $" + ticker.toUpperCase()); // prints graph header
        System.out.println(ASCIIGraph.fromSeries(data).plot()); // prints graph
    }
}
