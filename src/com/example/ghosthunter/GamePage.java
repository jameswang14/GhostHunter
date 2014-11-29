package com.example.ghosthunter;

import java.util.ArrayList;

import com.example.ghosthunter.Character.Bullet;
import com.example.ghosthunter.Character.Player;
import com.example.ghosthunter.Ghost.BasicGhost;
import com.example.ghosthunter.Ghost.Ghost;
import com.example.ghosthunter.GridMap.GridMap;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Bundle;
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

public class GamePage extends Activity {
	
	Canvas c = new Canvas();
	DrawingPanel d;
	int xcor = 0;
	int ycor = 0;
	int playerX = 250;
	int playerY = 250;
	int scoreCounter = 0;
	
	Bitmap b;
	Bitmap b2;
	BasicGhost ghost;
	BasicGhost ghost1;
	Player p;
	Bullet bullet;
	int[] a;
	int[] a2;
	GridMap grid = new GridMap(0,0,0);
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		

		    d = new DrawingPanel(this);
		    FrameLayout game = new FrameLayout(this);
	        RelativeLayout gameWidgets = new RelativeLayout (this);

	        Button leftButton = new Button(this);
			Button upButton = new Button(this);
			Button rightButton = new Button(this);
			Button downButton = new Button(this);
			Button leftButton2 = new Button(this);
			Button upButton2 = new Button(this);
			Button rightButton2 = new Button(this);
			Button downButton2 = new Button(this);
			scoreCounter += 1;
			TextView score = new TextView(d.getContext());
			//score = (TextView) findViewById(R.id.ScoreView);
			score.setText("Score: " + Integer.toString(scoreCounter));
			

	        leftButton.setWidth(100);
	        leftButton.setHeight(50);
	        leftButton.setY(500);
	        leftButton.setX(50);
	        
	        upButton.setMinimumWidth(0);
	        upButton.setWidth(50);
	        upButton.setHeight(100);
	        upButton.setY(450);
	        upButton.setX(150);
	      
	        rightButton.setWidth(100);
	        rightButton.setHeight(50);
	        rightButton.setY(500);
	        rightButton.setX(200);
	        
	        downButton.setMinimumWidth(0);
	        downButton.setWidth(50);
	        downButton.setHeight(100);
	        downButton.setY(550);
	        downButton.setX(150);
	        
	        gameWidgets.addView(upButton);
	        gameWidgets.addView(leftButton);   
	        gameWidgets.addView(rightButton);
	        gameWidgets.addView(downButton);
	        
	        //---------------------------------------------------]
	        
	        leftButton2.setWidth(100);
	        leftButton2.setHeight(50);
	        leftButton2.setY(500);
	        leftButton2.setX(250);
	        
	        upButton2.setMinimumWidth(0);
	        upButton2.setWidth(50);
	        upButton2.setHeight(100);
	        upButton2.setY(450);
	        upButton2.setX(350);
	      
	        rightButton2.setWidth(100);
	        rightButton2.setHeight(50);
	        rightButton2.setY(500);
	        rightButton2.setX(400);
	        
	        downButton2.setMinimumWidth(0);
	        downButton2.setWidth(50);
	        downButton2.setHeight(100);
	        downButton2.setY(550);
	        downButton2.setX(350);
	        
	        gameWidgets.addView(upButton2);
	        gameWidgets.addView(leftButton2);   
	        gameWidgets.addView(rightButton2);
	        gameWidgets.addView(downButton2);
	        game.addView(d);
	        gameWidgets.addView(score);

	        game.addView(gameWidgets);

	        setContentView(game);

	        leftButton.setOnTouchListener(new OnTouchListener() {
	            public boolean onTouch(View v, MotionEvent event) {
	                switch(event.getAction()) {
	                case MotionEvent.ACTION_DOWN:
	                
	                	p.move(-5, 0);
	                    return true;
	                case MotionEvent.ACTION_UP:
	                    // No longer down
	                    return true;
	                }
	                return false;
	            }
	        });
	        
