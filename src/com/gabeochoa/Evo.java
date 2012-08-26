package com.gabeochoa;

import java.awt.Graphics;
import java.awt.event.*;
import java.util.ArrayList;

import com.gabeochoa.Level.Level;
import com.gabeochoa.Menu.*;
import com.gabeochoa.engine.*;
import com.gabeochoa.entities.Entity;
import com.gabeochoa.entities.Person;
import com.gabeochoa.entities.Player;



public class Evo extends Game {

	private int x,y, mx, my;
	
	public int[] mouse = new int[5];
	public boolean[] keys = new boolean[424242];
	
	public Menu menu;
	private boolean use,right,up, down,left;
	private Graphics g;
	public Player player;
	private Level level;

	private long startTime;
	public long estimatedNano;
	public double elapsedSeconds;
	
	public static final int HEIGHT = 360;
	public static final int WIDTH = HEIGHT * 16/9;
		
	public static void main(String args[])
	{
		Application.start(new Evo());
	}
	
	public Evo()
	{
		super();
		title = "Class Warfare";
		setMenuTo(new topMenu());
	}
	
	public void startGame() {
		init();
	}
	
	@Override
	public void init() {
		
		level = Level.loadLevel(this, "first");
		player = new Player();
		player.level = level;
		
		level.addEntity(player);
		
		startTime = System.nanoTime();
	}

	@Override
	public void update() {
		handleInput();
		
		estimatedNano = System.nanoTime() - startTime;
    }
	
	public double getTimePassed()
	{
		elapsedSeconds = (double)estimatedNano / 1000000000.0;
		
		return elapsedSeconds;
	}

	public void resetTimeStart()
	{
		startTime = System.nanoTime();
	}
	
	private void handleInput() {
		mx = mouse[3];
		my = mouse[4];
		
		up = keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_NUMPAD8];
		down = keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_NUMPAD2];
		left = keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT] ;
		right = keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT];
		use = keys[KeyEvent.VK_SPACE] || keys[KeyEvent.VK_ENTER];
	}

	@Override
	public void draw(Graphics gr) {
		
		super.draw(gr);
		g = gr;
		
		handleInput();
		
		if(keys[KeyEvent.VK_ESCAPE])
		{
			keys[KeyEvent.VK_ESCAPE] = false;
			if(menu == null)
			{
				setMenuTo(new pauseMenu());
			}		
		}

		if (use) {
			keys[KeyEvent.VK_SPACE] = false;
		}


		if (menu != null) {
			keys[KeyEvent.VK_W] = keys[KeyEvent.VK_UP] = keys[KeyEvent.VK_NUMPAD8] = false;
			keys[KeyEvent.VK_S] = keys[KeyEvent.VK_DOWN] = keys[KeyEvent.VK_NUMPAD2] = false;
			keys[KeyEvent.VK_A] = false;
			keys[KeyEvent.VK_D] = false;
			
			menu.loop(this, up, down, left, right, use);
		}
		else {
			player.loop(up, down, left, right);
			if(use)
				player.action();
			
			level.loop(player,g);
			level.paint(g);
		}
		
		if(menu != null)
			menu.paint(g);
	}
	

	public void setMenuTo(Menu menu) {
		this.menu = menu;
	}

	
	public void mouseMoved(MouseEvent m) {
		 mouse[3] = m.getX();
	     mouse[4] = m.getY();
	    // System.out.println(""+mouse[3]+" , "+mouse[4]);
	}
	
	public void mousePressed(MouseEvent m) {
		mouse[0] = m.getButton();
        mouse[1] = m.getX();
        mouse[2] = m.getY();
	}

	public void mouseReleased(MouseEvent m) {
		mouse[0] = 0;//arg0.getButton();
        mouse[1] = 0;//arg0.getX();
        mouse[2] = 0;//arg0.getY();
	}

	public void keyPressed(KeyEvent k) {
		int kc = k.getKeyCode();
		if( kc > 0 && kc < keys.length)
			keys[kc] = true;
	}

	public void keyReleased(KeyEvent k) {
		int kc = k.getKeyCode();
		if( kc > 0 && kc < keys.length)
			keys[kc] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		level.addEntity(new Person(e.getX(), e.getY(), 20));
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void switchLevel(String string, int i) {
		level = Level.loadLevel(this, string);
		level.entities = new ArrayList<Entity>();
		level.player = player;
		player.wealth = 0;
		startTime = 0;
		
	}

	public void restartLevel(String string)
	{
		level = Level.loadLevel(this, string);
		player = new Player();
		player.level = level;
		
		level.addEntity(player);
		
		startTime = System.nanoTime();
	}
	

}
