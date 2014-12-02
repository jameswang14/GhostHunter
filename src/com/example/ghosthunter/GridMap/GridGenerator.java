package com.example.ghosthunter.GridMap;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import com.example.ghosthunter.Character.Character;
import com.example.ghosthunter.Environment.*;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class GridGenerator {
	
	int roomsize;
	int roomcap;
	ArrayList<GridRoom> rooms;
	static int[][] rotator = new int[][]{{1,0},{0,-1},{-1,0},{0,1}};
	
	//This is literally John Madden's marionette of a corpse twisted into code format.
	//Handles the semi-random generation of the rooms
	//The size argument handles the average size of the room as a square
	//The roomcap is the total number of rooms.
	//Spacing is handled on integer increments. I don't know the sizing for the rest of the
	//images, so all of these need to be scaled.

	public GridGenerator(int roomsize, int roomcap) {
		GridRoom temproom;
		rooms = new ArrayList<GridRoom>();
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
	public ArrayList<Environment> generateWalls(GridMap grid, Context context) {
		
		ArrayList<Environment> envis = new ArrayList<Environment>();
		CopyOnWriteArrayList<int[]> gridcheck = new CopyOnWriteArrayList<int[]>();
		CopyOnWriteArrayList<int[]> roomhold;
		ArrayList<int[]> clears = new ArrayList<int[]>();
		
		int xoff,yoff,dir;
		int[] neighbors = new int[4];
		int caseint = 0;
		Wall wall;
		
		for(GridRoom room : this.rooms) {
			//get list of walls
			roomhold = room.getWalls();
			for(int[] w : roomhold) {
				gridcheck.add(w);
			}
			//get list of clears
			roomhold = room.getClears();
			for(int[] c : roomhold) {
				clears.add(c);
			}
		}
		//remove all clear spots
		for(int[] twall : gridcheck) {
			for(int[] tclear : clears) {
				if(twall[0]==tclear[0] && twall[1]==tclear[1]) gridcheck.remove(twall);
			}
		}
		
		//SHOULD HAVE USED A MAP
		for(int[] w : gridcheck) {
			neighbors = new int[]{0,0,0,0};
			wall = new Wall(w, context);
			for(int i=0; i<4; i++) {
				//check all adjacent tiles for other walls
				for(int[] ww : gridcheck) {
					if(w[0]+GridGenerator.rotator[i][0]==ww[0] && w[1]+GridGenerator.rotator[i][1]==ww[1]) {
						neighbors[i]=1; //check off that box as containing a wall
						break;
					}
				}
			}
			//convert the neighbors list to an int as if in binary
			caseint=neighbors[0]*8+neighbors[1]*4+neighbors[2]*2+neighbors[3];
			
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
			envis.add(wall);
		}
		
		return envis;
	}

}