package com.gabeochoa.Level;

import java.awt.Graphics;

import com.gabeochoa.Evo;
import com.gabeochoa.Sound;
import com.gabeochoa.engine.Art;

public class SecondLevel extends Level{

	Graphics g;
	
	public SecondLevel()
	{
		super();
		xSpawn = Evo.WIDTH/2;
		ySpawn = Evo.HEIGHT/2;
		lvlName = "second";
		
		bounds = new Bounds(0,Evo.WIDTH, 0, Evo.HEIGHT);
		coinWorth = 10;
		timeLimit = 40;
	}
	
	public void loop(Graphics gr)
	{
		g = gr;
		super.loop(player, g);
		checkWin();
		
	}
	
	
	public void paint(Graphics gr)
	{
		g = gr;
		super.paint(gr);
	}
	
	public void checkWin() {

		if(player.wealth > levelLimit)
		{
			//Sound.second.stop();
			switchLevel(2);
		}
	}
	
	public void switchLevel(int id)
	{
		if(id == 2) game.switchLevel("third", 2);
	}
}
