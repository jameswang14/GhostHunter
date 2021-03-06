package com.example.ghosthunter.Ghost;

import com.example.ghosthunter.Character.Character;
import com.example.ghosthunter.Character.Player;
import com.example.ghosthunter.GridMap.GridMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;

public abstract class Ghost extends Character {
	
	private boolean ignoresWalls; //for use by subclasses
	private int damage;
	private int speed; //capped at 10
	private int velocityX = 1, velocityY = 1;
	
	public Ghost(int[] pos, int[] len, int hp, boolean ignoresWalls, int damage, int speed, int armor, GridMap grid, Context context){
		super(hp,armor,grid, context);
		super.setPos(pos);
		super.setLen(len);
		this.ignoresWalls=ignoresWalls;
		this.damage=damage;
		this.speed=speed;
		grid.addCharacter(this);
	}
	
	public void move(int x, int y, Player p){
		//I assume that the thread has already checked that the ghost can move in the given direction
		int[] nazi = super.getPos();
		if(nazi[0] > 400 || nazi[0] < 0)
			this.setVelocityX(-velocityX);
		
		if(nazi[1] > 600 || nazi[1] < 0)
			this.setVelocityY(-velocityY);
		
		int xcor = this.getPos()[0];
		int ycor = this.getPos()[1];
		int xcorPlayer = p.getPos()[0];
		int ycorPlayer = p.getPos()[1];
		
		if(xcor>xcorPlayer)
			this.setVelocityX(Math.min(-velocityX, velocityX));
		if(xcor<xcorPlayer)
			this.setVelocityX(Math.abs(velocityX));
		if(ycor>ycorPlayer)
			this.setVelocityY(Math.min(-velocityY, velocityY));
		if(ycor<ycorPlayer)
			this.setVelocityY(Math.abs(velocityY));
		
		nazi[0] = nazi[0] + this.velocityX; 
		nazi[1] = nazi[1] + this.velocityY;
		
		super.setPos(nazi);
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

	//this will be for the pathfinding
	public abstract int getNextDirection(Character target); //offers the possibility of ghost-ghost anger
	
	public void damage(Character target){ //to be overriden as necessary
		
		target.takeDamage(damage);
		 //in case we want to display the damage for whatever reason
	}

	public int getSpeed(){
		return this.speed;
	}
	
	public int getDamage(){
		return this.damage;
	}
	
	public void setSpeed(int speed){
		this.speed=speed;
	}
	
	public void setDamage(int damage){
		this.damage=damage;
	}
	public Bitmap draw(){ //currently does NOT use status effects
		if(super.getDead()) return super.getImages()[0]; 	//rekt
		else return super.getImages()[1];					//not rekt
	}
	public void update(Canvas c)
	{
		c.drawBitmap(draw(), getPos()[0],getPos()[1],new Paint(Paint.ANTI_ALIAS_FLAG));
		/*Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
		p.setColor(Color.WHITE);
		c.drawRect(getRect(), p);*/
	}
}
