package com.example.ghosthunter.GridMap;

//Bad practice in general, but should be fine here
import com.example.ghosthunter.Character.Bullet;
import com.example.ghosthunter.Character.Character;
import com.example.ghosthunter.Environment.Environment;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.Rect;
import java.util.ArrayList;

public class GridMap {
	
	int lenX, lenY;
	int roomsize,roomcap;
	ArrayList<Character> chars;
	ArrayList<Bullet> bullets;
	ArrayList<Environment> envis;
	GridGenerator GG;
	
	
	public GridMap(int x, int y, int roomsize, int roomcap){
		this.lenX=x;
		this.lenY=y;
		chars = new ArrayList<Character>();
		envis = new ArrayList<Environment>(); 
		bullets = new ArrayList<Bullet>();
		
		//WARNING: These next two functions take computational time
		GG = new GridGenerator(roomsize, roomcap);
		envis.addAll(GG.generateWalls(this));
	}
	
	public void addCharacter(Character character){
		this.chars.add(character);
	}
	
	public void addEnvironment(Environment environment){
		this.envis.add(environment);
	}
	
	public void addBullet(Bullet bullet)
	{
		bullets.add(bullet);
	}
	
	public ArrayList<Character> getCharList(int minX, int maxX, int minY, int maxY){ //returns a list of all the Character objects that need to be drawn
		Rect rectum = new Rect(minX,maxY, maxX, maxX);
		ArrayList<Character> hitler = new ArrayList<Character>();
		for(Character sodomy: this.chars){
			if(!sodomy.getRect().intersect(rectum))
				hitler.add(sodomy);
		}
		//Character[] himmler = (Character[])hitler.toArray(); //might cause problems with the Object[]  - it did 
		return hitler;
	}
	
	public ArrayList<Bullet> getBulletList(int minX, int maxX, int minY, int maxY){ //returns a list of all the Character objects that need to be drawn
		Rect rectum = new Rect(minX,maxY, maxX, maxX);
		ArrayList<Bullet> hitler = new ArrayList<Bullet>();
		for(Bullet sodomy: this.bullets){
			if(!sodomy.getRect().intersect(rectum))
				hitler.add(sodomy);
		}
		//Character[] himmler = (Character[])hitler.toArray(); //might cause problems with the Object[]  - it did 
		return hitler;
	}
	
	public ArrayList<Environment> getEnviList(int minX, int maxX, int minY, int maxY){ //returns a list of all of the Environment objects that need to be drawn
		Rect rectum = new Rect(minX,maxY, maxX, maxX);
		ArrayList<Environment> hitler = new ArrayList<Environment>();
		for(Environment sodomy: this.envis){
			if(!sodomy.getRect().intersect(rectum))
				hitler.add(sodomy);
		}
		Environment[] himmler = (Environment[])hitler.toArray();
		return hitler;
	}
	
	public boolean overlap(Character A, Character B){ //Fairly simple collision detection
		return A.getRect().intersect(B.getRect());
	}
	
	/*
	 * Right now this checks against all other sprites.
	 * This is quite inefficient, but this is the quickest way to code.
	 * I'm considering making some TreeSets of all the Environment objects,
	 * since the Character list likely won't go beyond 20 or so, which isn't
	 * too computationally intensive.
	 * The TreeSet route would make use of two sorted lists, with all ghosts
	 * and characters in it. Then it would check against nearby items in the list.
	 */
	public void detectBulletHit() //to be completed; automatically checks on each onDraw if a bullet has collided with a ghost
	{
		
	}
	
	public boolean moveAllowed(Character A, int direction){
		int xoff = (int)Math.cos(Math.PI * direction); //calculate the x offset
		int yoff = (int)Math.sin(Math.PI * direction); // calculate the y offset
		for(Character ch : this.chars){ //check for all of the characters
			if(A.getPos()[0]+A.getLen()[0]+xoff == ch.getPos()[0]+ch.getLen()[0]){
				if(A.getPos()[1]+A.getLen()[1]+yoff == ch.getPos()[1]+ch.getLen()[1])
					return false;
			}
		}
		for(Environment en : this.envis){ //check for all of the nonwalkable enviro's
			if(!en.getWalkable()){
				if(A.getPos()[0]+A.getLen()[0]+xoff == en.getPos()[0]+en.getLen()[0]){
					if(A.getPos()[1]+A.getLen()[1]+yoff == en.getPos()[1]+en.getLen()[1])
						return false;
				}
			}
		}
		return true;
	}
	
}
