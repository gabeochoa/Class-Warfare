package com.gabeochoa.engine;

public class Loop extends Thread{

	private Game game;
	private Canvas canvas;

	public Long sTime, eTime;
	public int fps, updates;
	private boolean running;
	private Thread thread;
	
	public Loop(Game game, Canvas canvas)
	{
		this.game = game;
		this.canvas = canvas;	
	}
	
	public void run()
	{
		while(running)
		{
		game.init();
		sTime = System.currentTimeMillis();
		
		while(!game.isOver())
		{

			game.update();
			canvas.repaint();
			
			try {
				Thread.sleep(game.getDelay());
			} catch (InterruptedException e) {
					e.printStackTrace();
			}
			
			eTime = System.currentTimeMillis() - sTime;
			
			//limit fps V
			if(updates > 4000)
			{
				eTime = (long) 2000;
			}
			
			if( (eTime/1000) >= 1 )
			{

				updates /= (eTime/1000);
				
				sTime = System.currentTimeMillis();
				System.out.println(""+updates+" fps");
			}else{
					updates++;
				}
			
		}
		}
		
	}
	
	public synchronized void start() {
		if (running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stopMe() {
		if (!running) return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
}
