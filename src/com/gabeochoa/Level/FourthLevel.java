package com.gabeochoa.Level;

import java.awt.Graphics;

import com.gabeochoa.Evo;
import com.gabeochoa.Sound;
import com.gabeochoa.Menu.WinMenu;

public class FourthLevel extends Level{

	Graphics g;
	
	
	public FourthLevel()
	{
		super();
		xSpawn = Evo.WIDTH/2;
		ySpawn = Evo.HEIGHT/2;
		lvlName = "fourth";
		
		bounds = new Bounds(0,Evo.WIDTH, 0, Evo.HEIGHT);
		coinWorth = 10;
		timeLimit = 20;
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

		if(player.wealth > levelLimit)
		{
			Sound.fourth.stop();
			game.setMenuTo(new WinMenu());
		}
	}
}
