package com.gabeochoa.engine;

import java.awt.Graphics;

import javax.swing.JComponent;

public class Canvas extends JComponent{

	private Game game;
	
	public Canvas(Game game)
	{
		this.game = game;
		addKeyListener(this.game);
		addMouseListener(this.game);
		addMouseMotionListener(this.game);
		this.setFocusable(true);
	}
	
	public void paintComponent(Graphics g)
	{
		game.draw(g);
		game.send(g);
	}
}
