package snake;

public class HighScore {
    private static int highScore = 0;

    public static int getHighScore() {
        return highScore;
    }

    public static void setHighScore(int highScore) {
        HighScore.highScore = highScore;
    }
}
