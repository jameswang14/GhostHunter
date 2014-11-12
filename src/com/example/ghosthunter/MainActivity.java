package com.example.ghosthunter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Setting up Menu Button References
		Button gameStart = (Button) findViewById(R.id.startGameButton);
		Button settings = (Button) findViewById(R.id.settingsButton);
		
		gameStart.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent gameIntent = new Intent(MainActivity.this, GamePage.class);
				startActivity(gameIntent);
			}
			
		});
		
		
		settings.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent settingsIntent = new Intent(MainActivity.this, Settings.class);
				startActivity(settingsIntent);
			}
			
		});
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
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
}
