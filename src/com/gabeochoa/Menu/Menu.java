package com.gabeochoa.Menu;

import java.awt.Graphics;

import com.gabeochoa.Evo;
import com.gabeochoa.engine.Game;

public abstract class Menu {

	public int choice = 0;
	
	public Graphics g;
	
	public void paint(Graphics gr)
	{
		g = gr;
		drawMenu();
	}
	
	public abstract void drawMenu();
	
	public abstract void loop(Evo game,  boolean up, boolean down, boolean left, boolean right, boolean select);
	
}
