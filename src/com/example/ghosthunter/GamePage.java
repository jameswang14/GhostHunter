package com.example.ghosthunter;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import com.example.ghosthunter.Character.Bullet;
import com.example.ghosthunter.Character.Player;
import com.example.ghosthunter.Ghost.BasicGhost;
import com.example.ghosthunter.Ghost.Ghost;
import com.example.ghosthunter.GridMap.GridMap;
import com.example.ghosthunter.Environment.Wall;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GamePage extends Activity {
	
	Canvas c = new Canvas();
	DrawingPanel d;
	int xcor = 0;
	int ycor = 0;
	int playerX = 250;
	int playerY = 250;
	static int scoreCounter = 0;
	int mvspeed = 75; //decrease value to increase speed of player
	String name;
	
	
	Bitmap b;
	Bitmap b2;
	BasicGhost ghost;
	BasicGhost ghost1;
	Player p;
	Bullet bullet;
	int[] a;
	int[] a2;

	static TextView score;
	static TextView hp;
	GridMap grid;

	Runnable moveLeft;
	Runnable moveUp;
	Runnable moveRight;
	Runnable moveDown;

	Runnable fire; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		final Handler mHandler = new Handler(); 
		
		final MediaPlayer pausesound = MediaPlayer.create(GamePage.this, R.raw.pausesound);
		final MediaPlayer newgunsound = MediaPlayer.create(GamePage.this, R.raw.gun1);
		  
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    name = extras.getString("Name");
		    if(name != null)
		    Log.v("Test", name);
		    else
		    	Log.e("Test", "f");
		}
			d = new DrawingPanel(this);
		    FrameLayout game = new FrameLayout(this);
	        RelativeLayout gameWidgets = new RelativeLayout (this);

			Wall.processImages(this); //process the wall images
			grid = new GridMap(10,10,5,10, this);
	        
			a2 = new int[2];
			a2[0] = 100;
			a2[1] = 200;
			p = new Player(50, 10, grid, this);
			p.setPos(a2);
	        
	        
	        Button leftButton = new Button(this);
			Button upButton = new Button(this);
			Button rightButton = new Button(this);
			Button downButton = new Button(this);
			Button fireButton = new Button(this);

			Button pauseButton = new Button(this);
			Button test = new Button(this);

			

			score = new TextView(d.getContext());
			hp = new TextView(d.getContext());

			//score = (TextView) findViewById(R.id.ScoreView);
			hp.setTextSize(30);
			hp.setTextColor(Color.parseColor("#336600"));
			hp.setText("Health: " + Integer.toString(p.getHp()));
			hp.setX(600);
			score.setTextSize(30);
			score.setTextColor(Color.parseColor("#FF0000"));
			score.setText("Score: " + Integer.toString(scoreCounter));
			
			

			test.setMinimumHeight(0);
			test.setMinimumWidth(0);
			test.setX(200);
			test.setY(300);
			test.setHeight(70);
			test.setWidth(45);
			test.setBackgroundColor(Color.WHITE);
			
	        leftButton.setWidth(100);
	        leftButton.setMinimumHeight(0);
	        leftButton.setHeight(75);
	        leftButton.setY(835);
	        leftButton.setX(60);
	        
	        upButton.setMinimumWidth(0);
	        upButton.setWidth(75);
	        upButton.setHeight(100);
	        upButton.setY(735);
	        upButton.setX(160);
	      
	        rightButton.setWidth(100);
	        rightButton.setMinimumHeight(0);
	        rightButton.setHeight(75);
	        rightButton.setY(835);
	        rightButton.setX(235);
	        
	        downButton.setMinimumWidth(0);
	        downButton.setWidth(75);
	        downButton.setHeight(100);
	        downButton.setY(910);
	        downButton.setX(160);
	        
	        fireButton.setMinimumHeight(0);
	        fireButton.setMinimumWidth(0);
	        fireButton.setWidth(100);
	        fireButton.setHeight(100);
	        fireButton.setY(850);
	        fireButton.setX(600);
	        fireButton.setText("Fire");
	        
	        pauseButton.setMinimumHeight(0);
	        pauseButton.setMinimumWidth(0);
	        pauseButton.setWidth(100);
	        pauseButton.setHeight(100);
	        pauseButton.setY(950);
	        pauseButton.setX(345);
	        pauseButton.setText("Pause");
	        
	        gameWidgets.addView(upButton);
	        gameWidgets.addView(leftButton);   
	        gameWidgets.addView(rightButton);
	        gameWidgets.addView(downButton);
	        gameWidgets.addView(fireButton);

	        gameWidgets.addView(pauseButton);

	        
	        //gameWidgets.addView(test);

	        //---------------------------------------------------]
	        
	        game.addView(d);
	        gameWidgets.addView(score);
	        gameWidgets.addView(hp);
	        game.addView(gameWidgets);

	        setContentView(game);
	        
	        moveLeft = new Runnable() {
	            @Override public void run(){
	                mHandler.postDelayed(this, mvspeed);
	                p.move(-p.getSpeed(), 0);
	            }
	        };
	        
	        
	        leftButton.setOnTouchListener(new OnTouchListener() {
	            public boolean onTouch(View v, MotionEvent event) {
	                switch(event.getAction()) {
	                case MotionEvent.ACTION_DOWN:
	                	p.setUp(false);
	                	p.setDown(false);
	                	
	                	p.setLeft(true);
	                	p.setRight(false);
//	                	bullet.setX(p.getPos()[0]);
//	                	bullet.setY(p.getPos()[1]);
//	                	
//	                	bullet.move(-1, 0);
	                	mHandler.postDelayed(moveLeft, 0);
	                	//p.move(-5, 0);
	                    return true; 
	                case MotionEvent.ACTION_UP:
	                    // No longer down
	                	mHandler.removeCallbacks(moveLeft);
	                    return true;
	                }
	                return false;
	            }
	        });
	        
	        moveUp = new Runnable() {
	            @Override public void run() {
	               
	                mHandler.postDelayed(this, mvspeed);
	                p.move(0, -p.getSpeed());
	            }
	        };
	        
	        
	        upButton.setOnTouchListener(new OnTouchListener() {
	            public boolean onTouch(View v, MotionEvent event) {
	                switch(event.getAction()) {
	                case MotionEvent.ACTION_DOWN:
	                	
	                	p.setUp(true);
	                	p.setDown(false);
	                	
	                	p.setLeft(false);
	                	p.setRight(false);
//	                	bullet.setX(p.getPos()[0]);
//	                	bullet.setY(p.getPos()[1]);
//	                	
//	                	bullet.move(0, 1);
	                	//p.move(0, -5);
	                	mHandler.postDelayed(moveUp, 0);
	                    return true;
	                case MotionEvent.ACTION_UP:
	                    // No longer down
	                	mHandler.removeCallbacks(moveUp);
	                    return true;
	                }
	                return false;
	            }
	        });
				
	        moveRight = new Runnable() {
	            @Override public void run() {
	               
	                mHandler.postDelayed(this, mvspeed);
	                p.move(p.getSpeed(), 0);
	            }
	        };
	        rightButton.setOnTouchListener(new OnTouchListener() {
	            public boolean onTouch(View v, MotionEvent event) {
	                switch(event.getAction()) {
	                case MotionEvent.ACTION_DOWN:
	                	p.setUp(false);
	                	p.setDown(false);
	                	
	                	p.setLeft(false);
	                	p.setRight(true);
//	                	bullet.setX(p.getPos()[0]);
//	                	bullet.setY(p.getPos()[1]);
//	                	
//	                	bullet.move(1,0);
	                	mHandler.postDelayed(moveRight, 0);
	                	//p.move(5, 0);
	                    return true;
	                case MotionEvent.ACTION_UP:
	                    // No longer down
	                	mHandler.removeCallbacks(moveRight);
	                    return true;
	                }
	                return false;
	            }
	        });

	        moveDown = new Runnable() {
	            @Override public void run() {
	               
	                mHandler.postDelayed(this, mvspeed);
	                p.move(0, p.getSpeed());
	            }
	        };
	        downButton.setOnTouchListener(new OnTouchListener() {
	            public boolean onTouch(View v, MotionEvent event) {
	                switch(event.getAction()) {
	                case MotionEvent.ACTION_DOWN:
	                	p.setUp(false);
	                	p.setDown(true);
	                	
	                	p.setLeft(false);
	                	p.setRight(false);

	                	mHandler.postDelayed(moveDown, 0);
	                	//p.move(0,5);
	                    return true;
	                case MotionEvent.ACTION_UP:
	                    // No longer down
	                	mHandler.removeCallbacks(moveDown);
	                    return true;
	                }
	                return false;
	            }
	        });
	        
	        fire = new Runnable()
	        {
	        	public void run()
	        	{
	        		int xdir = 0;
        			int ydir= 0;
        			int xpadding = 0;
        			int ypadding = 0;
        			Bullet temp;
        			if(p.isUp())
        			{
        				xpadding=42;
        				ypadding=10;
        				ydir=1;
        			}
        			if(p.isDown())
        			{
        				ydir=-1;
        				xpadding=36;
        				ypadding=64;
        			}
        			if(p.isLeft())
        			{
        				xpadding = 10;
        				ypadding = 56;
        				xdir = -1;
        			}
        			if(p.isRight())
        			{
        				xdir =1;
        				xpadding = 72;
        				ypadding=56;
        			}
					temp = new Bullet(p.getPos()[0]+xpadding,p.getPos()[1]+ypadding,xdir,ydir, grid, GamePage.this);
	        	}
	        };
	        
	        fireButton.setOnTouchListener(new OnTouchListener(){
	        	public boolean onTouch(View v, MotionEvent event) {
	        		switch(event.getAction()) {
	        			case MotionEvent.ACTION_DOWN:
	        				newgunsound.start();
	        			mHandler.postDelayed(fire, 0);
	        			return true;
	        		
	        			case MotionEvent.ACTION_UP:
	        				mHandler.removeCallbacks(fire);
	        				//newestgunsound.start();
	        				return true;
	        	
	        		}
	        		return false;
	        	}
	        });
	        
	        pauseButton.setOnTouchListener(new OnTouchListener(){
	        	public boolean onTouch(View v, MotionEvent event) {
	        		switch(event.getAction()) {
	        			case MotionEvent.ACTION_DOWN:
	        				if(d.running){
	        					pausesound.reset();
	        					pausesound.start();
	        					d.pause();
	        					Toast.makeText(GamePage.this, "Paused", Toast.LENGTH_SHORT).show();
	        				}
	        				else
	        					d.resume();
	        				return true;
	        			case MotionEvent.ACTION_UP:
	        				return true;
	        		}
	        		return false;
	        	}
	        });
		
		a = new int[2];



		int[] location = new int[2];
		location[0] = 600;
		location[1] = 600;

		ghost = new BasicGhost(a,grid,this);
		ghost1 = new BasicGhost(location,grid,this);
		

	}

	protected void onResume(){
		super.onResume();
		d.resume();
	}
	protected void onDestroy(){
		d.pause();
	}
	
	
	public class DrawingPanel extends SurfaceView implements Runnable {
		private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		Thread t = null;
		private SurfaceHolder sh;
		long start;
		long end;
		Handler handler; 
		boolean isHit = false;
		Canvas canvas;
		boolean running = false;
		Runnable r = new Runnable()
		{
			public void run()
			{
				isHit = grid.detectGhostHit(p);

			}
		 };

		public DrawingPanel(Context context) {
			super(context);
			sh = getHolder();
			paint.setColor(Color.BLUE);
			paint.setStyle(Style.FILL);
			handler = new Handler();
			t = new Thread(this);
			t.start();
		}
		
		
		@Override
		public void run() {
			while(running)
			{
				
				if(!sh.getSurface().isValid())
					continue;
				canvas = sh.lockCanvas();
				if(p.getHp() <1)
				{
					running = false;
					runOnUiThread(new Thread(new Runnable()
					{
						public void run() {
	
						Intent gameIntent = new Intent(GamePage.this, DeathScreen.class);
						gameIntent.putExtra("Name", name);
						gameIntent.putExtra("Score", scoreCounter);
						getContext().startActivity(gameIntent);


							
						}
					}));

				}
				 runOnUiThread(new Thread(new Runnable()
				{
					public void run()
					{
						GamePage.score.setText("Score: " + scoreCounter);
					}
				}));
				 end = System.currentTimeMillis();
				 if(isHit && end - start > 2000)
				 {

					 isHit = false;
				 }
				 else if (!isHit)
				 {
					 handler.post(r);
					 isHit=true;
					 start = System.currentTimeMillis();
				 }
				 
				 runOnUiThread(new Thread(new Runnable()
				 {
					 public void run()
					 {
						 
						 GamePage.hp.setText("Health: "+p.getHp());
						 
 					 }
				 }));
				onDraw(canvas);
				sh.unlockCanvasAndPost(canvas);

			}
			
			
		}
		public void onDraw(Canvas canvas) {

			Paint paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
			paint2.setColor(Color.GREEN);

			scoreCounter+=grid.detectBulletHit();
			
			canvas.drawARGB(255, 0, 0, 0);

			ArrayList<com.example.ghosthunter.Character.Character> toDraw = grid.getCharList(0, 0, canvas.getWidth(), canvas.getHeight());
			for(int a = 0; a < toDraw.size(); a++)
			{
				
				com.example.ghosthunter.Character.Character temp = toDraw.get(a);
				
				if(temp instanceof Ghost)
					((Ghost)temp).move(1,1,p); //replace with actual direction later
				
			}

			CopyOnWriteArrayList<Bullet> bulletDraw = grid.getBulletList(0,0,canvas.getWidth(),canvas.getHeight());
			for(int a = 0; a < bulletDraw.size(); a++)
			{
				Bullet temp = bulletDraw.get(a);
				bulletDraw.get(a).move();
			}
			grid.update(canvas, p);

		}
		public void pause(){
			running = false;
			while(true){
				try{
					t.join();
				}catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			}
			t = null;
		}
		public void resume(){
			running = true;
			t = new Thread(this);
			t.start();
		}



		
	}


}
