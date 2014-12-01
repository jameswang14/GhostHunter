package com.example.ghosthunter.GridMap;

import java.util.ArrayList;

import android.graphics.Rect;

//Rectangular rooms of variable size. Each has four hallways leading out
// that may or may not actually connect to other rooms.
//Requires that clears override walls.
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
	
	//Generates the spaces in the rooms
	//Also handles the construction of hallways
	public ArrayList<int[]> getClear() {
		ArrayList<int[]> clears = new ArrayList<int[]>();
		
		//main region
		for(int i=0; i<this.lenX; i++) {
			for(int j=0; j<this.lenY; j++) {
				clears.add(new int[]{this.posX+i,this.posY+j});
			}
		}
		//hallways
		for(int i=0; i<this.lenX; i++) clears.add(new int[]{this.posX+i,this.posY+lenY/2});
		for(int i=0; i<this.lenX; i++) clears.add(new int[]{this.posX-i,this.posY+lenY/2});
		for(int i=0; i<this.lenY; i++) clears.add(new int[]{this.posX+lenX/2,this.posY+i});
		for(int i=0; i<this.lenY; i++) clears.add(new int[]{this.posX+lenX/2,this.posY-i});
		
		return clears;
	}
	
	//Coats the clears in a nice layer of walls
	//Then makes room for the filling
	public ArrayList<int[]> getWall(ArrayList<int[]> clears) {
		ArrayList<int[]> walls = new ArrayList<int[]>();
		
		for(int[] curr : clears) {
			for(int i=-1; i<2; i++) {
				for(int j=-1; j<2; j++) {
					walls.add(new int[]{curr[0]+i,curr[1]+j});
				}
			}
		}
		walls.remove(clears);
		
		return walls;
	}
	
	public void shift(int x, int y) {
		this.posX+=x;
		this.posY+=y;
	}
}
