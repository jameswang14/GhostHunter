package com.example.ghosthunter.Ghost;

import android.graphics.drawable.BitmapDrawable;
import com.example.ghosthunter.Character.Character;
import com.example.ghosthunter.GridMap.GridMap;


/* The standard TankGhost
 * Big, slow, powerful, armored.
 * Hard to kill, easy to avoid.
 * Damage: 25. Speed: 6. Armor: 10. HP: 100. Ignores walls.
 * 2 images, 2x2
 * [Dead,Alive]
 */

//Sample instantiation
//TankGhost JohnMadden = new TankGhost(pos, images, grid);

public class TankGhost extends Ghost {
	
	public TankGhost(int[] pos, BitmapDrawable[] images, GridMap grid){
		super(pos, new int[]{2,2}, 100, images, true, 25, 6, 10, grid);
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

	public BitmapDrawable draw(){ //currently does NOT use status effects
		if(super.getDead()) return super.getImages()[0]; 	//rekt
		else return super.getImages()[1];					//not rekt
	}
}
