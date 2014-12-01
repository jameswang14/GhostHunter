package com.example.ghosthunter.GridMap;

import java.util.ArrayList;

import android.graphics.Rect;

public class GridRoom {
	
	int lenX, lenY;
	int posX, posY;
	
	public GridRoom(int lx, int ly, int posX, int posY) {
		this.lenX=lx;
		this.lenY=ly;
		this.posX=posX;
		this.posY=posY;
	}
	
	public Rect getRect(){
		return new Rect(this.posX,this.posY+this.lenY,this.posX+this.lenX,this.lenY);
	}
	
	//What it says on the tin
	public ArrayList<int[]> getClear() {
		ArrayList<int[]> clears = new ArrayList<int[]>();
		for(int i=0; i<this.lenX; i++) {
			for(int j=0; j<this.lenY; j++) {
				clears.add(new int[]{this.posX+i,this.posY+j});
			}
		}
		return clears;
	}
	
	//What it says on the tin
	public ArrayList<int[]> getWall() {
		ArrayList<int[]> walls = new ArrayList<int[]>();
		for(int i=0; i<this.lenX; i++) walls.add(new int[]{this.posX+i,this.posY});
		for(int i=0; i<this.lenX; i++) walls.add(new int[]{this.posX+i,this.posY+this.lenY});
		for(int i=0; i<this.lenY; i++) walls.add(new int[]{this.posX,this.posY+i});
		for(int i=0; i<this.lenY; i++) walls.add(new int[]{this.posX+this.lenX,this.posY+i});
		
		return walls;
	}
	
	public void shift(int x, int y) {
		this.posX+=x;
		this.posY+=y;
	}
}
