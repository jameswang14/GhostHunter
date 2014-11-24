package com.example.ghosthunter.Ghost;

import com.example.ghosthunter.Character.Character;
import com.example.ghosthunter.GridMap.GridMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

public abstract class Ghost extends Character {
	
	private boolean ignoresWalls; //for use by subclasses
	private int damage;
	private int speed; //capped at 10
	
	public Ghost(int[] pos, int[] len, int hp, boolean ignoresWalls, int damage, int speed, int armor, GridMap grid, Context context){
		super(hp,armor,grid, context);
		super.setPos(pos);
		super.setLen(len);
		this.ignoresWalls=ignoresWalls;
		this.damage=damage;
		this.speed=speed;
		grid.addCharacter(this);
	}
	
	public void move(int direction){
		//I assume that the thread has already checked that the ghost can move in the given direction
		int[] nazi = super.getPos();
		nazi[0]++; //this should work, but might cause problems
		nazi[1]++;
		super.setPos(nazi);
	}
	
	//this will be for the pathfinding
	public abstract int getNextDirection(Character target); //offers the possibility of ghost-ghost anger
	
	public int damage(Character target){ //to be overriden as necessary
		int deal = target.getArmor()-this.damage;
		if(deal<0) deal=1;
		target.takeDamage(deal);
		return deal; //in case we want to display the damage for whatever reason
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
}
