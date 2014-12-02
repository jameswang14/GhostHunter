package com.example.ghosthunter;

import java.util.Map;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class HighScores extends Activity {
	
	SharedPreferences.Editor editorNames = getSharedPreferences("Names", MODE_PRIVATE).edit();
	SharedPreferences Names = getSharedPreferences("Names", MODE_PRIVATE);

	String name;
	int score;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_high_scores);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			if(name!=null)
			{			
		    name = extras.getString("Name");
		    score = extras.getInt("Score");
		    editorNames.putInt(name, score);
			}
		}
		
		
		Map<String, ?> map = Names.getAll();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.high_scores, menu);
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
	public void onBackPressed()
	{
		
		super.onBackPressed();
	}
}
