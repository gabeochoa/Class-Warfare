package com.gabeochoa.entities;

import java.awt.Point;
import java.util.Random;

import com.gabeochoa.Evo;

public class Person extends Entity{

	public double xspeed, yspeed;
	private double dt;
	private Random random;

	public Person(int X, int Y, int R) {
		super(X, Y, R);
		 xspeed = 2;
		 yspeed = 2;//(Math.random()*10) > 5? 2:3;
		 random = new Random();
	}
	public Person(int X, int Y, int R, String lvlname) {
		super(X, Y, R, lvlname);
		 xspeed = 2;
		 yspeed = 2;//(Math.random()*10) > 5? 2:3;
		 random = new Random();
	}


	@Override
	public void move() {
		x += xspeed;
		y += yspeed;

		
		if(x > Evo.WIDTH + 20)
			x = 0;
		if(y > 400)
			y = 0;
		
		
	}
	public void bounce()
	{
		bounceX();
		bounceY();
	}
	public void bounceX() {
		
		xspeed *= -1;
		
		if(xspeed >0)
		x-=5;
		else
		x+=5;
		
	}
	public void bounceY() {
		yspeed *= -1;
		if(yspeed >0)
		y-=5;
		else
		y+=5;
	}
	
	public void moveTowards(Player pl)
	{
		if(x < pl.x)
			xspeed = 1;
		if(y < pl.y)
			yspeed = 1;
		
		if(x > pl.x)
			xspeed = -1;
		if(y > pl.y)
			yspeed = -1;
		
	}

	public void moveToward(Point a) {

		if(x + random.nextInt(50) < a.x)
			xspeed = 1;
		if(y + random.nextInt(50) < a.y)
			yspeed = 1;
		
		if(x + radius - random.nextInt(0100) > a.x)
			xspeed = -1;
		if(y + radius - random.nextInt(10) > a.y)
			yspeed = -1;
	}

	public void moveAway(Point b) {
		if(x + random.nextInt(10) < b.x)
			xspeed = -1;
		if(y + random.nextInt(10) < b.y)
			yspeed = -1;
		
		if(x + radius - random.nextInt(10) > b.x)
			xspeed = 1;
		if(y + radius - random.nextInt(10) > b.y)
			yspeed = 1;
	}

	public void bounce(Person p, double angleb) {

		p.xspeed = Math.tan(angleb) * p.yspeed;
		
	}

}
