package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SokobanGUI extends JPanel {
    private final int GRID_SIZE = 25;
    private final int TILE_SIZE = 30;
    private final char[][] map;
    private final String moves;
    private int moveIndex = 0;

    public SokobanGUI(char[][] map, String moves) {
        this.map = map;
        this.moves = moves;
        setPreferredSize(new Dimension(GRID_SIZE * TILE_SIZE, GRID_SIZE * TILE_SIZE));
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawMap(g);
    }

    private void drawMap(Graphics g) {
        for (int y = 0; y < GRID_SIZE; y++) {
            for (int x = 0; x < GRID_SIZE; x++) {
                char c = this.map[y][x];
                Color color = getColorForChar(c);
                g.setColor(color);
                g.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }
    }

    private Color getColorForChar(char c) {
        return switch (c) {
            case '#' -> new Color(0xff6150);
            case '.' -> new Color(0xffcb00);
            case ' ' -> new Color(0xf0f0f0);
            case '@' -> new Color(0x072448);
            case '+' -> new Color(0x54d2d2);
            case '$' -> new Color(0xf8aa4b);
            case '*' -> new Color(0x54d2a0);
            default -> Color.WHITE;
        };
    }

    public void animateMoves() {
        Timer timer = new Timer(300, e -> {
            if (moveIndex < moves.length()) {
                applyMove(moves.charAt(moveIndex));
                repaint();
                moveIndex++;
            } else {
                ((Timer) e.getSource()).stop();
            }
        });
        timer.start();
    }

    private void applyMove(char move) {
        int oldPlayerY = 0;
        int oldPlayerX = 0;
        for (int y = 0; y < GRID_SIZE; y++) {
            for (int x = 0; x < GRID_SIZE; x++) {
                if (this.map[y][x] == '@' || this.map[y][x] == '+') {
                    oldPlayerY = y;
                    oldPlayerX = x;
                }
            }
        }

        int newY = oldPlayerY;
        int newX = oldPlayerX;
        char currentPlayerNotation = this.map[oldPlayerY][oldPlayerX];
        char destination;

        switch (move) {
            case 'u' -> newY--;
            case 'd' -> newY++;
            case 'l' -> newX--;
            case 'r' -> newX++;
        }

        if (newY < 0 || newY >= GRID_SIZE || newX < 0 || newX >= GRID_SIZE) {
            return;
        }

        destination = this.map[newY][newX];

        if (destination == '$' || destination == '*') {
            int boxY = newY;
            int boxX = newX;

            switch (move) {
                case 'u' -> { boxY--; }
                case 'd' -> { boxY++; }
                case 'l' -> { boxX--; }
                case 'r' -> { boxX++; }
            }

            if (boxY >= 0 && boxY < GRID_SIZE && boxX >= 0 && boxX < GRID_SIZE) {
                char boxDestination = this.map[boxY][boxX];
                if (boxDestination == ' ' || boxDestination == '.') {
                    this.map[oldPlayerY][oldPlayerX] = (currentPlayerNotation == '+') ? '.' : ' ';
                    this.map[newY][newX] = (destination == '$') ? '@' : '+';
                    this.map[boxY][boxX] = (boxDestination == ' ') ? '$' : '*';
                }
            }
        } else if (destination == '.') {
            this.map[oldPlayerY][oldPlayerX] = (currentPlayerNotation == '+') ? '.' : ' ';
            this.map[newY][newX] = '+';
        } else if (destination == ' ') {
            this.map[oldPlayerY][oldPlayerX] = (currentPlayerNotation == '+') ? '.' : ' ';
            this.map[newY][newX] = '@';
        }
    }

}
