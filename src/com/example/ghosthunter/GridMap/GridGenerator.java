package com.example.ghosthunter.GridMap;

import com.example.ghosthunter.Character.Character;

public class GridGenerator {
	
	int roomsize;
	int roomcap;
	GridRoom[] rooms;
	
	//This is literally John Madden's marionette of a corpse twisted into code format.
	//Handles the semi-random generation of the rooms
	//The size argument handles the average size of the room as a square
	//The roomcap is the total number of rooms.
	
	public GridGenerator(int roomsize, int roomcap) {
		GridRoom temproom;
		for(int i=0; i<roomcap; i++) {
			temproom = CreateRoom((roomsize + (int)(3*Math.random()) -1),((int)(i*Math.random()))*roomsize,((int)(i*Math.random()))*roomsize);
			for(GridRoom all : rooms) {
				while(this.overlap(all, temproom)) {
					temproom.shift((int)(2*Math.random()),(int)(2*Math.random()));
				}
			}
		}
	}
	
	public GridRoom CreateRoom(int size, int posX, int posY) {
		int lenX = size + (int)(3*Math.random()) -1;
		int lenY = size + (int)(3*Math.random()) -1;
		return (new GridRoom(lenX,lenY,posX,posY));
	}

	public boolean overlap(GridRoom A, GridRoom B){ //Fairly simple collision detection
		return A.getRect().intersect(B.getRect());
	}
	
}