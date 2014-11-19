package com.example.ghosthunter.Character;

import com.example.ghosthunter.GridMap.GridMap;

import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;

//I changed this to an abstract class because I thought inheritance
//would be more useful than implementing something
//I am considering taking the large amount of overlap with Environment
//to create a larger superclass with the various getPos functions
public abstract class Character {

	int posX,posY; //I change these to integers since we should be working with a grid
	int lenX,lenY;
	int hp;
	boolean isDead;
	BitmapDrawable[] images;
	int status; //different from isDead
	int statusRemaining; //added to allow for timed effects
	int armor; //added for damage purposes
	
	public Character(int hp, BitmapDrawable[] images, int armor, GridMap grid){
		this.status = 0;
		this.hp = hp;
		this.images=images;
		this.armor=armor;
		grid.addCharacter(this);
	}
	
	//I added the direction
	public abstract void move(int direction); //exactly what it says on the tin
	public abstract BitmapDrawable draw(); //will return the correct image from the array
	
	//Same thing as with Environment
	//Getters
	public int getStatus(){
		return this.status;
	}

	public int[] getPos(){
		int[] nazi = new int[2]; //there is probably a more elegant way to do this, but I'm tired
		nazi[0]=this.posX;
		nazi[1]=this.posY;
		return nazi;
	}

	public int[] getLen(){
		int[] nazi = new int[2];
		nazi[0]=this.lenX;
		nazi[1]=this.lenY;
		return nazi; //keeping it in theme
	}

	public boolean getDead(){
		return this.isDead;
	}
	
	public BitmapDrawable[] getImages(){
		return this.images;
	}
	
	public int getArmor(){
		return this.armor;
	}
	
	public Rect getRect(){
		return new Rect(this.posX,this.posY+this.lenY,this.posX+this.lenX,this.lenY);
	}
	
	//Setters
	public boolean setStatus(int status){
		if(status<this.images.length) this.status = status; //check to make sure we have a valid status
		else return false;
		return true; //using the boolean method so we can test this one out
	}
	
	public void setPos(int[] pos){
		this.posX=pos[0];
		this.posY=pos[1];
	}
	
	//I don't know why we would need this, but it can't hurt to have
	public void setLen(int[] len){
		this.lenX=len[0];
		this.lenY=len[1];
	}
	
	public void setArmor(int armor){
		this.armor=armor;
	}
	
	public void takeDamage(int amount){
		hp-=amount; //this system does not take into account armor, probably can just override at the player class
		if(hp<=0) isDead = true;
	}
	
	public void tickStatus() { //for poisoning and confusion and whatnot
		if(statusRemaining>0 && status!=0) statusRemaining--;
		if(statusRemaining==0) status=0;
	}
}
