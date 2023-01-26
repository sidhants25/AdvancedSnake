package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Board extends JPanel implements ActionListener {
    public static final int ROWS = 20;
    public static final int COLS = 20;
    public static final int CELL_SIZE = 25;
    public static final int BOARD_LENGTH = CELL_SIZE * COLS;
    public static final int BOARD_HEIGHT = CELL_SIZE * ROWS;

    private int score = 0;
    Timer timer;

    State state;

    Direction direction = Direction.RIGHT;

    boolean start = true;

    private Food food = new Food();

    private Apple apple = new Apple();

    private Mango mango = new Mango();

    private Food curr;
    SnakeZoo zoo = new SnakeZoo();
    Snake snake = zoo.pickSnake();
    private int gameSpeed = snake.getSpeed() + 10;

    public Board() {
        this.setPreferredSize(new Dimension(BOARD_LENGTH, BOARD_HEIGHT));
        this.setBackground(
                new Color(
                        10, 24, 54
                )
        );
        this.setFocusable(true);
        KeyMovement m = new KeyMovement();
        this.addKeyListener(m);
        runGame();
    }

    public static void reset() {
        SoundEffects.stopPlaying();
        new Frame();
    }

    public void runGame() {
        try {
            Thread.sleep(0); // for adding a delay in the future
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        state = State.Paused;
        timer = new Timer(getGameSpeed(), this);
        timer.start();
        randomFoodGenerator();
        SoundEffects.playBackgroundMusic(zoo.getSong(snake));
        Frame.setCurrSong(zoo.getSongName(snake));
        Frame.setCurrSnake(snake.getName());
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        drawGame(graphics);

    }

    public void drawGame(Graphics graphics) {
        if (state == State.Running || state == State.Paused) {
            // food
            graphics.setColor(getCurr().getColor());
            graphics.fillOval(getCurr().getXpos(), getCurr().getYpos(), CELL_SIZE, CELL_SIZE);
            // snake
            for (int b = 0; b < snake.getLength(); b++) {
                if (b == 0) {
                    graphics.setColor(snake.getHeadColor());

                } else {
                    graphics.setColor(snake.getBodyColor());
                }
                graphics.fillRect(snake.getXcords()[b], snake.getYcords()[b], CELL_SIZE, CELL_SIZE);
            }
            // score
            graphics.setColor(new Color(202, 241, 223));
            graphics.setFont(new Font("Century Gothic", Font.BOLD, 35));
            FontMetrics fm = getFontMetrics(graphics.getFont());
            String s = "Score: " + getScore();
            graphics.drawString(
                    s, (BOARD_LENGTH - fm.stringWidth(s)) / 2, graphics.getFont().getSize()
            );

        }

        if (state == State.Paused && !start) {
            graphics.setColor(new Color(202, 241, 223));
            graphics.setFont(new Font("Century Gothic", Font.BOLD, 20));
            FontMetrics fm5 = getFontMetrics(graphics.getFont());
            String pause = "GAME PAUSED. Press Space to Continue";
            graphics.drawString(
                    pause, (BOARD_LENGTH - fm5.stringWidth(pause)) / 2, Board.BOARD_HEIGHT / 2
            );
        }

        if (state == State.NotRunning) {
            endGame(graphics);
        }
    }

    public void movement() {
        for (int x = snake.getLength(); x > 0; x--) {
            snake.getXcords()[x] = snake.getXcords()[x - 1];
            snake.getYcords()[x] = snake.getYcords()[x - 1];
        }

        switch (direction) {
            case LEFT:
                snake.getXcords()[0] = snake.getXcords()[0] - CELL_SIZE;
                break;
            case RIGHT:
                snake.getXcords()[0] = snake.getXcords()[0] + CELL_SIZE;
                break;
            case UP:
                snake.getYcords()[0] = snake.getYcords()[0] - CELL_SIZE;
                break;
            case DOWN:
                snake.getYcords()[0] = snake.getYcords()[0] + CELL_SIZE;
                break;
            default:

        }

    }

    public void checkForCollisions() {
        if (snake.collision()) {
            SoundEffects.playSound(
                    "/Users/sidhantsrivastava/Downloads/" +
                            "hw09_local_temp/" +
                            "src/main/java/org/cis1200/snake/" +
                            "AudioFiles/bruh.wav"
            ).start();
            state = State.NotRunning;
            timer.stop();
        }
    }

    public void randomFoodGenerator() {
        double x = Math.random();
        if (x > 0.45) {
            getFood().generateFood();
            getMango().setXpos(-1);
            getMango().setYpos(-1);
            getApple().setXpos(-1);
            getApple().setYpos(-1);
            setCurr(getFood());
        } else if (x > 0.25 || snake.getLength() > 15) {
            getMango().generateFood();
            getFood().setXpos(-1);
            getFood().setYpos(-1);
            getApple().setXpos(-1);
            getApple().setYpos(-1);
            setCurr(getMango());
        } else {
            getApple().generateFood();
            getMango().setXpos(-1);
            getMango().setYpos(-1);
            getFood().setXpos(-1);
            getFood().setYpos(-1);
            setCurr(getApple());
        }
    }

    public void checkForFood() {
        if (snake.getXcords()[0] == getFood().getXpos()
                && snake.getYcords()[0] == getFood().getYpos()) {
            SoundEffects.playSound(
                    "/Users/sidhantsrivastava/Downloads" +
                            "/hw09_local_temp" +
                            "/src/main/java/org" +
                            "/cis1200/snake/AudioFiles/munch.wav"
            ).start();
            snake.setLength(snake.getLength() + 1);
            setScore(getScore() + 1);
            randomFoodGenerator();
        } else if (snake.getXcords()[0] == getApple().getXpos()
                && snake.getYcords()[0] == getApple().getYpos()) {
            SoundEffects.playSound(
                    "/Users/sidhantsrivastava/Downloads" +
                            "/hw09_local_temp" +
                            "/src/main/java/org" +
                            "/cis1200/snake/AudioFiles/munch.wav"
            ).start();
            direction = getApple().changeDirection(
                    snake.getXcords(), snake.getYcords(), snake.getLength(), direction
            );
            setScore(getScore() + 2);
            randomFoodGenerator();
            setScore(getApple().rottenApple(getScore()));
        } else if (snake.getXcords()[0] == getMango().getXpos()
                && snake.getYcords()[0] == getMango().getYpos()) {
            SoundEffects.playSound(
                    "/Users/sidhantsrivastava/Downloads" +
                            "/hw09_local_temp" +
                            "/src/main/java/org" +
                            "/cis1200/snake/AudioFiles/munch.wav"
            ).start();
            snake.setLength(snake.getLength() + 1);
            setGameSpeed(getGameSpeed() - getMango().speedBoost(getGameSpeed()));
            setScore(getMango().luckyMango(getScore()));
            randomFoodGenerator();

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (state == State.Running) {
            movement();
            checkForCollisions();
            checkForFood();

        }
        repaint();

    }

    public void endGame(Graphics graphics) {
        SoundEffects.stopPlaying();
        if (getScore() > HighScore.getHighScore()) {
            HighScore.setHighScore(getScore());
        }
        this.setBackground(Color.black);
        // score
        graphics.setColor(new Color(202, 241, 223));
        graphics.setFont(new Font("Century Gothic", Font.BOLD, 35));
        FontMetrics fm1 = getFontMetrics(graphics.getFont());
        String s = "Final Score: " + getScore();
        graphics.drawString(
                s, (BOARD_LENGTH - fm1.stringWidth(s)) / 2, graphics.getFont().getSize()
        );

        graphics.setColor(new Color(244, 215, 170));
        graphics.setFont(new Font("Century Gothic", Font.BOLD, 20));
        FontMetrics fm5 = getFontMetrics(graphics.getFont());
        String s5 = "High Score: " + HighScore.getHighScore();
        graphics.drawString(
                s5, (BOARD_LENGTH - fm5.stringWidth(s5)) / 2, BOARD_HEIGHT / 7
        );

        // losing message
        graphics.setColor(new Color(128, 0, 0));
        graphics.setFont(new Font("Century Gothic", Font.BOLD, 15));
        FontMetrics fm = getFontMetrics(graphics.getFont());
        String s1 = generateMessage();
        graphics.drawString(s1, (BOARD_LENGTH - fm.stringWidth(s1)) / 2, BOARD_HEIGHT / 4);

        // game summary message

        graphics.setColor(new Color(16, 107, 18));
        graphics.setFont(new Font("Century Gothic", Font.BOLD, 20));
        FontMetrics fm2 = getFontMetrics(graphics.getFont());
        String s2 = "Game Summary";
        graphics.drawString(s2, (BOARD_LENGTH - fm2.stringWidth(s2)) / 2, 170);

        // food array
        for (int i = 0; i < getFood().getSpawn().length; i++) {
            for (int j = 0; j < getFood().getSpawn()[i].length; j++) {
                if (getMango().getSpawn()[i][j]) {
                    graphics.setColor(getMango().getColor());
                } else if (getApple().getSpawn()[i][j]) {
                    graphics.setColor(getApple().getColor());
                } else if (getFood().getSpawn()[i][j]) {
                    graphics.setColor(getFood().getColor());
                } else {
                    graphics.setColor(new Color(184, 187, 192));
                }
                graphics.fillRect(
                        125 + (Board.CELL_SIZE / 2) * j, 200 + (Board.CELL_SIZE / 2) * i,
                        (Board.CELL_SIZE / 2), (Board.CELL_SIZE / 2)
                );
            }
        }

    }

    public String generateMessage() {
        String[] messages = new String[5];
        messages[0] = "Disappointing But Expected. Try Again.";
        messages[1] = "You Have Failed CIS 1200";
        messages[2] = "I've Seen Better Gameplay From Monkeys";
        messages[3] = "Who Even Are You?";
        messages[4] = "That Performance Was Worse Than The Food At Hill";

        int x = (int) (Math.random() * 5);
        return messages[x].toUpperCase();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Apple getApple() {
        return apple;
    }

    public void setApple(Apple apple) {
        this.apple = apple;
    }

    public Mango getMango() {
        return mango;
    }

    public void setMango(Mango mango) {
        this.mango = mango;
    }

    public Food getCurr() {
        return curr;
    }

    public void setCurr(Food curr) {
        this.curr = curr;
    }

    public int getGameSpeed() {
        return gameSpeed;
    }

    public void setGameSpeed(int gameSpeed) {
        this.gameSpeed = gameSpeed;
    }

    public class KeyMovement extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (start || state == State.Running || e.getKeyCode() == (KeyEvent.VK_SPACE)) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                    case KeyEvent.VK_D:
                        if (direction != Direction.LEFT) {
                            direction = Direction.RIGHT;
                        }
                        if (state == State.Paused && start) {
                            state = State.Running;
                            start = false;
                        }
                        break;
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_A:
                        if (direction != Direction.RIGHT) {
                            direction = Direction.LEFT;
                        }
                        break;
                    case KeyEvent.VK_W:
                    case KeyEvent.VK_UP:
                        if (direction != Direction.DOWN) {
                            direction = Direction.UP;
                        }
                        break;
                    case KeyEvent.VK_S:
                    case KeyEvent.VK_DOWN:
                        if (direction != Direction.UP) {
                            direction = Direction.DOWN;
                        }
                        if (state == State.Paused && start) {
                            state = State.Running;
                            start = false;
                        }
                        break;
                    case KeyEvent.VK_SPACE:
                        if (state == State.Running) {
                            state = State.Paused;
                        } else if (state == State.Paused) {
                            state = State.Running;
                        }
                        break;
                    default:
                }

            }
        }
    }
}
