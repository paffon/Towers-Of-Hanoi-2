package main;

import TowersOfHanoi.TowersOfHanoi;

import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        System.out.println("Hello world");

        Scanner sc = new Scanner(System.in);

        int discsAmount = getUserInput("How many discs?", sc);

        TowersOfHanoi toh = new TowersOfHanoi(discsAmount);

        toh.printSolutions();

        toh.solve();

        toh.printSolutions();
    }

    private static int getUserInput(String string, Scanner sc) {
        System.out.println(string);
        return sc.nextInt();
    }
}
