package gui;

import javax.lang.model.type.ArrayType;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Main {
    public char[][] map = new char[25][25];
    char active = ' ';

    public void execute(){
        JFrame frame = new JFrame();
        JPanel gridPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JButton playerButton = new JButton("Player");
        JButton playerOnGoalButton = new JButton("Player on Goal");
        JButton wallButton = new JButton("Wall");
        JButton boxButton = new JButton("Box");
        JButton greenBoxButton = new JButton("Green Box");
        JButton goalButton = new JButton("Goal");
        JButton delButton = new JButton("Delete");
        JButton startButton = new JButton("Start");

        for (int i = 0; i < 25; i++){
            for (int j = 0; j < 25; j++){
                this.map[i][j] = ' ';
            }
        }

        frame.setSize(680, 537);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

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
        rightPanel.setPreferredSize(new Dimension(180, 500));
        rightPanel.setLayout(new GridLayout(4,2,0,0));

        Dimension buttonSize = new Dimension(160, 80); // Set a fixed size for the buttons

        playerButton.setPreferredSize(buttonSize);
        playerButton.addActionListener(new ComponentButton(this, '@'));
        rightPanel.add(playerButton);

        playerOnGoalButton.setPreferredSize(buttonSize);
        playerOnGoalButton.addActionListener(new ComponentButton(this, '+'));
        rightPanel.add(playerOnGoalButton);

        wallButton.setPreferredSize(buttonSize);
        wallButton.addActionListener(new ComponentButton(this, '#'));
        rightPanel.add(wallButton);

        boxButton.setPreferredSize(buttonSize);
        boxButton.addActionListener(new ComponentButton(this, '$'));
        rightPanel.add(boxButton);

        greenBoxButton.setPreferredSize(buttonSize);
        greenBoxButton.addActionListener(new ComponentButton(this, '*'));
        rightPanel.add(greenBoxButton);

        goalButton.setPreferredSize(buttonSize);
        goalButton.addActionListener(new ComponentButton(this, '.'));
        rightPanel.add(goalButton);

        delButton.setPreferredSize(buttonSize);
        delButton.addActionListener(new ComponentButton(this, ' '));
        rightPanel.add(delButton);

        startButton.setPreferredSize(buttonSize);
        startButton.addActionListener(new StartButton(this));
        rightPanel.add(startButton);

        rightPanel.add(startButton);

        frame.add(gridPanel, BorderLayout.CENTER);
        frame.add(rightPanel, BorderLayout.EAST);
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

    public static class ComponentButton implements ActionListener {
        Main main;
        char component;

        public ComponentButton(Main main, char component) {
            this.main = main;
            this.component = component;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            this.main.active = this.component;
        }
    }

    public static class StartButton implements ActionListener {
        private final Main main;

        public StartButton(Main main) {
            this.main = main;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            for (char[] row: this.main.map){
                System.out.println(Arrays.toString(row));
            }
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.execute();
    }
}
