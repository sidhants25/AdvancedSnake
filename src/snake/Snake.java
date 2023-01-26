package snake;

import java.awt.*;

public class Snake {
    private int[] xcords;
    private int[] ycords;
    private int length;
    private Color headColor;
    private Color bodyColor;

    private int speed;
    private final String name;

    public Snake(int l, int s, Color head, Color body, String name) {
        setXcords(new int[Board.ROWS * Board.COLS]);
        setYcords(new int[Board.ROWS * Board.COLS]);
        setLength(l);
        setSpeed(s);
        setHeadColor(head); // Color.black;
        setBodyColor(body); // new Color(0, 102, 0);
        this.name = name;
    }

    public boolean collision() {
        // board collisions
        if (getXcords()[0] < 0 || getXcords()[0] > Board.BOARD_LENGTH - Board.CELL_SIZE
                || getYcords()[0] < 0
                || getYcords()[0] > Board.CELL_SIZE * (Board.ROWS - 1) - Board.CELL_SIZE) {
            return true;
        }
        // collisions with itself
        for (int i = getLength(); i > 0; i--) {
            if (getXcords()[i] == getXcords()[0] && getYcords()[i] == getYcords()[0]) {
                return true;
            }
        }
        return false;

    }

    public int[] getXcords() {
        return xcords;
    }

    public void setXcords(int[] xcords) {
        this.xcords = xcords;
    }

    public int[] getYcords() {
        return ycords;
    }

    public void setYcords(int[] ycords) {
        this.ycords = ycords;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Color getHeadColor() {
        return headColor;
    }

    public void setHeadColor(Color headColor) {
        this.headColor = headColor;
    }

    public Color getBodyColor() {
        return bodyColor;
    }

    public void setBodyColor(Color bodyColor) {
        this.bodyColor = bodyColor;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getName() {
        return name;
    }
}
