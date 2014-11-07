package com.example.ghosthunter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Launcher extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
		Thread logoTimer = new Thread(){
			public void run() {
				try {
					sleep(5000);
					Intent menuIntent = new Intent(Launcher.this, MainActivity.class);
					startActivity(menuIntent);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				finally {
					finish();
				}
			}
		};
		logoTimer.start();
	}

}
