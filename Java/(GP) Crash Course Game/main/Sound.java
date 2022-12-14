package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * Sound class -manages sound of Crash Course Game.
 * @author - Mariah Bray, Dylan Howard, Thomas Jones
 * @version - 2/21/22
 */
public class Sound {
	
	/**
     * A method to play music during the game.
     * @return An integer representing success(0) or failure(1).
     */
    public int playMusic(){
        try{
            // Can cut out "src/" if doesn't work
            File musicPath = new File("Tours-Enthusiast.wav");
            
            if(musicPath.exists()){
            	AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            }
            else{
                System.out.println("Cant find file");
                return 1;
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return 0;
    }
}


