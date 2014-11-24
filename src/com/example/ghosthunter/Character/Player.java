package com.example.ghosthunter.Character;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import com.example.ghosthunter.GridMap.GridMap;

public class Player extends Character{



	public Player(int hp, Bitmap[] images, int armor, GridMap grid,Context context) {
		super(hp, images, armor, grid, context);
		
	}

	@Override
	public void move(int direction) {
		
	}

	@Override
	public Bitmap draw() {
		return images[0];
	}

}
