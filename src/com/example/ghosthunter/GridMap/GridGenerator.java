package com.example.ghosthunter.GridMap;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import com.example.ghosthunter.Character.Character;
import com.example.ghosthunter.Environment.*;
import java.util.ArrayList;

public class GridGenerator {
	
	int roomsize;
	int roomcap;
	ArrayList<GridRoom> rooms;
	BitmapDrawable[] images; //TO PUT THE WALL IMAGES HERE
	Context context;
	
	//This is literally John Madden's marionette of a corpse twisted into code format.
	//Handles the semi-random generation of the rooms
	//The size argument handles the average size of the room as a square
	//The roomcap is the total number of rooms.
	//Spacing is handled on integer increments. I don't know the sizing for the rest of the
	//images, so all of these need to be scaled.

	public GridGenerator(int roomsize, int roomcap, Context context) {
		GridRoom temproom;
		rooms = new ArrayList<GridRoom>();
		this.context=context;
		for (int i = 0; i < roomcap; i++) {
			temproom = CreateRoom((roomsize + (int) (3 * Math.random()) - 1),
					((int) (i * Math.random())) * roomsize,
					((int) (i * Math.random())) * roomsize);
			for (GridRoom all : rooms) {
				while (this.overlap(all, temproom)) {
					temproom.shift((int) (2 * Math.random()),(int) (2 * Math.random()));
				}
			}
			this.rooms.add(temproom);
		}
	}

	public GridRoom CreateRoom(int size, int posX, int posY) {
		int lenX = size + (int) (3 * Math.random()) - 1;
		int lenY = size + (int) (3 * Math.random()) - 1;
		return (new GridRoom(lenX, lenY, posX, posY));
	}

	public boolean overlap(GridRoom A, GridRoom B) { // Fairly simple collision
														// detection
		return A.getRect().intersect(B.getRect());
	}
	
	//MUST BE RUN IN INITIALIZATION
	public ArrayList<Environment> generateWalls(GridMap grid) {
		ArrayList<Environment> envis = new ArrayList<Environment>();
		ArrayList<int[]> gridcheck = new ArrayList<int[]>();
		ArrayList<int[]> roomhold;
		ArrayList<int[]> clears = new ArrayList<int[]>();
		int xoff,yoff,dir;
		int[] neighbors = new int[4];
		int caseint = 0;
		Wall wall;
		
		for(GridRoom room : this.rooms) {
			//get list of walls
			roomhold = room.getWalls();
			for(int[] w : roomhold) {
				if(!gridcheck.contains(w)) gridcheck.add(w);
			}
			//get list of clears
			roomhold = room.getClears();
			for(int[] c : roomhold) {
				if(!gridcheck.contains(c)) clears.add(c);
			}
		}
		//remove all clear spots
		gridcheck.removeAll(clears);
		
		for(int[] w : gridcheck) {
			wall = new Wall(grid,w, this.context);
			envis.add(wall);
			for(int i=0; i<4; i++) {
				xoff = (int)Math.cos(Math.PI * i); //calculate the x offset
				yoff = (int)Math.sin(Math.PI * i); // calculate the y offset
				
				//check all adjacent tiles for other walls
				if(gridcheck.contains(new int[]{w[0]+xoff,w[1]+yoff})) {
					neighbors[i]=1; //check off that box as containing a wall
				}
			}
			//convert the neighbors list to an int as if in binary
			for(double i=0; i<4; i++) caseint+=(int)(Math.pow((double)(2),i));
			
			//Refer to the Wall.java file
			switch(caseint) {
				case(10): dir=0; break;
				case(5): dir=1; break;
				
				case(12): dir=2; break;
				case(6): dir=3; break;
				case(3): dir=4; break;
				case(9): dir=5; break;
				
				case(13): dir=6; break;
				case(14): dir=7; break;
				case(7): dir=8; break;
				case(11): dir=9; break;
				
				case(15): dir=10; break;
				case(0): dir=11; break;
				
				default: dir = 11; break;
			}
			wall.setDirection(dir);
		}
		
		return envis;
	}

}