	        upButton.setOnTouchListener(new OnTouchListener() {
	            public boolean onTouch(View v, MotionEvent event) {
	                switch(event.getAction()) {
	                case MotionEvent.ACTION_DOWN:
	                	
	                	
	                	p.move(0, -5);
	                    return true;
	                case MotionEvent.ACTION_UP:
	                    // No longer down
	                    return true;
	                }
	                return false;
	            }
	        });
				
	        
	        rightButton.setOnTouchListener(new OnTouchListener() {
	            public boolean onTouch(View v, MotionEvent event) {
	                switch(event.getAction()) {
	                case MotionEvent.ACTION_DOWN:
	                	
	                	p.move(5, 0);
	                    return true;
	                case MotionEvent.ACTION_UP:
	                    // No longer down
	                    return true;
	                }
	                return false;
	            }
	        });

	        downButton.setOnTouchListener(new OnTouchListener() {
	            public boolean onTouch(View v, MotionEvent event) {
	                switch(event.getAction()) {
	                case MotionEvent.ACTION_DOWN:
	                	
	                	p.move(0,5);
	                    return true;
	                case MotionEvent.ACTION_UP:
	                    // No longer down
	                    return true;
	                }
	                return false;
	            }
	        });
		
	        leftButton2.setOnTouchListener(new OnTouchListener() {
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
	                    return true;
	                case MotionEvent.ACTION_UP:
	                    // No longer down
	                    return true;
	                }
	                return false;
	            }
	        });
	        
	        upButton2.setOnTouchListener(new OnTouchListener() {
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
	                    return true;
	                case MotionEvent.ACTION_UP:
	                    // No longer down
	                    return true;
	                }
	                return false;
	            }
	        });
				
	        
	        rightButton2.setOnTouchListener(new OnTouchListener() {
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
	                    return true;
	                case MotionEvent.ACTION_UP:
	                    // No longer down
	                    return true;
	                }
	                return false;
	            }
	        });
	    
	        downButton2.setOnTouchListener(new OnTouchListener() {
	            public boolean onTouch(View v, MotionEvent event) {
	                switch(event.getAction()) {
	                case MotionEvent.ACTION_DOWN:
	                	p.setUp(false);
	                	p.setDown(true);
	                	
	                	p.setLeft(false);
	                	p.setRight(false);
//	                	bullet.setX(p.getPos()[0]);
//	                	bullet.setY(p.getPos()[1]);
//	                	
//	                	bullet.move(0, -1);
//	                	
	                    return true;
	                case MotionEvent.ACTION_UP:
	                    // No longer down
	                    return true;
	                }
	                return false;
	            }
	        });
		a = new int[2];
		a2 = new int[2];
		a2[0] = 100;
		a2[1] = 200;
		int[] location = new int[2];
		location[0] = 200;
		location[1] = 200;
		//int[] pos, int[] len, int hp, BitmapDrawable[] images, boolean ignoresWalls, int damage, int speed, int armor, GridMap grid


		ghost = new BasicGhost(a,grid,this);
		ghost1 = new BasicGhost(location,grid,this);

		//int hp, Bitmap[] images, int armor, GridMap grid,Context context
		p = new Player(50, 10, grid, this);
		p.setPos(a2);

//		bullet = new Bullet(10,10,grid,this);
//		bullet.setX(100);
//		bullet.setY(100);
//		

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
		Canvas canvas;
		boolean running = false;
		public DrawingPanel(Context context) {
			super(context);
			sh = getHolder();
			paint.setColor(Color.BLUE);
			paint.setStyle(Style.FILL);
		
		}
		

		@Override
		public void run() {
			while(running)
			{
				if(!sh.getSurface().isValid())
					continue;
				canvas = sh.lockCanvas();
				onDraw(canvas);
				sh.unlockCanvasAndPost(canvas);

			}
			
			
		}
		public void onDraw(Canvas canvas) {
	
			Paint paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
			paint2.setColor(Color.GREEN);

			canvas.drawARGB(255, 0, 0, 0);
			ArrayList<com.example.ghosthunter.Character.Character> toDraw = grid.getCharList(0, 0, canvas.getWidth(), canvas.getHeight());
			for(int a = 0; a < toDraw.size(); a++)
			{
				
				com.example.ghosthunter.Character.Character temp = toDraw.get(a);
				toDraw.get(a).update(canvas);
				
				if(temp instanceof Ghost)
					((Ghost)temp).move(1,1,p); //replace with actual direction later
				
			}
		

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
