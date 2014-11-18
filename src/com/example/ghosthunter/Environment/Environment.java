package com.example.ghosthunter.Environment;

import android.graphics.drawable.BitmapDrawable;

public abstract class Environment {
	
	private int posX,posY;
	private int lenX,lenY;
	private boolean walkable,destructible;
	private int status; //contains facing direction and destroyed or not
	private BitmapDrawable[] images; //for destructibles, this will have more images
	
	
	
	// I define these functions because they are needed for all environment subtypes

	public abstract BitmapDrawable draw();
	
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
	
	public boolean getWalkable(){
		return this.walkable;
	}
	
	public boolean getDestructible(){
		return this.destructible;
	}
	
	public BitmapDrawable[] getImages(){
		return this.images;
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

}
