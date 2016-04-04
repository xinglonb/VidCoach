package com.VidCoach.myapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class PlayBack extends ActionBarActivity{
	private int ID;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_playback);
        
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
		
		String[] titleList = {"Watch Model", "Practice", "Watch Practice"};
		setTitle(titleList[pref.getInt("model", 1) - 1]);
        
        Intent i = getIntent();
        ID = i.getIntExtra("ID", 1);
        
        showAlert();
	}
	
	private void showAlert() {
		AlertDialog.Builder builder = new AlertDialog.Builder(PlayBack.this);
		builder.setMessage(R.string.ReplayVideo);
		builder.setPositiveButton(R.string.YES, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Intent i = new Intent(PlayBack.this, ToGreeting.class);
				int counter = getIntent().getIntExtra("counter",0);
				i.putExtra("counter", counter);
				i.putExtra("ID", ID);
				
				dialog.dismiss();
				startActivity(i);
				
			}
		});
		builder.setNegativeButton(R.string.NO, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Intent i = new Intent(PlayBack.this, MainActivity.class);
				
				dialog.dismiss();
				startActivity(i);
			}
		});
        AlertDialog alert = builder.create();
        alert.setCancelable(false);
        alert.show();
	}	
	
	@Override
	public void onBackPressed(){
		showAlert();
		//Do Nothing.
	}
}
