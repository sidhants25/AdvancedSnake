package snake;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    private static boolean launch;
    private static String currSong;

    private static String currSnake;

    public Frame() {
        if (!isLaunch()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Take Note of the Following Instructions: \n" +
                            "1. Use WASD/Arrow Keys to Move. Press Space to Pause \n" +
                            "2. There are 3 types of food. \n" +
                            "   I. Pink-colored food increase score and length by one. \n" +
                            "   II. Red-colored apples increase score by" +
                            " two, reverse your direction and have a"
                            + "5% chance to reset your score.  \n"
                            + "       Apples will stop spawning after a while. \n"
                            + " III. Orange-colored mangoes don't add any points"
                            + " but have a 30% chance of doubling your score. \n"
                            + "      They also give you a speed " +
                            "boost and increase length by one."
                            + "\n" +
                            "3. Avoid hitting walls and yourself. \n"
                            + "    At the end of the game, you'll get a summary of your match"
                            +
                            "including food spawn locations.  \n" +
                            "4. Enjoy the music and have fun playing Sid's Snake! ",

                    "Welcome to Sid's Snake!",
                    JOptionPane.PLAIN_MESSAGE
            );
        }
        setLaunch(true);
        Board board = new Board();
        board.setLayout(new BorderLayout());
        final JPanel control_panel = new JPanel();
        board.add(control_panel, BorderLayout.SOUTH);
        final JButton reset = new JButton("Play Again");
        reset.addActionListener(e -> Board.reset());
        JLabel snakeLabel = new JLabel("Snake: " + getCurrSnake());
        control_panel.add(snakeLabel);
        control_panel.add(reset);
        JLabel songLabel = new JLabel("Now Playing: " + getCurrSong());
        control_panel.add(songLabel);
        this.add(board);
        this.setTitle("Sid's Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
    }

    public static boolean isLaunch() {
        return launch;
    }

    public static void setLaunch(boolean launch) {
        Frame.launch = launch;
    }

    public static String getCurrSong() {
        return currSong;
    }

    public static void setCurrSong(String currSong) {
        Frame.currSong = currSong;
    }

    public static String getCurrSnake() {
        return currSnake;
    }

    public static void setCurrSnake(String currSnake) {
        Frame.currSnake = currSnake;
    }
}
