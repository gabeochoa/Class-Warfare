package com.gabeochoa.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

public abstract class Entity {

	public int x,y;
	public int size;
	//testing
	public int radius;
	public int wealth;
	
	public Color col = Color.black;
	private Graphics g;
	
	public int xspeed;
	public int yspeed;
	private Random random;
	
	public Entity(int X, int Y, int R)
	{
		x = X;
		y = Y;
		
		radius = R;
		size = radius * 2;
		random = new Random();
		wealth = 0;
	}
	
	public Entity(int X, int Y, int R, String name)
	{
		x = X;
		y = Y;
		
		radius = R;
		size = radius * 2;
		random = new Random();
		wealth = 0;
		
		if(name.equals("first"))
			col = new Color(wealth/2,0,0,255);
		if(name.equals("second"))
			col = new Color(255,0,wealth/2,255);
		if(name.equals("third"))
			col = new Color(255,wealth/2,255,255);
		
	}
	
	public void addRed(int wealth)
	{
		if(wealth<510 && wealth > 0)
			col = new Color(wealth/2,0,0,255);
	}
	public void addBlue(int wealth)
	{
		if(wealth<510 && wealth > 0)
			col = new Color(255,0,wealth/2,255);
	}
	public void addGreen(int wealth)
	{
		if(wealth<510 && wealth > 0)
			col = new Color(255,wealth/2,255,255);
	}

	
	public abstract void move();

	public void paint(Graphics gr) {
		
		g = gr;
		drawEntity();
	}
	public void drawEntity(){
		
		g.setColor(col);
		g.fillOval(x, y,radius, radius);
	}

	public void addWorth(int worth, String name) {
		wealth += worth;
		
		if(name.equals("first"))
			addRed(wealth);
		if(name.equals("second"))
			addBlue(wealth);
		if(name.equals("third"))
			addGreen(wealth);
		
	}
	
}


