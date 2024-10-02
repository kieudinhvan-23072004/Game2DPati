package org.example;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound {
    Clip clip;
    File[] file = new File[30];

    public Sound() {
        file[0] = new File("data/BlueBoyAdventure.wav");
        file[1] = new File("data/coin.wav");
        file[2] = new File("data/powerup.wav");
        file[3] = new File("data/unlock.wav");
        file[4] = new File("data/fanfare.wav");
        file[5] = new File("data/Goodnightmare.wav");
    }

    public void setFile(int i){
        try{
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file[i]);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
}
