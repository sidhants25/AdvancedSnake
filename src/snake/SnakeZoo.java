package snake;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;

public class SnakeZoo {

    private LinkedList<Snake> zoo = new LinkedList<>();

    private HashMap<Snake, String> music = new HashMap<>();

    private HashMap<Snake, String> songName = new HashMap<>();

    public SnakeZoo() {
        Snake eddieLacy = new Snake(8, 69, Color.black, new Color(100, 10, 100), "Eddie Lacy");
        Snake lebron = new Snake(6, 73, Color.black, new Color(130, 112, 198), "Lebron");
        Snake sid = new Snake(7, 70, Color.black, new Color(6, 80, 211), "Sid");
        Snake messi = new Snake(5, 74, Color.black, new Color(255, 254, 10), "Messi");
        zoo.add(eddieLacy);
        zoo.add(lebron);
        zoo.add(sid);
        zoo.add(messi);
        // replace with your specific download location
        music.put(
                sid,
                "/Users/sidhantsrivastava/Downloads/hw09_local_temp/src" +
                        "/main/java/org/cis1200/snake/AudioFiles/metroboomin.wav"
        );
        music.put(
                eddieLacy,
                "/Users/sidhantsrivastava/Downloads/hw09_local_temp/src" +
                        "/main/java/org/cis1200/snake/AudioFiles/chanel.wav"
        );
        music.put(
                messi,
                "/Users/sidhantsrivastava/Downloads/hw09_local_temp/src" +
                        "/main/java/org/cis1200/snake/AudioFiles/driptoohard.wav"
        );
        music.put(
                lebron,
                "/Users/sidhantsrivastava/Downloads/hw09_local_temp/src" +
                        "/main/java/org/cis1200/snake/AudioFiles/popsmoke.wav"
        );
        songName.put(sid, "Superhero by Metro BOOMIN");
        songName.put(eddieLacy, "Chanel by Frank Ocean");
        songName.put(messi, "Drip Too Hard by Gunna");
        songName.put(lebron, "Dior by Pop Smoke");

    }

    public Snake pickSnake() {
        int s = (int) (Math.random() * zoo.size());
        return zoo.get(s);
    }

    public String getSong(Snake snake) {

        return music.get(snake);

    }

    public String getSongName(Snake snake) {
        return songName.get(snake);

    }

}
