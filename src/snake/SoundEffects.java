package snake;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundEffects {

    private static boolean isPlaying;

    private static Clip music;

    public static Clip playSound(String filePath) {
        try {
            File soundFile = new File(filePath);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            AudioFormat format = audioIn.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioIn);
            return clip;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Error playing sound: " + e.getMessage());
        }

        return null;
    }

    public static void playBackgroundMusic(String s) {
        if (!isIsPlaying()) {
            music = playSound(s);
            music.start();
            music.loop(Clip.LOOP_CONTINUOUSLY);
            setIsPlaying(true);
        }
    }

    public static void stopPlaying() {
        music.stop();
        setIsPlaying(false);
    }

    public static boolean isIsPlaying() {
        return isPlaying;
    }

    public static void setIsPlaying(boolean isPlaying) {
        SoundEffects.isPlaying = isPlaying;
    }
}
