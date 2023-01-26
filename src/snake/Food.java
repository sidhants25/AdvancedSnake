package snake;

import java.awt.*;
import java.util.Random;

public class Food {
    private int xpos;
    private int ypos;
    private Random foodPlacer = new Random();

    private boolean[][] spawn = new boolean[Board.ROWS][Board.COLS];

    private Color color;

    public Food() {
        setXpos(0);
        setYpos(0);
        setColor(new Color(244, 170, 170));
    }

    public void generateFood() {
        setXpos(foodPlacer.nextInt(Board.COLS) * Board.CELL_SIZE);
        setYpos(foodPlacer.nextInt(Board.ROWS - 2) * Board.CELL_SIZE);
        spawn[getYpos() / Board.CELL_SIZE][getXpos() / Board.CELL_SIZE] = true;
    }

    public boolean[][] getSpawn() {
        boolean[][] copy = new boolean[spawn.length][];
        for (int i = 0; i < spawn.length; i++) {
            copy[i] = spawn[i].clone();
        }
        return copy;
    }

    public int getXpos() {
        return xpos;
    }

    public void setXpos(int xpos) {
        this.xpos = xpos;
    }

    public int getYpos() {
        return ypos;
    }

    public void setYpos(int ypos) {
        this.ypos = ypos;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
