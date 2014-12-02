package com.example.ghosthunter;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class DeathScreen extends Activity {
	
//	MediaPlayer logoMusic;

//	final MediaPlayer buttonSound = MediaPlayer.create(DeathScreen.this, R.raw.button_click);

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_death_screen);
		Button gameRestart = (Button) findViewById(R.id.backToGameButton);
		Button menuRestart = (Button) findViewById(R.id.backToMenuButton);
//
//		logoMusic = MediaPlayer.create(DeathScreen.this, R.raw.music);
//		logoMusic.setLooping(true);
//		logoMusic.start();
//		
		gameRestart.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
			//	buttonSound.start();
				Intent gameIntent = new Intent(DeathScreen.this, GamePage.class);
				startActivity(gameIntent);
			}
			
		});
		
		
		menuRestart.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//buttonSound.start();
				Intent menuIntent = new Intent(DeathScreen.this, MainActivity.class);
				startActivity(menuIntent);
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.death_screen, menu);
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
}
