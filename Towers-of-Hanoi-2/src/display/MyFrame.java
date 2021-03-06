package display;

import TowersOfHanoi.TowersOfHanoi;

import javax.swing.*;

public class MyFrame extends JFrame {

    MyPanel panel;

    public MyFrame(TowersOfHanoi toh) {
        panel = new MyPanel(toh);

        this.setTitle("Towers Of Hanoi :: " + toh.amountOfDiscs + " discs");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
