package gui;

import solver.Bot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class MapEditor {
    public char[][] map = new char[25][25];
    char active = ' ';

    public void execute(){
        JFrame frame = new JFrame("Map Maker");
        JPanel gridPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JButton playerButton = new JButton();
        JButton playerOnGoalButton = new JButton();
        JButton wallButton = new JButton();
        JButton boxButton = new JButton();
//        JButton boxButton = new JButton("<html><div style='text-align: center;'>Box</div></html>");
        JButton greenBoxButton = new JButton();
        JButton goalButton = new JButton();
        JButton delButton = new JButton("<html><div style='text-align: center;'>Delete</div></html>");
        JButton startButton = new JButton("<html><div style='text-align: center;'>Start</div></html>");

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
        frame.setBackground(new Color(0xf0f0f0));

        gridPanel.setBackground(Color.black);
        gridPanel.setSize(500,500);
        gridPanel.setLayout(new GridLayout(25,25, 0,0));
        gridPanel.setBackground(new Color(0xf0f0f0));

        for (int i = 0; i <= 24; i++){
            for (int j = 0; j <= 24; j++){
                JButton button = new JButton();
                button.setBorder(BorderFactory.createEmptyBorder());
                button.setBackground(new Color(0xf0f0f0));
                button.addActionListener(new GridButton(this, i, j));
                gridPanel.add(button);
            }
        }

        rightPanel.setBackground(Color.cyan);
        rightPanel.setPreferredSize(new Dimension(180, 500));
        rightPanel.setLayout(new GridLayout(4,2,0,0));

        Dimension buttonSize = new Dimension(160, 80);

        ImageIcon playerIcon = new ImageIcon("assets/player.png");
        playerButton.setIcon(playerIcon);
        playerButton.setPreferredSize(buttonSize);
        playerButton.setBackground(new Color(0x072448));
        playerButton.setForeground(new Color(0xf0f0f0));
        playerButton.setHorizontalAlignment(SwingConstants.CENTER);
        playerButton.setVerticalAlignment(SwingConstants.CENTER);
        playerButton.setMargin(new Insets(10, 10, 10, 10));
        playerButton.addActionListener(new ComponentButton(this, '@'));
        rightPanel.add(playerButton);

        ImageIcon playerGoalIcon = new ImageIcon("assets/playergoal.png");
        playerOnGoalButton.setIcon(playerGoalIcon);
        playerOnGoalButton.setPreferredSize(buttonSize);
        playerOnGoalButton.setBackground(new Color(0x54d2d2));
        playerOnGoalButton.setForeground(new Color(0xf0f0f0));
        playerOnGoalButton.setHorizontalAlignment(SwingConstants.CENTER);
        playerOnGoalButton.setVerticalAlignment(SwingConstants.CENTER);
        playerOnGoalButton.setMargin(new Insets(10, 10, 10, 10));
        playerOnGoalButton.addActionListener(new ComponentButton(this, '+'));
        rightPanel.add(playerOnGoalButton);

        ImageIcon wallIcon = new ImageIcon("assets/brick.png");
        wallButton.setIcon(wallIcon);
        wallButton.setPreferredSize(buttonSize);
        wallButton.setBackground(new Color(0xff6150));
        wallButton.setForeground(new Color(0xf0f0f0));
        wallButton.setHorizontalAlignment(SwingConstants.CENTER);
        wallButton.setVerticalAlignment(SwingConstants.CENTER);
        wallButton.setMargin(new Insets(10, 10, 10, 10));
        wallButton.addActionListener(new ComponentButton(this, '#'));
        rightPanel.add(wallButton);

        ImageIcon boxIcon = new ImageIcon("assets/box.png");
        boxButton.setIcon(boxIcon);
        boxButton.setPreferredSize(buttonSize);
        boxButton.setBackground(new Color(0xf8aa4b));
        boxButton.setForeground(new Color(0xf0f0f0));
        boxButton.setHorizontalAlignment(SwingConstants.CENTER);
        boxButton.setVerticalAlignment(SwingConstants.CENTER);
        boxButton.setMargin(new Insets(10, 10, 10, 10));
        boxButton.addActionListener(new ComponentButton(this, '$'));
        rightPanel.add(boxButton);

        ImageIcon greenBoxIcon = new ImageIcon("assets/greenbox.png");
        greenBoxButton.setIcon(greenBoxIcon);
        greenBoxButton.setPreferredSize(buttonSize);
        greenBoxButton.setBackground(new Color(0x54d2a0));
        greenBoxButton.setForeground(new Color(0xf0f0f0));
        greenBoxButton.setHorizontalAlignment(SwingConstants.CENTER);
        greenBoxButton.setVerticalAlignment(SwingConstants.CENTER);
        greenBoxButton.setMargin(new Insets(10, 10, 10, 10));
        greenBoxButton.addActionListener(new ComponentButton(this, '*'));
        rightPanel.add(greenBoxButton);

        ImageIcon goalIcon = new ImageIcon("assets/goal.png");
        goalButton.setIcon(goalIcon);
        goalButton.setPreferredSize(buttonSize);
        goalButton.setBackground(new Color(0xffcb00));
        goalButton.setForeground(new Color(0xf0f0f0));
        goalButton.setHorizontalAlignment(SwingConstants.CENTER);
        goalButton.setVerticalAlignment(SwingConstants.CENTER);
        goalButton.setMargin(new Insets(10, 10, 10, 10));
        goalButton.addActionListener(new ComponentButton(this, '.'));
        rightPanel.add(goalButton);

        delButton.setPreferredSize(buttonSize);
        delButton.setBackground(new Color(0x2E2E2E));
        delButton.setForeground(new Color(0xf0f0f0));
        delButton.setHorizontalAlignment(SwingConstants.CENTER);
        delButton.setVerticalAlignment(SwingConstants.CENTER);
        delButton.setMargin(new Insets(10, 10, 10, 10));
        delButton.addActionListener(new ComponentButton(this, ' '));
        rightPanel.add(delButton);

        startButton.setPreferredSize(buttonSize);
        startButton.setBackground(new Color(0x2E2E2E));
        startButton.setForeground(new Color(0xf0f0f0));
        startButton.setHorizontalAlignment(SwingConstants.CENTER);
        startButton.setVerticalAlignment(SwingConstants.CENTER);
        startButton.setMargin(new Insets(10, 10, 10, 10));
        startButton.addActionListener(new StartButton(this));
        rightPanel.add(startButton);

        Font font = new Font("Arial", Font.BOLD, 12);
        for (Component comp : rightPanel.getComponents()) {
            if (comp instanceof JButton) {
                comp.setFont(font);
            }
        }

        frame.add(gridPanel, BorderLayout.CENTER);
        frame.add(rightPanel, BorderLayout.EAST);
        frame.setVisible(true);
    }

    public static class GridButton implements ActionListener {
        private final int row;
        private final int col;
        private final MapEditor main;

        public GridButton(MapEditor main, int row, int col) {
            this.row = row;
            this.col = col;
            this.main = main;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            this.main.map[row][col] = this.main.active;
            Object source = e.getSource();

            switch (this.main.active) {
                case '@' -> // Player
                        ((JButton) source).setBackground(new Color(0x072448));
                case '+' -> // Player on goal
                        ((JButton) source).setBackground(new Color(0x54d2d2));
                case '#' -> // Wall
                        ((JButton) source).setBackground(new Color(0xff6150));
                case '$' -> // Box
                        ((JButton) source).setBackground(new Color(0xf8aa4b));
                case '*' -> // Box on goal
                        ((JButton) source).setBackground(new Color(0x54d2a0));
                case '.' -> // Goal
                        ((JButton) source).setBackground(new Color(0xffcb00));
                case ' ' -> {
                    ((JButton) source).setBackground(new Color(0xf0f0f0));
                    ;
                }
            }

        }
    }

    public static class ComponentButton implements ActionListener {
        MapEditor main;
        char component;

        public ComponentButton(MapEditor main, char component) {
            this.main = main;
            this.component = component;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            this.main.active = this.component;
        }
    }

    public char[][] copyMap(char[][] original) {
        if (original == null) {
            return null;
        }

        char[][] copy = new char[original.length][];
        for (int i = 0; i < original.length; i++) {
            copy[i] = new char[original[i].length];
            System.arraycopy(original[i], 0, copy[i], 0, original[i].length);
        }
        return copy;
    }

    public static String mapToString(char[][] map) {
        StringBuilder sb = new StringBuilder();
        for (char[] row : map) {
            sb.append(row);
        }
        return sb.toString();
    }

    public static class StartButton implements ActionListener {
        private HashMap<String, String> solutions = new HashMap<>();
        private final MapEditor main;

        public StartButton(MapEditor main) {
            this.main = main;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            char[][] mapCopy = main.copyMap(this.main.map);
            String mapId = mapToString(mapCopy);
            String moveSequence;

            if (!this.solutions.containsKey(mapId)){
                Bot bot = new Bot();
                moveSequence = bot.solveSokobanPuzzle(this.main.map);
                this.solutions.put(mapId, moveSequence);
            }
            else{
                moveSequence = this.solutions.get(mapId);
            }

            JFrame animationFrame = new JFrame("Solution");
            SokobanGUI gui = new SokobanGUI(mapCopy, moveSequence);
            animationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            animationFrame.add(gui);
            animationFrame.pack();
            animationFrame.setVisible(true);

            gui.animateMoves();
            animationFrame.setLocationRelativeTo(null);
        }
    }

    public static void main(String[] args) {
        MapEditor main = new MapEditor();
        main.execute();
    }
}
