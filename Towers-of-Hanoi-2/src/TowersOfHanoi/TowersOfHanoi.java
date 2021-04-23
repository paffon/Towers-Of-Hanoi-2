package TowersOfHanoi;

import display.ObjectType;
import objects.Disc;
import objects.OnScreenObject;
import objects.Rod;

import java.awt.*;
import java.util.ArrayList;

public class TowersOfHanoi {
    public Rod[] rods;
    public Disc[] discs;
    public int amountOfDiscs;
    public ArrayList<RodsTriple> solution;

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

        rods[0] = new Rod("0", ObjectType.ROD_FROM);
        rods[1] = new Rod("1", ObjectType.ROD_HELPER);
        rods[2] = new Rod("2", ObjectType.ROD_TO);
    }

    private void initDiscs() {
        discs = new Disc[amountOfDiscs]; // discs are 0 to n-1
        for(int i=0; i < amountOfDiscs; i++) {
            // placing all discs on the first rod
            discs[i] = new Disc(rods[0], i, ObjectType.DISC);
            rods[0].push(discs[i]);
        }
    }

    public void paintObjectsArray(OnScreenObject[] objects, Color color) {
        for(OnScreenObject object : objects) {
            object.color = color;
        }
    }

}
