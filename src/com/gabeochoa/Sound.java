package com.gabeochoa;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
	public static final Sound mainMenu = new Sound("/bu-random-cannons.wav");
	public static final Sound moveSelection = new Sound("/menuHover.wav");
	public static final Sound select = new Sound("/selectOption.wav");
	public static final Sound walk = new Sound("/footsteps.wav");
	
	public static final Sound first = new Sound("/bu-the-invisible-brother.wav");
	public static final Sound second = new Sound("/bu-a-years-path.wav");
	public static final Sound third = new Sound("/bu-a-jealous-tofu.wav");
	public static final Sound fourth = new Sound("/bu-sacred-and-blue.wav");
	
	public static final Sound endGame = new Sound("/bu-on-the-oceans-sister.wav");	

	private AudioClip clip;

	private Sound(String name) {
		try {
			clip = Applet.newAudioClip(Sound.class.getResource(name));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void play() {
		try {
			new Thread() {
				public void run() {
					clip.play();
				}
			}.start();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		clip.stop();
	}
}