package com.gabeochoa.Menu;

import java.awt.Graphics;

import com.gabeochoa.Evo;
import com.gabeochoa.Font;
import com.gabeochoa.Sound;
import com.gabeochoa.engine.Art;
import com.gabeochoa.engine.Game;

public class LossMenu extends Menu {
	
	public String[] choices = {"Main Menu", "Quit"};
	public String finish;
	
	public int choice = 0;
	public Graphics g;
	
	public Font winsFont;
	private String words;
	public String level;
	
	public LossMenu()
	{
		super();
		winsFont = new Font();
	}
	
	public LossMenu(String s)
	{
		super();
		winsFont = new Font();
		level = s;
	}
	
	public void paint(Graphics gr)
	{
		g = gr;
		drawMenu();
	}
	
	public void drawMenu()
	{
		g.drawImage(Art.getImage("/menu/black.png"), 0,0, Evo.WIDTH, Evo.HEIGHT,null);
		
		//for gif, have to use imageIcon
		
		winsFont.printLine("You ran out of time", 30, 37, 20, g);

		
		for (int i=0; i<choices.length; i++) {
			words = choices[i];
			if(choice == i)
				winsFont.printLine("-", ( 50 ), 90 , 90 + i*50,g);
			winsFont.printLine(words, 35, (Evo.WIDTH/4 - 20), 100 + i*50, g);
		}

	}
	
	public void loop(Evo game,  boolean up, boolean down, boolean left, boolean right, boolean select)
	{
		if (up || down) 
			Sound.moveSelection.play();
		if (up) 
			choice--;
		if (down)
			choice++;
		if (choice < 0) 
			choice  = 0;
		if (choice >= choices.length)
			choice = choices.length - 1;
		if (select) {
			Sound.select.play();
			if (choice == 0) {
				game.setMenuTo(new topMenu());
			}
			if (choice == 1) {
				System.exit(0);
			}
		}
	}
	
}
