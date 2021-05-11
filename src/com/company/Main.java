package com.company;

import com.mitchtalmadge.asciidata.graph.ASCIIGraph;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Requests r = new Requests();
        Scanner s = new Scanner(System.in);
        System.out.println("What ticker do you want to know the price for?");
        System.out.print("$");
        String ticker = s.next();
        double[] data = r.getDoubleDataForTicker(ticker);
        printChart(data , ticker);
    }

    public static void printChart(double[] data , String ticker){
        System.out.println("TODAY'S PRICE OF " + ticker);
        System.out.println(ASCIIGraph.fromSeries(data).plot());
    }
}
