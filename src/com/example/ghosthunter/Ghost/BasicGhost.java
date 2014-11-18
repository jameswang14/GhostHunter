package com.example.ghosthunter.Ghost;

import android.graphics.drawable.BitmapDrawable;
import com.example.ghosthunter.Character.Character;


/* The stand ghost right here.
 * It chases after you and tries to eat you.
 * Easy to kill, easy to avoid.
 * Damage: 10. Speed: 3. Armor: 0. HP: 15. Ignores walls.
 * 2 images, 2x2
 * [Dead,Alive]
 */

public class BasicGhost extends Ghost {
	
	public BasicGhost(int[] pos, BitmapDrawable[] images, int damage, int speed){
		super(pos, new int[]{2,2}, 15, images, true, damage, 3, 0);
	}
	
	public int getNextDirection(Character target){
		return Math.max(target.getPos()[0]-super.getPos()[0], target.getPos()[1]-super.getPos()[1]); //return the bigger difference
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
	
	public void tickStatus(){
		
	}
}
