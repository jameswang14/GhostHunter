package com.example.ghosthunter;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class Settings extends Activity implements OnCheckedChangeListener{
	
	MediaPlayer logoMusic;
	TextView textOut;
	EditText textIn;
	RadioGroup difficultyG, playersG;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		final MediaPlayer buttonSound = MediaPlayer.create(Settings.this, R.raw.button_click);
		
		
		textIn = (EditText) findViewById(R.id.editText1);
		textOut = (TextView) findViewById(R.id.tvChange);
		RadioGroup difficultyG = (RadioGroup) findViewById(R.id.rgDifficulty);
		difficultyG.setOnCheckedChangeListener(this);
		RadioGroup playersG = (RadioGroup) findViewById(R.id.rgPlayers);
		playersG.setOnCheckedChangeListener(this);
		
		Button gen = (Button) findViewById(R.id.bGenerate);
		Button back = (Button) findViewById(R.id.bBack);
		
		gen.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				textOut.setText(textIn.getText());
			}
		});		
		
		back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				buttonSound.start();
				Intent settingsIntent = new Intent(Settings.this, MainActivity.class);
				startActivity(settingsIntent);
			}
			
		});
		logoMusic = MediaPlayer.create(Settings.this, R.raw.menumusic);
		logoMusic.setLooping(true);
		logoMusic.start();
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		String difficultyRank = "";
		String playersRank = "";
		//switch (checkedId){
		//case R.id.radioButton1:
		//	difficultyRank = "Playful";
		//	textOut.setText(difficultyRank + " " + playersRank + " " + textOut.getText());
		//	break;
		//}
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		logoMusic.release();
	}
	


}
