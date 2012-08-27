package com.gabeochoa.entities;

import java.awt.Color;
import java.awt.Graphics;

import com.gabeochoa.Sound;
import com.gabeochoa.Level.Level;

public class Player extends Entity {

	private int walkSoundCount;
	private int count;
	public Level level;
	public double speed;
	public boolean action;
	private Graphics g;

	public Player(int X, int Y, int R) {
		super(X, Y, R);
		wealth = 0;
		speed = 3;
	}

	public Player() {
		super(0,0,20);
		wealth = 0;
		speed = 3;
		
	}
	

	public void loop(boolean up,boolean down,boolean left,boolean right)
	{
		 walkSoundCount = 6;
		
		
		if(up && y >0 )
		{
			walkSound();
			y-=speed;
			
		}
		if(down && y< 300)
		{
			
			walkSound();
			y+=speed;
			
		}
		if(left && x>0)
		{
			walkSound();
			x-=speed;
		}
		if(right && x<(360 *16/9 )- 35)
		{
			walkSound();
			x+=speed;
		}
		action = false;
		//System.out.println(""+x+" , "+y+" :"+wealth);
	}

	private void walkSound() {
		if(count >= walkSoundCount)
		{
			count = 0;
			Sound.walk.play();
		}
		else
		{
			count++;
		}
	}

	public void paint(Graphics gr)
	{
		g = gr;
		drawEntity();
	}
	public void drawEntity(){
		
		g.setColor(col);
		g.fillOval(x, y,radius, radius);
	}
	@Override
	public void move() {
		
	}

	public void action() {
		action = true;
	}

	
}
