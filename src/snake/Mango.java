package snake;

import java.awt.*;

public class Mango extends Food {

    public Mango() {
        super();
        setColor(new Color(255, 183, 10));

    }

    public int speedBoost(int speed) {
        return speed + 2;
    }

    public int luckyMango(int score) {
        double x = Math.random();
        if (x > 0.7) {
            score *= 2;
        }
        return score;

    }
}
