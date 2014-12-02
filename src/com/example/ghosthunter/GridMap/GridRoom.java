package com.example.ghosthunter.GridMap;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import android.graphics.Rect;

//Rectangular rooms of variable size. Each has four hallways leading out
// that may or may not actually connect to other rooms.
//Requires that clears override walls.
public class GridRoom {

	int lenX, lenY;
	int posX, posY;

	public GridRoom(int lx, int ly, int posX, int posY) {
		this.lenX = lx;
		this.lenY = ly;
		this.posX = posX;
		this.posY = posY;
	}

	public Rect getRect() {
		return new Rect(this.posX, this.posY + this.lenY,
				this.posX + this.lenX, this.lenY);
	}

	// Generates the spaces in the rooms
	// Also handles the construction of hallways
	public CopyOnWriteArrayList<int[]> getClears() {
		CopyOnWriteArrayList<int[]> clears = new CopyOnWriteArrayList<int[]>();

		// main region
		for (int i = 0; i < this.lenX; i++) {
			for (int j = 0; j < this.lenY; j++) {
				clears.add(new int[] { this.posX + i, this.posY + j });
			}
		}
		// hallways
		for (int i = 0; i < this.lenX; i++)
			clears.add(new int[] { this.posX + i, this.posY + lenY / 2 });
		for (int i = 0; i < this.lenX; i++)
			clears.add(new int[] { this.posX - i, this.posY + lenY / 2 });
		for (int i = 0; i < this.lenY; i++)
			clears.add(new int[] { this.posX + lenX / 2, this.posY + i });
		for (int i = 0; i < this.lenY; i++)
			clears.add(new int[] { this.posX + lenX / 2, this.posY - i });

		return clears;
	}

	// Coats the clears in a nice layer of walls
	// Then makes room for the filling
	public CopyOnWriteArrayList<int[]> getWalls() {
		CopyOnWriteArrayList<int[]> walls = new CopyOnWriteArrayList<int[]>();
		CopyOnWriteArrayList<int[]> clears = this.getClears();
		
		//spincoating
		for (int[] curr : clears) {
			for (int i = -1; i < 2; i++) {
				for (int j = -1; j < 2; j++) {
					walls.add(new int[] { curr[0] + i, curr[1] + j });
				}
			}
		}
		
		//hollowing
		for(int[] twall : walls) {
			for(int[] tclear : clears) {
				if(twall[0]==tclear[0] && twall[1]==tclear[1]) walls.remove(twall);
			}
		}

		return walls;
	}

	public void shift(int x, int y) {
		this.posX += x;
		this.posY += y;
	}
}
