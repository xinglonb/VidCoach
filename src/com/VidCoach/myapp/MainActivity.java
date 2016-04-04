package com.VidCoach.myapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
public class MainActivity extends ActionBarActivity {
	int practice = 1;
	private int videoCounter;
	private LinearLayout layout;
	private LinearLayout layout1;
	private Button topbutton;
	private Button mainbutton;
	private int[] iArray = {1, 8, 22, 32, 41, 56, 65};
	Editor editor;
	SharedPreferences pref;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
		editor = pref.edit();
		Intent in = getIntent();
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		//videoCounter = in.getIntExtra("videoCounter", 0);
		videoCounter = pref.getInt("videoCounter", 1);
		
		String[] titleList = {"Watch Model", "Practice", "Watch Practice"};
		setTitle(titleList[pref.getInt("model", 1) - 1]);
		
		SQLiteDatabase db = openOrCreateDatabase("InterviewerTable", MODE_PRIVATE,null);
		
		String VideoTypes[][] = new String[6][64];
		for(int i=0; i<6; i++){
			for(int j=iArray[i]; j<iArray[i+1]; j++){
				Cursor c = db.rawQuery("SELECT i.VideoType FROM InterviewerTable As i WHERE i.ID ="+ Integer.toString(j), null);
				c.moveToFirst();
				String id = c.getString(c.getColumnIndex("VideoType"));
				VideoTypes[i][j-1] = id; 
				}
			}
		
		
		
//		Button b = (Button) findViewById(R.id.greeting1_answer_amanda);
//		b.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				startActivity(new Intent(MainActivity.this, ToGreeting.class));
//			}
//		});
//		
//		Button b2 = (Button) findViewById(R.id.button1);
//		
//		b2.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				startActivity(new Intent(MainActivity.this, Resume.class));
//			}
//		});
//		
//		Button b3 = (Button) findViewById(R.id.button2);
//		
//		
//		b3.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				startActivity(new Intent(MainActivity.this, Recording.class));
//			}
//		}); 
//		
//		Button b4 = (Button) findViewById(R.id.button3);
//		
//		b4.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Intent i = new Intent(MainActivity.this, ToGreeting.class);
//				i.putExtra("practice", practice);
//				startActivity(i);
//				}
//			
//		}); 
		db.close();
	
		// implementation for watch all
		layout1 = (LinearLayout) findViewById(R.id.layout1);
		LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0.5f);
		layout1.setOrientation(LinearLayout.VERTICAL);
		
		if (pref.getInt("model", 1) == 1 || pref.getInt("model", 1) == 2 || pref.getInt("model", 1) == 3){ //if it is watch model create watch all button
			topbutton = new Button(this);
			if (pref.getInt("model", 1) == 1 || pref.getInt("model", 1) == 3)
				topbutton.setText("Watch All");
			else if (pref.getInt("model", 1) == 2)
				topbutton.setText("Practice All");
			
			//topbutton.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 60));
			topbutton.setId(iArray[videoCounter]);
			topbutton.setBackgroundColor(Color.LTGRAY);
			layout1.addView(topbutton);
			
			editor.putInt("start", iArray[videoCounter]); // the ID of the first played video
			editor.putInt("end", iArray[videoCounter+1] - 1); // ID of the last played video
			editor.commit(); // save
			
			topbutton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i = new Intent(MainActivity.this, ToGreeting.class);
					i.putExtra("ID", v.getId());
					i.putExtra("videoCounter", videoCounter);
					
					editor.putBoolean("watchAll", true); // save a counter in sharedpreference for watch all
					editor.commit(); // save
					
					boolean practiced = practiceAll(iArray[videoCounter], iArray[videoCounter+1]);
					
					if (pref.getInt("model", 1) == 3){
						i.putExtra("practice", practice);
						if (!practiced)
							i = new Intent(MainActivity.this, Warning.class);
							i.putExtra("practiceAll", false);
					}
					
					startActivity(i); // start play
					}
				
			});
			
		}
		// Above is for watch all
		
		layout = (LinearLayout) findViewById(R.id.layout2);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0.5f);
		layout.setOrientation(LinearLayout.VERTICAL);
		
		
		for(int j=iArray[videoCounter]; j<(iArray[videoCounter+1]); j++){
			String Videos = VideoTypes[videoCounter][j-1];
			mainbutton = new Button(this);
			mainbutton.setText(Videos);
			mainbutton.setId(j);
			mainbutton.setBackgroundColor(Color.WHITE);
			
			View divider = new View(this);
			divider.setBackgroundColor(Color.parseColor("#FFFFFF"));
			divider.setId(j+1000);
			divider.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1));
			
			GradientDrawable drawable = (GradientDrawable) layout.getBackground();
			
			
			if (videoCounter == 0){
				mainbutton.setBackgroundColor(Color.parseColor("#B266B2"));
				
				drawable.setColor(Color.parseColor("#B266B2"));}
			if (videoCounter == 1 || videoCounter == 3){
				mainbutton.setBackgroundColor(Color.parseColor("#00FFFF"));
				drawable.setColor(Color.parseColor("#00FFFF"));}
			if (videoCounter == 2){
				mainbutton.setBackgroundColor(Color.parseColor("#FFA500"));
				drawable.setColor(Color.parseColor("#FFA500"));}
			if (videoCounter == 4){
				mainbutton.setBackgroundColor(Color.parseColor("#FF4C4C"));
				drawable.setColor(Color.parseColor("#FF4C4C"));}
			if (videoCounter == 5){
				mainbutton.setBackgroundColor(Color.parseColor("#0066FF"));
				drawable.setColor(Color.parseColor("#0066FF"));}
			
			
			
			layout.addView(mainbutton);
			if (j < iArray[videoCounter+1] - 1)
				layout.addView(divider);
			
			
			mainbutton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i = new Intent(MainActivity.this, ToGreeting.class);
					if (pref.getInt("model", 1) == 3){
						i.putExtra("practice", practice);
						if (pref.getBoolean("isRecorded" + Integer.toString(v.getId()), false) == false)
							i = new Intent(MainActivity.this, Warning.class);
					}
					i.putExtra("ID", v.getId());
					i.putExtra("videoCounter", videoCounter);
					
					editor.putBoolean("watchAll", false);
					editor.commit();
					
					startActivity(i);
					}
				
			}); 
		}
			
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
			Intent i = new Intent(MainActivity.this, Setting.class);
			startActivity(i);
			return true;
		}
		else if (id == R.id.action_home) {
			startActivity(new Intent(MainActivity.this, Vidcoach.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void onBackPressed(){
		Intent i = new Intent(MainActivity.this, Vidcoach.class);
		//i.putExtra("practice", practice);
		startActivity(i);
	}
	
	public boolean practiceAll(int id, int end){		
		if (id == end)
			return true;
		boolean practiced = pref.getBoolean("isRecorded" + Integer.toString(id++), false);
		return practiced && practiceAll(id, end);
	}
}
