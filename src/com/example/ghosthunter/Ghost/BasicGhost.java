package com.example.ghosthunter.Ghost;

import android.graphics.drawable.BitmapDrawable;
import com.example.ghosthunter.Character.Character;


/* The standard ghost right here.
 * It chases after you and tries to eat you.
 * Easy to kill, easy to avoid.
 * Damage: 10. Speed: 3. Armor: 0. HP: 15. Ignores walls.
 * 2 images, 2x2
 * [Dead,Alive]
 */

public class BasicGhost extends Ghost {
	
	public BasicGhost(int[] pos, BitmapDrawable[] images, int speed){
		super(pos, new int[]{2,2}, 15, images, true, 10, 3, 0);
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
	
	public int damage(Character target){
		int deal = target.getArmor()-super.getDamage();
		if(deal<0) deal=1;
		target.takeDamage(deal);
		return deal; //in case we want to display the damage for whatever reason
	}
		
}
