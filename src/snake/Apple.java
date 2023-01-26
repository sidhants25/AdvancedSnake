package snake;

import java.awt.*;

public class Apple extends Food {
    public Apple() {
        super();
        setColor(new Color(226, 10, 10));

    }

    public Direction changeDirection(int[] x, int[] y, int size, Direction d) {
        int ind = size - 1;
        for (int i = 0; i <= ind / 2; i++) {
            int temp = x[i];
            x[i] = x[ind - i];
            x[ind - i] = temp;

            int temp2 = y[i];
            y[i] = y[ind - i];
            y[ind - i] = temp2;
        }

        switch (d) {
            case LEFT -> d = Direction.RIGHT;
            case RIGHT -> d = Direction.LEFT;
            case UP -> d = Direction.DOWN;
            case DOWN -> d = Direction.UP;
            default -> {
            }

        }

        return d;

    }

    public int rottenApple(int score) {
        if (Math.random() > 0.95) {
            score = 0;
        }
        return score;
    }
}
