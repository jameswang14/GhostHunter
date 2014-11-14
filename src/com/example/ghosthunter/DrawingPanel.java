package com.example.ghosthunter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class DrawingPanel extends SurfaceView implements SurfaceHolder.Callback {
	private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	PanelThread _thread;
	private SurfaceHolder sh;
	public DrawingPanel(Context context)
	{
		super(context);
		sh = getHolder();
		getHolder().addCallback(this);
		paint.setColor(Color.BLUE);
		paint.setStyle(Style.FILL);
	
	}
	
	public void onDraw(Canvas canvas)
	{
		Log.e("Test", "t");
		canvas.drawCircle(100, 200, 50, paint);
	}
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

    public void surfaceCreated(SurfaceHolder holder) {
        Canvas canvas = sh.lockCanvas();
        canvas.drawColor(Color.BLACK);
        Bitmap b=BitmapFactory.decodeResource(getResources(), R.drawable.ghosticon);

        canvas.drawBitmap(b, 0, 0, null);
        sh.unlockCanvasAndPost(canvas);                          //onDraw()
       }


       @Override
       public void surfaceDestroyed(SurfaceHolder holder) {
        try {
               _thread.setRunning(false);                //Tells thread to stop
        _thread.join();                           //Removes thread from mem.
    } catch (InterruptedException e) {}
       }

	
}
