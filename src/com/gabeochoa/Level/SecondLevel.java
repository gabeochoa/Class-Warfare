package com.gabeochoa.Level;

import java.awt.Graphics;

import com.gabeochoa.Evo;
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
		levelLimit = 510;
		coinWorth = 10;
	}
	
	public void loop(Graphics gr)
	{
		g = gr;
		super.loop(player, g);
		checkWin();
		
	}
	
	private void drawBackground() {
		g.drawImage(Art.getImage("./res/level/"+lvlName+".png"),0, 0, Evo.WIDTH, Evo.HEIGHT,null);
	}
	
	public void paint(Graphics gr)
	{
		g = gr;
		super.paint(gr);
	}
	
	public void checkWin() {

		if(player.wealth > levelLimit)
		{
			switchLevel(2);
		}
	}
	
	public void switchLevel(int id)
	{
		if(id == 2) game.switchLevel("Third", 2);
	}
}
