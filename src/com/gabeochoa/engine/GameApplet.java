package com.gabeochoa.engine;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;

import com.gabeochoa.Evo;
public class GameApplet extends Applet {
	private static final long serialVersionUID = 1L;

	private Game game;
	private Loop loop;

	public void init() {
		setLayout(new BorderLayout());

		game = new Evo();
		
		Canvas canvas = new Canvas(game);
		canvas.requestFocus();
		add(canvas,BorderLayout.CENTER);
		setBackground(Color.black);
		
		loop = new Loop(game, canvas);
	}

	public void start() {
		loop.start();
	}

	public void stop() {
		this.destroy();
		loop.stopMe();
		loop.stop();
		System.exit(0);
	}
	public void update(){}
}