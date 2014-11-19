package com.example.ghosthunter.GridMap;

//Bad practice in general, but should be fine here
import com.example.ghosthunter.Character.Character;
import com.example.ghosthunter.Environment.*;
import com.example.ghosthunter.Ghost.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.Rect;
import java.util.ArrayList;
import java.util.List;

//This is a HIGHLY EXPERIMENTAL class. Please do not design around this yet.

public class GridMap {
	
	int lenX, lenY;
	int seed; //will be used for the eventual generation
	Character[] chars;
	Environment[] envis;
	
	
	public GridMap(int x, int y, int seed){
		this.lenX=x;
		this.lenY=y;
		
	}
	
	public Character[] getCharList(int minX, int maxX, int minY, int maxY){
		Rect rectum = new Rect(minX,maxY, maxX, maxX);
		ArrayList<Character> hitler = new ArrayList<Character>();
		for(Character sodomy: this.chars){
			if(sodomy.getRect().intersect(rectum))
				hitler.add(sodomy);
		}
		Character[] himmler = (Character[])hitler.toArray(); //might cause problems with the Object[]
		return himmler;
	}
	
	public Environment[] getEnviList(int minX, int maxX, int minY, int maxY){
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
