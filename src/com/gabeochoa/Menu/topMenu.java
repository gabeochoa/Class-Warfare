package com.gabeochoa.Menu;

import java.awt.Graphics;

import com.gabeochoa.Evo;
import com.gabeochoa.Font;
import com.gabeochoa.Sound;
import com.gabeochoa.SoundP;
import com.gabeochoa.engine.Art;
import com.gabeochoa.engine.Game;

public class topMenu extends Menu {

	public boolean firstTime = true;
	
	public String[] choices = {"Start","About","Instructions", "Quit"};
	public String words;
	
	public int choice = 0;
	public Graphics g;
	
	public Font winsFont;
	
	public topMenu()
	{
		super();
		winsFont = new Font();
	}
	
	public topMenu(boolean s)
	{
		super();
		firstTime = s;
		winsFont = new Font();
	}
	
	public void paint(Graphics gr)
	{
		g = gr;
		drawMenu();
	}
	
	public void drawMenu()
	{
		g.drawImage(Art.getImage("./res/menu/black.png"), 0,0, Evo.WIDTH, Evo.HEIGHT,null);
		
		//for gif, have to use imageIcon
		
		winsFont.printLine(""+Evo.title, 40, 40, 20, g);
		
		for (int i=0; i<choices.length; i++) {
			words = choices[i];
			if(choice == i)
				winsFont.printLine("-", 70, ( Evo.WIDTH/4 - 90 ) , 85 + i*50,g);
			winsFont.printLine(words, 40, (Evo.WIDTH/4-25), 100 + i*50, g);
		}

	}
	
	public void loop(Evo game,  boolean up, boolean down, boolean left, boolean right, boolean select)
	{
		if(firstTime)
		{
			firstTime = false;
		//	Sound.mainMenu.play();
		}
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
				game.setMenuTo(null);
				game.startGame();
			}
			if (choice == 1) {
				game.setMenuTo(new About());
			}
			if (choice == 2) {
				game.setMenuTo(new Instructions());
			}
			if (choice == 3) {
				System.exit(0);
			}
		}
		
	}
}
