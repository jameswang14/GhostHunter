package com.example.ghosthunter.Ghost;

import android.graphics.drawable.BitmapDrawable;
import com.example.ghosthunter.Character.Character;
import com.example.ghosthunter.GridMap.GridMap;


/* The KamikazeGhost
 * One hit wonder.
 * Easy to kill, hard to avoid.
 * Damage: 50. Speed: 1. Armor: 0. HP: 5. Ignores walls.
 * 2 images, 1x1
 * [Dead,Alive]
 */

//Sample instantiation
//KamikazeGhost JohnMadden = new KamikazeGhost(pos, images, grid);

public class KamikazeGhost extends Ghost {
	
	public KamikazeGhost(int[] pos, BitmapDrawable[] images, GridMap grid){
		super(pos, new int[]{2,2}, 5, images, true, 50, 1, 0, grid);
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
	
	@Override
	public int damage(Character target){ //overriden as necessary
		int deal = target.getArmor()-super.getDamage();
		if(deal<0) deal=1;
		target.takeDamage(deal);
		damage(this); //where the ghost kills itself
		return deal; //in case we want to display the damage for whatever reason
	}
}
