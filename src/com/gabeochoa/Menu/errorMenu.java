package com.gabeochoa.Menu;

import java.awt.Graphics;

import com.gabeochoa.Evo;
import com.gabeochoa.Font;
import com.gabeochoa.Sound;
import com.gabeochoa.engine.Art;
import com.gabeochoa.engine.Game;

public class errorMenu extends Menu {
	
	public String[] choices = {"Continue", "Quit"};
	public String finish;
	
	public int choice = 0;
	public Graphics g;
	
	public Font winsFont;
	private String words;
	
	public errorMenu()
	{
		super();
		winsFont = new Font();
	}
	
	public errorMenu(String error)
	{
		super();
		finish = error;
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
		
		winsFont.printLine("Error: "+finish, 60, 40, 20, g);
		
		for (int i=0; i<choices.length; i++) {
			words = choices[i];
			if(choice == i)
				winsFont.printLine("-", ( Evo.WIDTH/4 - 100 ), 100 , 90 + i*50,g);
			winsFont.printLine(words, 40, (Evo.WIDTH/4), 100 + i*50, g);
		}

	}
	
	public void loop(Evo game,  boolean up, boolean down, boolean left, boolean right, boolean select)
	{
		if (up || down) 
			//Sound.moveSelection.play();
		if (up) 
			choice--;
		if (down)
			choice++;
		if (choice < 0) 
			choice  = 0;
		if (choice >= choices.length)
			choice = choices.length - 1;
		if (select) {
			//Sound.select.play();
			if (choice == 0) {
				//Sound.mainMenu.stop();
				game.setMenuTo(new topMenu());
			}
			if (choice == 1) {
				System.exit(0);
			}
	}
	}
}
