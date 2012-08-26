package com.gabeochoa.engine;

import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class Game implements KeyListener, MouseListener,MouseMotionListener {

	public static final int HEIGHT = 360;
	public static final int WIDTH = HEIGHT * 16/9;
	public static String title;
	
	public boolean over;
	public int delay = 20;
	private Graphics g;

	public abstract void init();
	public abstract void update();
	public void draw(Graphics gr)
	{
		g = gr;
	}
	
	public boolean isOver() {
		return over;
	}
	public long getDelay() {
		return delay;
	}
	public int getHeight() {
		return HEIGHT;
		}
	public int getWidth() {
		return WIDTH;
		}
	public String getTitle() {
		return title;
	}
	public void send(Graphics gr) {
		g = gr;
	}
}
