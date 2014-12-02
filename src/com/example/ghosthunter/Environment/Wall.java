package com.example.ghosthunter.Environment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import com.example.ghosthunter.R;
import android.graphics.Matrix;

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
	static Bitmap[] images;
	
	public Wall(int[] pos, Context context){
		super(false,false,context); //not destructible and not walkable
		
		//WARNING: This needs to be scaled
		super.setLen(new int[]{1,1});
		
		super.setPos(pos);
		//direction set later


	}
	
	//Checking for intersections handled elsewhere
	public void setDirection(int dir) {
		this.direction=dir;
	}
	
	public Bitmap getImages() {
		return Wall.images[this.direction];
	}
	
	public static void processImages(Context context) {
		Bitmap[] images = new Bitmap[12];
		
		images[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.wall00);
		images[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.wall01);
		images[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.wall02);
		images[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.wall03);
		images[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.wall04);
		images[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.wall05);
		images[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.wall06);
		images[7] = BitmapFactory.decodeResource(context.getResources(), R.drawable.wall07);
		images[8] = BitmapFactory.decodeResource(context.getResources(), R.drawable.wall08);
		images[9] = BitmapFactory.decodeResource(context.getResources(), R.drawable.wall09);
		images[10] = BitmapFactory.decodeResource(context.getResources(), R.drawable.wall10);
		images[11] = BitmapFactory.decodeResource(context.getResources(), R.drawable.wall11);
		
		Wall.images = images;
	}
	
	public void update(Canvas c, int[] offset) {
		c.drawBitmap(this.getImages(), super.getPos()[0]-offset[0],super.getPos()[1]-offset[1], new Paint(Paint.ANTI_ALIAS_FLAG));
	}
	
//	public void update(Canvas c, int[] offset)
//	{
//		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//		paint.setColor(Color.WHITE);
//		
//		 //replace with direction calculation
//		if(this.direction[0]==1)
//			c.drawRect(this.getPos()[0]+50-offset[0],this.getPos()[1]+40-offset[1],this.getPos()[0]+100-offset[0],this.getPos()[1]+60-offset[1],paint);
//		if(this.direction[1]==1)
//			c.drawRect(this.getPos()[0]+40-offset[0],this.getPos()[1]-offset[1],this.getPos()[0]+60-offset[0],this.getPos()[1]+50-offset[1],paint);
//		if(this.direction[2]==1)
//			c.drawRect(this.getPos()[0]-offset[0],this.getPos()[1]+40-offset[1],this.getPos()[0]+50-offset[0],this.getPos()[1]+60-offset[1],paint);
//		if(this.direction[3]==1)
//			c.drawRect(this.getPos()[0]+40-offset[0],this.getPos()[1]+50-offset[1],this.getPos()[0]+60-offset[0],this.getPos()[1]+100-offset[1],paint);
//		if(this.direction==new int[]{0,0,0,0})
//			c.drawRect(this.getPos()[0]+40-offset[0],this.getPos()[1]+40-offset[1],this.getPos()[0]+60-offset[0],this.getPos()[1]+60-offset[1],paint);
//		
//
//	}
}
