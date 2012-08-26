package com.gabeochoa.entities;

import java.awt.Color;

public class Coin extends Entity{

	public int worth;
	
	public Coin(int X, int Y, int R, int w) {
		super(X, Y, R);
		worth = w;
		col = new Color(255,255,0,255);
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	
}
