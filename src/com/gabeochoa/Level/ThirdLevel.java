package com.gabeochoa.Level;

import java.awt.Graphics;

import com.gabeochoa.Evo;
import com.gabeochoa.engine.Art;

public class ThirdLevel extends Level{

	Graphics g;
	
	
	public ThirdLevel()
	{
		super();
		xSpawn = Evo.WIDTH/2;
		ySpawn = Evo.HEIGHT/2;
		lvlName = "third";
		
		bounds = new Bounds(0,Evo.WIDTH, 0, Evo.HEIGHT);
		levelLimit = 510;
		coinWorth = 5;
		timeLimit = 12;
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
		if(id == 2) game.switchLevel("Fourth", 2);
	}

	@Override
	public void checkWin() {

		if(player.wealth > levelLimit)
		{
			switchLevel(2);
		}
	}
}
