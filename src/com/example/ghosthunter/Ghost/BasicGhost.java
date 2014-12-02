package com.example.ghosthunter.Ghost;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

import com.example.ghosthunter.R;
import com.example.ghosthunter.Character.Character;
import com.example.ghosthunter.GridMap.GridMap;


/* The standard ghost right here.
 * It chases after you and tries to eat you.
 * Easy to kill, easy to avoid.
 * Damage: 10. Speed: 3. Armor: 0. HP: 15. Ignores walls.
 * 2 images, 1x1
 * [Dead,Alive]
 */

//Sample instantiation
//BasicGhost JohnMadden = new BasicGhost(pos, images, grid);

public class BasicGhost extends Ghost {
	
	public BasicGhost(int[] pos, GridMap grid,Context c){
		//int[] pos, int[] len, int hp, BitmapDrawable[] images, boolean ignoresWalls, int damage, int speed, int armor, GridMap grid
		super(pos, new int[]{1,1}, 15, true, 10, 3, 0, grid,c);
		Bitmap b=BitmapFactory.decodeResource(c.getResources(), R.drawable.ghostdown1blurry);
		Bitmap[] images = new Bitmap[5];
		images[0] = b;
		images[1] = b;
		int[] len = new int[2];
		len[0] = 50;
		len[1] = 70;
		setLen(len);
		this.setImages(images);
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
