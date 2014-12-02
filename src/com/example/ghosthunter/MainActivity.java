package com.example.ghosthunter;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	
	//THIS IS THE CHANGE
	
	MediaPlayer logoMusic;
	String name;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		logoMusic = MediaPlayer.create(MainActivity.this, R.raw.music);
		logoMusic.setLooping(true);
		logoMusic.start();
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    name = extras.getString("Name");
		    if(name != null)
		    Log.v("Test", name);
		    else
		    	Log.e("Test", "f");
		}
		final MediaPlayer buttonSound = MediaPlayer.create(MainActivity.this, R.raw.button_click);
		
		//Setting up Menu Button References
		Button gameStart = (Button) findViewById(R.id.startGameButton);
		Button settings = (Button) findViewById(R.id.settingsButton);
		Button highscores = (Button) findViewById(R.id.highscoresButton);
		
		gameStart.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				buttonSound.start();
				Intent gameIntent = new Intent(MainActivity.this, GamePage.class);
				if(name !=null)
				{
					gameIntent.putExtra("Name", name);
				}
				startActivity(gameIntent);
			}
			
		});
		
		
		settings.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				buttonSound.start();
				Intent settingsIntent = new Intent(MainActivity.this, Settings.class);
				startActivity(settingsIntent);
			}
			
		});
		
		highscores.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				buttonSound.start();
				Intent highscoresIntent = new Intent(MainActivity.this, HighScores.class);
				startActivity(highscoresIntent);
			}
		});
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		logoMusic.release();
	}
	
	
}
