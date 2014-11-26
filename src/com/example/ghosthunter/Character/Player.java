package com.example.ghosthunter.Character;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.example.ghosthunter.R;
import com.example.ghosthunter.GridMap.GridMap;

public class Player extends Character implements OnTouchListener{

	int dx, dy;
	int vectorX, vectorY;
	boolean up,down;
	boolean left, right;

	public Player(int hp, int armor, GridMap grid,Context context) {
		super(hp, armor, grid, context);
		Bitmap b=BitmapFactory.decodeResource(c.getResources(), R.drawable.down1);
		Bitmap b2=BitmapFactory.decodeResource(c.getResources(), R.drawable.up1);
		Bitmap b3=BitmapFactory.decodeResource(c.getResources(), R.drawable.left1);
		Bitmap b4 = BitmapFactory.decodeResource(c.getResources(), R.drawable.right1);
		
		Bitmap[] images = new Bitmap[4];
		images[0] = b;
		images[1] = b2;
		images[2] = b3;
		images[3] = b4;
		
		this.setImages(images);
		grid.addCharacter(this);
		up = false;
		down = true;
		
		left = false;
		right = false;
	}

	
	public void move(int x, int y) {
		//Log.e("tag", "in move");
		int[] nazi = super.getPos();
		nazi[0]=nazi[0]+x; //this should work, but might cause problems
		nazi[1]=nazi[1]+y;
		super.setPos(nazi);
	}

	@Override
	public Bitmap draw() {
		//Log.e("tag", "in draw player");
		if(up == false && down == true && left == false && right == false)
			return images[0];
		if(up == true && down == false && left == false && right == false)
			return images[1];
		if(up == false && down == false && left == true && right == false)
			return images[2];
		
		return images[3];
	}

	public boolean isLeft() {
		return left;
	}


	public void setLeft(boolean left) {
		this.left = left;
	}


	public boolean isRight() {
		return right;
	}


	public void setRight(boolean right) {
		this.right = right;
	}


	public boolean isUp() {
		return up;
	}


	public void setUp(boolean up) {
		this.up = up;
	}


	public boolean isDown() {
		return down;
	}


	public void setDown(boolean down) {
		this.down = down;
	}


	@Override
	public boolean onTouch(View v, MotionEvent me) {
		Log.e("tag", "In onTouch");
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		switch(me.getAction()){
		case MotionEvent.ACTION_DOWN:
			this.move((int)me.getX(),(int) me.getY());
//			dx = (int) (me.getX() - getPos()[0]);
//			dy = (int) (me.getY() - getPos()[1]);
//			vectorX = (int) (getPos()[0] / Math.sqrt((double)(dx^2 + dy^2)));
//			vectorY = (int) (getPos()[1] / Math.sqrt((double)(dx^2 + dy^2)));//best i can do with integers right now
//			this.move(vectorX, vectorY);
			break;
		
		case MotionEvent.ACTION_MOVE:
			this.move((int)me.getX(),(int) me.getY());
//			dx = (int) (me.getX() - getPos()[0]);
//			dy = (int) (me.getY() - getPos()[1]);
//			vectorX = (int) (getPos()[0] / Math.sqrt((double)(dx^2 + dy^2)));
//			vectorY = (int) (getPos()[1] / Math.sqrt((double)(dx^2 + dy^2)));//best i can do with integers right now
//			this.move(vectorX, vectorY);
			break;
			
		}
		return true;
	}
	
	public void update(Canvas c)
	{
		 //replace with direction calculation
		c.drawBitmap(draw(), getPos()[0],getPos()[1],new Paint(Paint.ANTI_ALIAS_FLAG));
	}
	

}
