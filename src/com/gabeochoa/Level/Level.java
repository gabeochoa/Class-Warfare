package com.gabeochoa.Level;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import com.gabeochoa.Evo;
import com.gabeochoa.Font;
import com.gabeochoa.Menu.LossMenu;
import com.gabeochoa.Menu.errorMenu;
import com.gabeochoa.engine.*;
import com.gabeochoa.entities.*;

public abstract class Level {

	Image background;
	public ArrayList <Entity> entities = new ArrayList<Entity>();
	Graphics g;
	Entity hold;
	
	public Player player;
	public int xSpawn,ySpawn;
	
	public String lvlName = "";
	
	public Bounds bounds;
	protected Evo game;
	private Person p;
	private Random random;
	private int coins;
	public int coinWorth;
	public Font winsFont;
	public int levelLimit;
	public int timeLimit;
	 
	public Level()
	{
		bounds = new Bounds(0,Evo.WIDTH, 0, Evo.HEIGHT);
		random = new Random();
		coinWorth = 20;
		winsFont = new Font();
		levelLimit = 500;
		timeLimit = 999;
	}
	
	public void loop(Player p, Graphics gr)
	{
		player = p;
		g = gr;
		spawnEntities();
		collision();
		
		updateMovement();
		
		if(game.elapsedSeconds > timeLimit)
			game.setMenuTo(new LossMenu(lvlName));
	}

	private void drawBackground() {
		g.drawImage(Art.getImage("/level/"+lvlName+".png"),0, 0, Evo.WIDTH, Evo.HEIGHT,null);
	}

	private void drawHUD() {
		winsFont.printLine("Wealth", 11, 5, 11, g);
		winsFont.printLine(""+player.wealth, 20, 75, 5, g);
		
		winsFont.printLine("Time======Left", 11, 210, 11, g);
		int timeLeft = (int) (timeLimit - game.getTimePassed());
		if(timeLeft < 10)
		winsFont.printLine("0"+timeLeft, 25, 260, 5, g);
		else
		winsFont.printLine(""+timeLeft, 25, 260, 5, g);
		
		winsFont.printLine("Needed to Finish", 11, 440, 4, g);
		winsFont.printLine(""+(levelLimit), 15, 510, 15, g);
		
	}

	private void drawEntities() {
		for(Entity e: entities)
			e.paint(g);
	}

	public void spawnEntities()
	{
		if(entities.size() + coins < 200)
		{
			hold = new Person(random.nextInt(500), 10, 20, lvlName);
			addEntity(hold);
		}
		
		if(coins < 25)
		{
			hold = new Coin(random.nextInt(480), random.nextInt(340), 20, coinWorth);
			addEntity(hold);
			coins++;

			if(entities.size() < 150)
			{
			hold = new Person(random.nextInt(500), 10, 20, lvlName);
			addEntity(hold);
			}
		}
	}
	public void addEntity(Entity p)
	{
		entities.add(p);
	}
	public void removeEntity(Entity p)
	{
		entities.remove(p);
	}
	
	private void updateMovement() {
		for(Entity e: entities)
			e.move();
	}

	private void collision() {

		coinPickUP();
		ballballCollision();
		//gravitate();
		gravitateTowardsMoney();
		wallCollision();
		
		if(player.action)
		{
			//giveMoney();
		}
		
		checkWin();
	}
	
	public abstract void checkWin();
	
	public void giveMoney()
	{
		double lowest = 2000;
		int index = 0;
		
		for(int i=0; i<entities.size(); i++)
		{
			Entity A = entities.get(i);	
			if(A instanceof Coin || A instanceof Person)
				continue;
			for(int j=0; j<entities.size(); j++)
			{
				Entity B = entities.get(j);
				if(B instanceof Coin || B instanceof Player)
					continue;
				
				Point a = new Point(A.x + A.radius, A.y + A.radius);
				Point b = new Point(B.x + B.radius, B.y + B.radius);
				if( Calc.dist(a, b) < lowest )
				{
					index = j;
					lowest = Calc.dist(a, b);
				}
			}
		}
		
		entities.get(index).wealth += 10;
		player.wealth -= 10;
				
	}
	public void gravitate()
	{
		for(Entity e: entities)
			if((!(e instanceof Player) ) && (e instanceof Person) )
				((Person) e).moveTowards(player);
	}
	
