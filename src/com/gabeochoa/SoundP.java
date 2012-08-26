package com.gabeochoa;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;

public enum SoundP{
   
	mainMenu("./sound/bu-random-cannons.wav"),
	moveSelection("./sound/menuHover.wav"),
	select("./sound/selectOption.wav"),
	walk("./sound/footsteps.wav");
	
	
    int position;
    String name;
    
  // Nested class for specifying volume
    public static enum Volume {
        MUTE, LOW, MEDIUM, HIGH
     }
  
  public static Volume volume = Volume.LOW;
  // Each sound effect has its own clip, loaded with its own sound file.
  private Clip clip;
  
  // Constructor to construct each element of the enum with its own sound file.
  SoundP(){}
  SoundP(String soundFileName) {
     try {
        // Use URL (instead of File) to read from disk and JAR.
        URL url = this.getClass().getClassLoader().getResource(soundFileName);
        // Set up an audio input stream piped from the sound file.
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
        // Get a clip resource.
        clip = AudioSystem.getClip();
        // Open audio clip and load samples from the audio input stream.
        clip.open(audioInputStream);
     } catch (UnsupportedAudioFileException e) {
        e.printStackTrace();
     } catch (IOException e) {
        e.printStackTrace();
     } catch (LineUnavailableException e) {
        e.printStackTrace();
     }
  }
  
  // Play or Re-play the sound effect from the beginning, by rewinding.
  public void play() {
     if (volume != Volume.MUTE) {
        if (clip.isRunning())
           clip.stop();   // Stop the player if it is still running
        clip.setFramePosition(-1); // rewind to the beginning
        clip.start();     // Start playing
     }
  }
  public void pause() {
     if (volume != Volume.MUTE) {
         if (!clip.isRunning()) {
        position = clip.getFramePosition();
        clip.setFramePosition(position);
        clip.start();
       }else{clip.stop();}
   } 
  }
   public void stop() {
     if (volume != Volume.MUTE) {
           clip.stop();   // Stop the player if it is still running
           clip.setFramePosition(-1); // rewind to the beginning
        // clip.start();     // Start playing
      } 
  }
  // Optional static method to pre-load all the sound files.
 static void init() {
     values(); // calls the constructor for all the elements
  }
}