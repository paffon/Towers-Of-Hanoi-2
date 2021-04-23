package main;

import TowersOfHanoi.TowersOfHanoi;
import display.MyFrame;

import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int discsAmount = 0;
        int minDiscs = 2;
        int maxDiscs = 10;
        while(discsAmount < minDiscs || discsAmount > maxDiscs) {
            discsAmount = getUserInput("How many discs? "+minDiscs+"-"+maxDiscs, sc);
        }


        TowersOfHanoi toh = new TowersOfHanoi(discsAmount);

        toh.solve();

        try {
            new MyFrame(toh);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int getUserInput(String string, Scanner sc) {
        System.out.println(string);
        return sc.nextInt();
    }
}
