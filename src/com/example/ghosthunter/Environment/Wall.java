package com.example.ghosthunter.Environment;

import android.graphics.drawable.BitmapDrawable;

import com.example.ghosthunter.GridMap.GridMap;

//Requires a larger number of images
/*
0 - EW (1010)
1 - NS (0101)

2 - NE (1100)
3 - NW (0110)
4 - SW (0011)
5 - SE (1001)

6 - NES (1101)
7 - WNE (1110)
8 - SWN (0111)
9 - ESW (1011)

10 - all (1111)
11 - pillar (0000)

*/
public class Wall extends Environment{
	
	int direction; //also handles intersection stuff
	
	//WARNING: NEEDS THE WALL IMAGES
	
	public Wall(BitmapDrawable[] images, GridMap grid, int[] pos){
		super(images,false,false,grid); //not destructible and not walkable
		
		//WARNING: This needs to be scaled
		super.setLen(new int[]{1,1});
		
		super.setPos(pos);
		//direction is set later
	}
	
	public BitmapDrawable draw() {
		return super.getImages()[direction];
	}
	
	//Checking for intersections handled elsewhere
	public void setDirection(int dir) {
		this.direction=dir;
	}
}
