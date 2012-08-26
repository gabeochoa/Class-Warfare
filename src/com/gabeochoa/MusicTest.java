package com.gabeochoa;

import java.awt.Dimension;

import javax.swing.JFrame;

import com.gabeochoa.SoundP.Volume;

public class MusicTest extends JFrame implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int HEIGHT = 76;
	public static final int WIDTH = HEIGHT * 16/9;
	public Dimension size;

	private Thread thread;

	private boolean running;

	private int count;
	
	public MusicTest()
	{
		size = new Dimension(WIDTH,HEIGHT);
		setSize(size);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Music Test");
		
	}
	
	
	
	
	public void loop()
	{
		count++;
		
		if(count > 10)
		{
			Sound.mainMenu.play();
			count = 0;
		}
		
		
	}
	
	
	
	
	
	public void paint(){}
	
	
	public static void main(String args[])
	{
		MusicTest mt = new MusicTest();
		while(true)
			mt.run();
	}
	
	public synchronized void start()
	{
		if(running)return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop()
	{
		if(!running)return;
		running = false;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void run() {
		
		try {
			thread.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		loop();
		
		repaint();
	}
	
}
