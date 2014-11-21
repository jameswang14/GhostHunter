package com.example.ghosthunter;

import com.example.ghosthunter.Ghost.BasicGhost;
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
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GamePage extends Activity {
	
	Canvas c = new Canvas();
	DrawingPanel d;
	int xcor = 0;
	int ycor = 0;
	Bitmap b;
	BasicGhost ghost;
	BasicGhost ghost1;
	boolean running = false;
	int[] a;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		d = new DrawingPanel(this);
		setContentView(d);
		b=BitmapFactory.decodeResource(getResources(), R.drawable.ghosticon);
		a = new int[2];
		a[0]=0;
		a[1]=0;
		Bitmap[] images = new Bitmap[2];
		images[1] = b;
		images[0] = b;
		int[] location = new int[2];
		location[0] = 100;
		location[1] = 100;
		//int[] pos, int[] len, int hp, BitmapDrawable[] images, boolean ignoresWalls, int damage, int speed, int armor, GridMap grid
		GridMap grid = new GridMap(0,0,0);
		ghost = new BasicGhost(a,images,grid,this);
		ghost1 = new BasicGhost(location,images,grid,this);
		//grid.addCharacter(ghost);
		

	}
	protected void onResume(){
		super.onResume();
		d.resume();
	}
	protected void onDestroy(){
		running = false;
	}
	
	
	public class DrawingPanel extends SurfaceView implements Runnable {
		private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		Thread t = null;
		private SurfaceHolder sh;
		Canvas canvas;
		boolean running = false;
		public DrawingPanel(Context context)
		{
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
			// TODO Auto-generated method stub
			
			
		}
		public void onDraw(Canvas canvas)
		{
			
			canvas.drawARGB(255, 0, 0, 0);
			xcor++;
			ycor++;
			canvas.drawBitmap(ghost.draw(), xcor,ycor, paint);
		

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
