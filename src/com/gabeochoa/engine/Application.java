package com.gabeochoa.engine;

import javax.swing.JFrame;

public class Application {

	public static void start(Game game)
	{
		JFrame frame = new JFrame(game.getTitle());
		frame.setSize(game.getWidth(), game.getHeight());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Canvas canvas = new Canvas(game);
		canvas.requestFocus();
		frame.add(canvas);
		frame.setVisible(true);
		
		Loop loop = new Loop(game, canvas);
		loop.start();
		
	}
}
