package com.example.ghosthunter;

import com.example.ghosthunter.Character.Player;
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
	int playerX = 250;
	int playerY = 250;
	
	Bitmap b;
	Bitmap b2;
	BasicGhost ghost;
	BasicGhost ghost1;
	Player p;
	boolean running = false;
	int[] a;
	int[] a2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		d = new DrawingPanel(this);
		setContentView(d);
		b2 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		b=BitmapFactory.decodeResource(getResources(), R.drawable.ghosticon);
		a = new int[2];
		a2 = new int[2];
		a2[0] = 100;
		a2[1] = 200;
		
		a[0]=0;
		a[1]=0;
		Bitmap[] images = new Bitmap[2];
		Bitmap[] images2 = new Bitmap[2];
		images2[0] = b2;
		images2[1] = b2;
		
		images[1] = b;
		images[0] = b;
		int[] location = new int[2];
		location[0] = 100;
		location[1] = 100;
		//int[] pos, int[] len, int hp, BitmapDrawable[] images, boolean ignoresWalls, int damage, int speed, int armor, GridMap grid
		GridMap grid = new GridMap(0,0,0);
<<<<<<< HEAD
		ghost = new BasicGhost(a,grid,this);
		ghost1 = new BasicGhost(location,grid,this);

=======
		ghost = new BasicGhost(a,images,grid,this);
		ghost1 = new BasicGhost(location,images,grid,this);
		
		//int hp, Bitmap[] images, int armor, GridMap grid,Context context
		p = new Player(50, images2, 10, grid, this);
		p.setPos(a2);
		//grid.addCharacter(ghost);
>>>>>>> 77c6d3d3019e0cc72b8f78efbd3e9fd0967d1f40
		

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
			Paint paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
			paint2.setColor(Color.GREEN);
			canvas.drawARGB(255, 0, 0, 0);
			xcor++;
			ycor++;
			canvas.drawBitmap(ghost.draw(), xcor,ycor, paint);
			canvas.drawBitmap(p.draw(), playerX,playerY , paint2);
		

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
