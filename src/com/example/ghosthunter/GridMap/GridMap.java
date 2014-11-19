package com.example.ghosthunter.GridMap;

//Bad practice in general, but should be fine here
import com.example.ghosthunter.Character.Character;
import com.example.ghosthunter.Environment.Environment;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.Rect;
import java.util.ArrayList;

public class GridMap {
	
	int lenX, lenY;
	int seed; //will be used for the eventual generation
	ArrayList<Character> chars;
	ArrayList<Environment> envis;
	
	
	public GridMap(int x, int y, int seed){
		this.lenX=x;
		this.lenY=y;
	}
	
	public void addCharacter(Character character){
		this.chars.add(character);
	}
	
	public void addEnvironment(Environment environment){
		this.envis.add(environment);
	}
	
	public Character[] getCharList(int minX, int maxX, int minY, int maxY){ //returns a list of all the Character objects that need to be drawn
		Rect rectum = new Rect(minX,maxY, maxX, maxX);
		ArrayList<Character> hitler = new ArrayList<Character>();
		for(Character sodomy: this.chars){
			if(sodomy.getRect().intersect(rectum))
				hitler.add(sodomy);
		}
		Character[] himmler = (Character[])hitler.toArray(); //might cause problems with the Object[]
		return himmler;
	}
	
	public Environment[] getEnviList(int minX, int maxX, int minY, int maxY){ //returns a list of all of the Environment objects that need to be drawn
		Rect rectum = new Rect(minX,maxY, maxX, maxX);
		ArrayList<Environment> hitler = new ArrayList<Environment>();
		for(Environment sodomy: this.envis){
			if(sodomy.getRect().intersect(rectum))
				hitler.add(sodomy);
		}
		Environment[] himmler = (Environment[])hitler.toArray();
		return himmler;
	}
}
