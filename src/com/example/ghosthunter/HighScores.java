package com.example.ghosthunter;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class HighScores extends Activity {
	


	String name;
	int score;
	View mainLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SharedPreferences.Editor editorNames = getSharedPreferences("Names", MODE_PRIVATE).edit();
		SharedPreferences Names = getSharedPreferences("Names", MODE_PRIVATE);
		setContentView(R.layout.activity_high_scores);
		mainLayout = (View)findViewById(R.id.mainLayout);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			if(name!=null)
			{			
		    name = extras.getString("Name");
		    score = extras.getInt("Score");
		    editorNames.putInt(name, score);
			}
		}
		
		
		HashMap<String, ?> map = (HashMap<String, ?>) Names.getAll();
		for(Map.Entry<String,?> entry : map.entrySet()) {
			  String key = entry.getKey();
			  Integer value = (Integer) entry.getValue();
			  TextView temp = new TextView(this);
			  TextView temp1 = new TextView(this);
			  temp.setText(key);
			  temp.setTextSize(30);
			  temp.setX(200);
			  temp.setY(300);
			  temp1.setText(Integer.toString(value));
			  temp1.setTextSize(30);
			  temp1.setX(500);
			  temp1.setY(300);
			   ((ViewGroup) mainLayout).addView(temp);
			   ((ViewGroup) mainLayout).addView(temp1);
			  
			}
		

		
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
