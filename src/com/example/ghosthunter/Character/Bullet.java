package com.example.ghosthunter.Character;

import com.example.ghosthunter.R;
import com.example.ghosthunter.GridMap.GridMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View.OnTouchListener;

public class Bullet {

	int x = 0, y = 0;
	int a = 0, b = 0;
	int lenY = 25, lenX = 25; //probably will change
	int velocityX = 2, velocityY = 2;
	
	Bitmap[] images = new Bitmap[1];
	
	
	public Bullet(int xstart, int ystart, int xdir, int ydir, GridMap grid, Context context) {
		Bitmap image = BitmapFactory.decodeResource(context.getResources(), R.drawable.bullet);
		x = xstart;
		y = ystart;
		a = xdir;
		b = ydir;
		images[0] = image;
		grid.addBullet(this);
		
		
	}
	public Rect getRect(){
		return new Rect(this.x,this.y+this.lenY,this.x+this.lenX,this.lenY);
	}

	//(-1,0) means left, (1,0) menas right, (-1,0) means down, (1,0) up
	//when move is called, pass these combos to determine the direction of the bullet
	public void move(){
		
		if(a == -1)
			this.setX(this.getX() - velocityX);
		if(a == 1)
			this.setX(this.getX() + velocityX);
		if(b == -1)
			this.setY(this.getY() + velocityY);
		if(b == 1)
			this.setY(this.getY() - velocityY);
		
	}
	public Bitmap draw(){
		return images[0];
	}
	
	public void update(Canvas c){
		c.drawBitmap(draw(), this.getX(), this.getY(), new Paint(Paint.ANTI_ALIAS_FLAG));
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getVelocityX() {
		return velocityX;
	}

	public void setVelocityX(int velocityX) {
		this.velocityX = velocityX;
	}

	public int getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(int velocityY) {
		this.velocityY = velocityY;
	}
	
	public void setImages(Bitmap[] images){
		this.images = images;
	}
}
