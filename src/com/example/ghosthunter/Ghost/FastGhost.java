package com.example.ghosthunter.Ghost;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import com.example.ghosthunter.Character.Character;
import com.example.ghosthunter.GridMap.GridMap;


/* The standard Fast Ghost
 * Like a Basic Ghost, only faster.
 * Easy to kill, harder to avoid.
 * Damage: 10. Speed: 1. Armor: 0. HP: 5. Ignores walls.
 * 2 images, 1x1
 * [Dead,Alive]
 */

//Sample instantiation
//FastGhost JohnMadden = new FastGhost(pos, images, grid);

public class FastGhost extends Ghost {
	
	public FastGhost(int[] pos,  GridMap grid,Context c){
		super(pos, new int[]{1,1}, 5, true, 10, 1, 0, grid,c);
	}
	
	//0-E,1-N,2-W,3-S
	public int getNextDirection(Character target){ //to be fixed
		if(Math.abs(target.getPos()[0]-super.getPos()[0]) < Math.abs(target.getPos()[1]-super.getPos()[1])){
			if(target.getPos()[0]-super.getPos()[0] < 0) return 0;
				return 2;
		}
		if(target.getPos()[1]-super.getPos()[1] > 0) return 1;
			return 3;
	}

	public Bitmap draw(){ //currently does NOT use status effects
		if(super.getDead()) return super.getImages()[0]; 	//rekt
		else return super.getImages()[1];					//not rekt
	}
}
