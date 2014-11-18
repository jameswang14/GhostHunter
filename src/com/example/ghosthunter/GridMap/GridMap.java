package com.example.ghosthunter.GridMap;

//Bad practice in general, but should be fine here
import com.example.ghosthunter.Character.*;
import com.example.ghosthunter.Environment.*;
import com.example.ghosthunter.Ghost.*;
import android.graphics.drawable.BitmapDrawable;

//This is a HIGHLY EXPERIMENTAL class. Please do not design around this yet.

public class GridMap {
	
	int lenX, lenY;
	int seed; //will be used for the eventual generation
	
	public GridMap(int x, int y, int seed){
		this.lenX=x;
		this.lenY=y;
	}
}
