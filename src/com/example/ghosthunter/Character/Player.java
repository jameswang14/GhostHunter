package com.example.ghosthunter.Character;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.example.ghosthunter.R;
import com.example.ghosthunter.GridMap.GridMap;

public class Player extends Character{

	int dx, dy;
	int vectorX, vectorY;
	boolean up,down;
	boolean left, right;
	int speed = 13;

	public Player(int hp, int armor, GridMap grid,Context context) {
		super(hp, armor, grid, context);
		setHp(50);
		Bitmap b=BitmapFactory.decodeResource(c.getResources(), R.drawable.down1);
		Bitmap b2=BitmapFactory.decodeResource(c.getResources(), R.drawable.up1);
		Bitmap b3=BitmapFactory.decodeResource(c.getResources(), R.drawable.left1);
		Bitmap b4=BitmapFactory.decodeResource(c.getResources(), R.drawable.right1);
		
		Bitmap[] images = new Bitmap[4];
		images[0] = b;
		images[1] = b2;
		images[2] = b3;
		images[3] = b4;
		
		int[] len = new int[2];
		len[0] = 45; //dimensions based on rigorous empirical testing
		len[1] = 70;
		this.setLen(len);
		
		this.setImages(images);
		grid.addCharacter(this);
		up = false;
		down = true;
		
		
		
		left = false;
		right = false;
	}

	public Rect getRect()
	{
		return new Rect(posX, posY, posX+lenX, posY+lenY);
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

	public int getSpeed()
	{
		return speed;
	}
	
	public void setSpeed(int s)
	{
		speed = s;
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
	
	public void update(Canvas c)
	{
		c.drawBitmap(draw(), getPos()[0],getPos()[1],new Paint(Paint.ANTI_ALIAS_FLAG));
		/*Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
		p.setColor(Color.WHITE);
		c.drawRect(getRect(), p);*/
	}
	

}
