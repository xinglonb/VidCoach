package com.VidCoach.myapp;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class Models extends ActionBarActivity{
	private Editor editor;
	private SharedPreferences pref;
	private int videoCounter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.models);
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
		editor = pref.edit();
		
		SQLiteDatabase db = openOrCreateDatabase("InterviewerTable", MODE_PRIVATE,null);
		Context context = getApplicationContext();
		Cursor cr = db.rawQuery("SELECT i.PreUrl FROM VideoTable AS i WHERE i.ID = 1", null);
		cr.moveToFirst();
		String id = cr.getString(cr.getColumnIndex("PreUrl"));
		
		
		
		Intent i = getIntent();
        
		videoCounter = pref.getInt("videoCounter", 1);
		String[] titleList = {"General", "Retail with Experience", "Retail without Experience", "Food Service", "Healthcare", "Hospitality"};
		setTitle(titleList[videoCounter]);
		
		db.close();
		
		ImageButton b = (ImageButton) findViewById(R.id.watchall);
		
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Models.this, MainActivity.class);
				//i.putExtra("practice", practice);
				i.putExtra("videoCounter", videoCounter);
				editor.putInt("model", 1);
	        	editor.commit();
				startActivity(i);
				}
			
		});
		
		ImageButton c = (ImageButton) findViewById(R.id.practice);
		
		c.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Models.this, MainActivity.class);
				//i.putExtra("practice", practice);
				i.putExtra("videoCounter", videoCounter);
				editor.putInt("model", 2);
	        	editor.commit();
				startActivity(i);
				}
			
		});
		ImageButton d = (ImageButton) findViewById(R.id.watchpractice);
		
		d.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Models.this, MainActivity.class);
				i.putExtra("videoCounter", videoCounter);
				editor.putInt("model", 3);
	        	editor.commit();
				//i.putExtra("practice", practice);
				startActivity(i);
				}
			
		});
		//db.close();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent i = new Intent(Models.this, Setting.class);
			startActivity(i);
			return true;
		}
		else if (id == R.id.action_home) {
			startActivity(new Intent(Models.this, Vidcoach.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void onBackPressed(){
		Intent i = new Intent(Models.this, Vidcoach.class);
		//i.putExtra("practice", practice);
		startActivity(i);
	}
}
