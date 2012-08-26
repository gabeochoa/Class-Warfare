package com.gabeochoa.Level;

public class Bounds {

	int leftBounds, rightBounds, topBounds, bottomBounds;
	
	
	public int getLeftBounds() {
		return leftBounds;
	}
	public int getRightBounds() {
		return rightBounds;
	}
	public int getTopBounds() {
		return topBounds;
	}
	public int getBottomBounds() {
		return bottomBounds;
	}

	
	public Bounds(int leftBounds,int rightBounds,int topBounds,int bottomBounds)
	{
		this.leftBounds = leftBounds;
		this.rightBounds = rightBounds;
		this.topBounds = topBounds;
		this.bottomBounds = bottomBounds;
	}
}
