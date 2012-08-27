package com.gabeochoa.Menu;

import java.awt.Graphics;
import java.util.Random;

import com.gabeochoa.Evo;
import com.gabeochoa.Font;
import com.gabeochoa.Sound;
import com.gabeochoa.engine.Art;
import com.gabeochoa.engine.Game;

public class WinMenu extends Menu {
	
	public String[] choices = {"Main Menu", "Quit"};
	public String finish;
	
	public int choice = 0;
	public Graphics g;
	
	public Font winsFont;
	private String words;
	private String fjob;
	private String mjob;
	
	public WinMenu()
	{
		super();
		winsFont = new Font();
		fjob = getRandomJob();
		mjob = getRandomJob();
		Sound.third.stop();
		Sound.endGame.play();
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
		
		winsFont.printLine("YOU WON!!", 60, 40, 20, g);
		
		String[] lines = 
			{
				"You collected enough money,",
				"to find your parents.",
				"",
				"Your father is a<n>",
				""+fjob+"",
				"",
				"And your mother is a<n>",
				""+mjob+""
			};
		
		for (int i=0; i<lines.length; i++) {
			winsFont.printLine(lines[i], 15, 120, 90+i*15, g);
		}
		
		for (int i=0; i<choices.length; i++) {
			words = choices[i];
			if(choice == i)
				winsFont.printLine("-", ( Evo.WIDTH/4 - 100 ), 100 , 210 + i*50,g);
			winsFont.printLine(words, 40, (Evo.WIDTH/4), 220 + i*50, g);
		}

	}
	
	String[] jobs = 
	{
		"Accountant",
		"Actor",
		"Actuary",
		"Agricultural and food scientist",
		"Anthropologist",
		"Architect",
		"Artist",
		"Automotive mechanic",
		"Bookkeeping clerk",
		"Budget analyst",
		"Carpenter",
		"Chemist",
		"Childcare worker",
		"Civil engineer",
		"Coach",
		"Computer hardware engineer",
		"Computer support specialist",
		"Cost estimator",
		"Court reporter",
		"Dancer",
		"Database administrator",
		"Designer",
		"Desktop publisher",
		"Drafter",
		"Economist",
		"Editor",
		"Educator",
		"Electrical engineer",
		"Electrician",
		"Environmental scientist",
		"Farmer",
		"Financial analyst",
		"Firefighter",
		"Fitness trainer",
		"Historian",
		"Human resources assistant",
		"Judge",
		"Landscape architect",
		"Lawyer",
		"Librarian",
		"Loan officer",
		"Mathematician",
		"Microbiologist",
		"Musician",
		"Paralegal",
		"Pharmacist",
		"Photographer",
		"Physician",
		"Physicist",
		"Police officer",
		"Professional athlete",
		"Psychologist",
		"Real estate agent",
		"Recreational therapist",
		"Referee",
		"Registered nurse",
		"Reporter",
		"Secretary",
		"Social worker",
		"Software developer",
		"Statistician",
		"Surveyor",
		"Systems analyst",
		"Urban planner",
		"Veterinarian",
		"Web developer",
		"Writer",
		"Zoologist"
	};
	private Random random;
	
	private String getRandomJob() {
		random = new Random();
		int number = random.nextInt(jobs.length);
		return jobs[number];
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
			Sound.endGame.stop();
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