	public void gravitateTowardsMoney()
	{
		for(int i=0; i<entities.size(); i++)
		{
			Entity A = entities.get(i);	
			if(A instanceof Coin)
				continue;
			for(int j=0; j<entities.size(); j++)
			{
				Entity B = entities.get(j);
				if(B instanceof Coin)
					continue;
				
				Point a = new Point(A.x + A.radius, A.y + A.radius);
				Point b = new Point(B.x + B.radius, B.y + B.radius);
				if( Calc.dist(a, b) < A.radius * 10)
				{
					
					if( A.wealth < B.wealth)
					{
						if(A instanceof Person)
							((Person)A).moveToward(b);
						if(B instanceof Person)
							((Person)B).moveAway(a);
						
						if(B instanceof Player)
						{
						}
					}
					
					if( B.wealth < A.wealth)
					{
						if(B instanceof Person)
							((Person)B).moveToward(a);
						if(A instanceof Person)
								((Person)A).moveAway(b);
					}
					
				}
				
			}
				
		}
	}
	

	public void ballballCollision()
	{
		for(int i=0; i<entities.size(); i++)
		{
			Entity A = entities.get(i);	
			if(A instanceof Coin)
				continue;
			for(int j=0; j<entities.size(); j++)
			{
				Entity B = entities.get(j);
				if(B instanceof Coin)
					continue;
		
				Point a = new Point(A.x+A.radius, A.y+A.radius);
				Point b = new Point(B.x+B.radius, B.y+B.radius);
				
				if( Calc.dist(a, b) < A.radius)
				{
					if(A instanceof Person)
						((Person) A).bounce();
					if(B instanceof Person)
							((Person) B).bounce();
				}	
			}
		}
	}

	public void wallCollision(){
		
		for(Entity e: entities)
		{
			if(e instanceof Player || e instanceof Coin)
				continue;
			else
				p = (Person)e;

			if( (p.x < bounds.leftBounds && p.xspeed <0)|| (p.x+p.radius+p.radius > bounds.rightBounds && p.xspeed >0) )
				p.xspeed *= -1;
			
			if ((p.y < bounds.topBounds && p.yspeed <0)||(p.y+p.radius+p.radius > bounds.bottomBounds && p.yspeed >0))
					p.yspeed *= -1;
		}
	}
	
	public void wallTrigCollision()
	{
		//angle coming in same as going out
		
		//Tan B = O /A
		//B = atan(o/A
		//A = o/tanb
		//o = tanbA
		
		for(Entity e: entities)
		{
			if(e instanceof Player || e instanceof Coin)
				continue;
			else
				p = (Person)e;
			
			double angleb = Math.atan(e.xspeed / e.yspeed);
			
			if(
				(p.x < bounds.leftBounds && p.xspeed <0)
							||
				(p.x+p.radius+p.radius > bounds.rightBounds && p.xspeed >0)
							||			
				(p.y < bounds.topBounds && p.yspeed <0)
							||
				(p.y+p.radius+p.radius > bounds.bottomBounds && p.yspeed >0)
			 )
			{
				p.bounce(p, angleb);
			}
			
			
			
		}
	}
	
public void coinPickUP()
{
		for(int i=0; i<entities.size(); i++)
		{
			Entity A = entities.get(i);	
		
			for(int j=0; j<entities.size(); j++)
			{
				Entity B = entities.get(j);
				if(B instanceof Person)
					continue;
				
				Point a = new Point(A.x, A.y);
				Point b = new Point(B.x, B.y);
				if( Calc.dist(a, b) < A.radius)
				{
					if( !(A instanceof Coin) && (B instanceof Coin))
					{
						(A).addWorth(((Coin) B).worth, lvlName);
						removeEntity(B);
						j--;
						coins--;
					}
					if( !(B instanceof Coin) && (A instanceof Coin))
					{
						(B).addWorth(((Coin) A).worth, lvlName);
						removeEntity(A);
						i--;
						coins--;
					}
				}
				
				
			}
		}
	}
	public static Level loadLevel(Evo game, String name)
	{
		Level requested = getLevelByName(name);
		if(requested != null)
		{
			requested.init(game, name, Art.getImage("/level/"+name+".png"));
			return requested;
		}
		else
			game.setMenuTo(new errorMenu("Failed to Load Level"));
			
		return null;
	}

	private void init(Evo game, String name, Image imageFromFile) {
		this.game = game;
		player = game.player;
		background = imageFromFile;
	}
	
	private static Level getLevelByName(String name)
	{
	 try{
		name = name.substring(0, 1).toUpperCase() + name.substring(1);
				return (Level) Class.forName("com.gabeochoa.Level." + name + "Level").newInstance();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
	}
	public void switchLevel(int id)
	{
		
	}

	
	public void paint(Graphics gr) {
		g = gr;
		drawBackground();
		drawEntities();
		drawHUD();
	}
}
