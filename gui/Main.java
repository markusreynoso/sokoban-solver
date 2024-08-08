package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public char[][] map = new char[25][25];
    char active = ' ';

    public void execute(){
        JFrame frame = new JFrame();
        JPanel gridPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JButton playerButton = new JButton();
        JButton wallButton = new JButton();
        JButton boxButton = new JButton();

        for (int i = 0; i < 25; i++){
            for (int j = 0; j < 25; j++){
                this.map[i][j] = ' ';
            }
        }
        frame.setSize(680   ,537);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        gridPanel.setBackground(Color.black);
        gridPanel.setSize(500,500);
        gridPanel.setLayout(new GridLayout(25,25, 0,0));

        for (int i = 0; i <= 24; i++){
            for (int j = 0; j <= 24; j++){
                JButton button = new JButton();
                button.setBackground(Color.black);
                button.addActionListener(new GridButton(this, i, j));
                gridPanel.add(button);
            }
        }

        rightPanel.setBackground(Color.cyan);
        rightPanel.setSize(180,500);
        rightPanel.setLayout(new GridLayout(6,1,0,0));
        frame.add(gridPanel);
        frame.add(rightPanel);
        frame.setVisible(true);
    }



    public static class GridButton implements ActionListener {
        private final int row;
        private final int col;
        private final Main main;

        public GridButton(Main main, int row, int col) {
            this.row = row;
            this.col = col;
            this.main = main;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            this.main.map[row][col] = this.main.active;
        }
    }

    public static class componentButton implements ActionListener{
        Main main;
        char component;

        public componentButton(Main main, char component) {
            this.main = main;
            this.component = component;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            this.main.active = this.component;
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.execute();
    }
}
