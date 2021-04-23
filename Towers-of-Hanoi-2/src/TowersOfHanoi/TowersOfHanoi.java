package TowersOfHanoi;

import objects.Disc;
import objects.OnScreenObject;
import objects.Rod;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class TowersOfHanoi {
    Rod[] rods;
    Disc[] discs;
    int amountOfDiscs;
    ArrayList<RodsTriple> solution;

    public TowersOfHanoi(int amountOfDiscs) {
        this.amountOfDiscs = amountOfDiscs;

        initRods();
        initDiscs();

        paintObjectsArray(rods, Color.GRAY);
        paintObjectsArray(discs, Color.WHITE);

        solution = new ArrayList<>();
    }

    public void printSolutions() {
        int i=1;
        for(RodsTriple rt: solution) {
            System.out.println(i++ + ". " + rt);
        }
    }

    public void solve() {
        System.out.println("Solving...\n");
        solve(rods[0], rods[1], rods[2], amountOfDiscs);
    }

    // moving n discs from rodA to rodC via rodB
    private void solve(Rod rodA, Rod rodB, Rod rodC, int n) {
        if(n > 0) {
            solve(rodA, rodC, rodB, n-1);
            solution.add(new RodsTriple(rodA, rodB, rodC));
            solve(rodB, rodA, rodC, n-1);
        }
    }

    private void initRods() {
        rods = new Rod[3];
        for(int i=0; i<3; i++) {
            rods[i] = new Rod(Integer.toString(i));
        }
    }

    private void initDiscs() {
        discs = new Disc[amountOfDiscs]; // discs are 0 to n-1
        for(int i=0; i < amountOfDiscs; i++) {
            // placing all discs on the first rod
            discs[i] = new Disc(rods[0], i);
            rods[0].push(discs[i]);
        }
    }

    public void paintObjectsArray(OnScreenObject[] objects, Color color) {
        for(OnScreenObject object : objects) {
            object.color = color;
        }
    }

}
