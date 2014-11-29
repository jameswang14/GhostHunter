package com.example.ghosthunter.Character;

import com.example.ghosthunter.R;
import com.example.ghosthunter.GridMap.GridMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.View.OnTouchListener;

public class Bullet extends Character{

	int x = 0, y = 0;
	int velocityX = 2, velocityY = 2;
	
	Bitmap[] images = new Bitmap[1];
	
	
	public Bullet(int hp, int armor, GridMap grid, Context context) {
		super(hp, armor, grid, context);
		Bitmap b = BitmapFactory.decodeResource(c.getResources(), R.drawable.bullet);
		images[0] = b;
		grid.addCharacter(this);
		
		
	}


	//(-1,0) means left, (1,0) menas right, (-1,0) means down, (1,0) up
	//when move is called, pass these combos to determine the direction of the bullet
	public void move(int a, int b){
		
		if(a == -1)
			this.setX(this.getX() + velocityX);
		if(a == 1)
			this.setX(this.getX() - velocityX);
		if(b == -1 && a == 0)
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
