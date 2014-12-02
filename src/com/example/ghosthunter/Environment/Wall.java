package com.example.ghosthunter.Environment;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Bitmap;
import com.example.ghosthunter.R;
import com.example.ghosthunter.GridMap.GridMap;

//Requires a larger number of images
/*
0 - EW (1010)
1 - NS (0101)

2 - NE (1100)
3 - NW (0110)
4 - SW (0011)
5 - SE (1001)

6 - NES (1101)
7 - WNE (1110)
8 - SWN (0111)
9 - ESW (1011)

10 - all (1111)
11 - pillar (0000)

*/
public class Wall extends Environment{
	
	int direction; //also handles intersection stuff
	
	//WARNING: NEEDS THE WALL IMAGES
	
	public Wall(GridMap grid, int[] pos, Context c){
		super(false,false,grid); //not destructible and not walkable
		
		//WARNING: This needs to be scaled
		super.setLen(new int[]{1,1});
		
		super.setPos(pos);
		//direction is set later
		
		Bitmap b00=BitmapFactory.decodeResource(c.getResources(), R.drawable.wall00);
		Bitmap b01=BitmapFactory.decodeResource(c.getResources(), R.drawable.wall01);
		Bitmap b02=BitmapFactory.decodeResource(c.getResources(), R.drawable.wall02);
		Bitmap b03=BitmapFactory.decodeResource(c.getResources(), R.drawable.wall03);
		Bitmap b04=BitmapFactory.decodeResource(c.getResources(), R.drawable.wall04);
		Bitmap b05=BitmapFactory.decodeResource(c.getResources(), R.drawable.wall05);
		Bitmap b06=BitmapFactory.decodeResource(c.getResources(), R.drawable.wall06);
		Bitmap b07=BitmapFactory.decodeResource(c.getResources(), R.drawable.wall07);
		Bitmap b08=BitmapFactory.decodeResource(c.getResources(), R.drawable.wall08);
		Bitmap b09=BitmapFactory.decodeResource(c.getResources(), R.drawable.wall09);
		Bitmap b10=BitmapFactory.decodeResource(c.getResources(), R.drawable.wall10);
		Bitmap b11=BitmapFactory.decodeResource(c.getResources(), R.drawable.wall11);
		
		Bitmap[] images = new Bitmap[12];
		images[0] = b00;
		images[1] = b01;
		images[2] = b02;
		images[3] = b03;
		images[4] = b04;
		images[5] = b05;
		images[6] = b06;
		images[7] = b07;
		images[8] = b08;
		images[9] = b09;
		images[10] = b10;
		images[11] = b11;
		
		super.setImages(images);
	}
	
	public Bitmap draw() {
		return super.getImages()[direction];
	}
	
	//Checking for intersections handled elsewhere
	public void setDirection(int dir) {
		this.direction=dir;
	}
	
	public void update(Canvas c, int[] offset)
	{
		 //replace with direction calculation
		c.drawBitmap(this.draw(), (float)(super.getPos()[0]-offset[0]),(float)(super.getPos()[1]-offset[1]),new Paint(Paint.ANTI_ALIAS_FLAG));
	}
}
