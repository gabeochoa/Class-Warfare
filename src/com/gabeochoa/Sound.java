package com.gabeochoa;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
	public static final Sound mainMenu = new Sound("/bu-random-cannons.wav");
	public static final Sound moveSelection = new Sound("/menuHover.wav");
	public static final Sound select = new Sound("/selectOption.wav");
	public static final Sound walk = new Sound("/footsteps.wav");
	

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
		this.stop();
	}
}