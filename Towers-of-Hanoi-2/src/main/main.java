package main;

import TowersOfHanoi.TowersOfHanoi;
import display.MyFrame;

import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        System.out.println("Hello world");

        Scanner sc = new Scanner(System.in);

        int discsAmount = 0;
        while(discsAmount < 1 || discsAmount > 10) {
            discsAmount = getUserInput("How many discs? 1-10", sc);
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
