package com.gabeochoa.Menu;

import java.awt.Color;
import java.awt.Graphics;

import com.gabeochoa.Evo;
import com.gabeochoa.Font;
import com.gabeochoa.engine.Art;

public class Instructions extends Menu {

	
	public int delayLoops = 60;
	public Graphics g;
	public Font winsFont;
	
	public Instructions()
	{
		super();
		winsFont = new Font();
	}
	
	public void paint(Graphics gr)
	{
		g = gr;
		drawMenu();
	}
	@Override
	public void drawMenu() {
		
		g.drawImage(Art.getImage("./res/menu/black.png"), 0,0, Evo.WIDTH, Evo.HEIGHT,null);
		
		g.setColor(Color.white);
		String title = "Instructions";
		String[] lines = 
			{
				"WASD or Arrow Keys to Move",
				"and Space to push.",
				
				"Collect money to move up in",
				"the world."
			};
		
		winsFont.printLine(title, 40, 50, 13, g);
		winsFont.printLine("_____________", 40, 50, 25, g);
		
		for (int i=0; i<lines.length; i++) {
			winsFont.printLine(lines[i], 20, 50, 95+i*30, g);
		}
		
		if (delayLoops == 0)
			winsFont.printLine("- Continue", 20, 50, 290,g);
	
	
	}

	@Override
	public void loop(Evo game, boolean up, boolean down, boolean left,
			boolean right, boolean select) {
		
		if (delayLoops > 0)
			delayLoops--;
		else if (select) {
			game.setMenuTo(new topMenu(false));
		}
		
	}

}
