package com.gabeochoa.Level;

import java.awt.Graphics;

import com.gabeochoa.Evo;

public class FourthLevel extends Level{

	Graphics g;
	
	
	public FourthLevel()
	{
		super();
		xSpawn = Evo.WIDTH/2;
		ySpawn = Evo.HEIGHT/2;
		lvlName = "first";
		
		bounds = new Bounds(0,Evo.WIDTH, 0, Evo.HEIGHT);
		levelLimit = 510;
		coinWorth = 10;
		timeLimit = 6;
	}
	
	public void loop(Graphics gr)
	{
		g = gr;
		super.loop(player, g);
		
	}
	
	public void paint(Graphics gr)
	{
		g = gr;
		super.paint(gr);
	}
	
	public void switchLevel(int id)
	{
		if(id == 2) game.switchLevel("Win", 2);
	}

	@Override
	public void checkWin() {
		
	}
}
