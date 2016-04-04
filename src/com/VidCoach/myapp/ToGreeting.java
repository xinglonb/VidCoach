package com.VidCoach.myapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.VideoView;

public class ToGreeting extends ActionBarActivity{
	//private SQLiteDatabase db = openOrCreateDatabase("InterviewerTable", MODE_PRIVATE,null);
	private int ID;
	private String path2 = "android.resource://com.VidCoach.myapp/raw/v2";
	private String path1 = "android.resource://com.VidCoach.myapp/raw/v1";
	private int counter = 0;
	Editor editor;
	SharedPreferences pref;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_greeting);
        
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        SQLiteDatabase db = openOrCreateDatabase("InterviewerTable", MODE_PRIVATE,null);
        pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
		editor = pref.edit();
		Intent i = getIntent();
		
		String[] titleList = {"Watch Model", "Practice", "Watch Practice"};
		setTitle(titleList[pref.getInt("model", 1) - 1]);
		
		ID = i.getIntExtra("ID", 1);
		Cursor c = db.rawQuery("SELECT i.PreUrl FROM VideoTable AS i WHERE i.ID = " + Integer.toString(ID), null);
		c.moveToFirst();
		String path1 = c.getString(c.getColumnIndex("PreUrl"));
		c = db.rawQuery("SELECT i.PostUrl FROM VideoTable AS i WHERE i.ID = " + Integer.toString(ID), null);
		c.moveToFirst();
		String path2 = c.getString(c.getColumnIndex("PostUrl"));
		
        
        VideoView v = (VideoView) findViewById(R.id.videoView1);
        String path = path1;
        
        
        
        counter = i.getIntExtra("counter", 0);
        int practice = i.getIntExtra("practice", 0);
        if (pref.getInt("model", 1) == 3)
        	path2 = "file:///storage/emulated/0/Android/data/com.VidCoach.myapp/files/Pictures/MyVideos/video_00"+Integer.toString(ID)+".mp4";
        
        if (counter%2 == 0)
        	path = path1;
        else if (counter%2 == 1)
        	path = path2;
        ++counter;
        
        db.close();
        
        v.setVideoURI(Uri.parse(path));
        //v.setMediaController(new MediaController(this));
        v.start();
        v.requestFocus();
        v.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer mp) {
				// TODO Auto-generated method stub
				if (counter%2 == 1){
					Intent i = new Intent(ToGreeting.this, Prompt.class);
					i.putExtra("ID", ID);
					i.putExtra("counter", counter);
					startActivity(i);
					}
				else if (counter%2 == 0){
					if (!pref.getBoolean("post", true)){
						Intent i = new Intent(ToGreeting.this, PlayBack.class);
						// implementation for watch all
						if (pref.getBoolean("watchAll", false) == true){
							if (ID < pref.getInt("end", -1)){ //if have not played all yet
								i = new Intent(ToGreeting.this, ToGreeting.class);
								ID += 1; // increment ID in order to play next subcategory
							}
							else{
								ID = pref.getInt("start", 1); // if have played all set the ID to the first video
							}
						}
						// implementation for watch all
						i.putExtra("counter", counter);
						i.putExtra("ID", ID);
						startActivity(i);
						}
					else{
						Intent i = new Intent(ToGreeting.this, MultipleChoice.class);
						i.putExtra("counter", counter);
						i.putExtra("ID", ID);
						startActivity(i);
						}
					}
				}
				//showAlert();
		});
      
        
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
	
	
	private void showAlert() {
		AlertDialog.Builder builder = new AlertDialog.Builder(ToGreeting.this);
        builder.setMessage(R.string.shake_hand_notice)
        	   .setNeutralButton(R.string.OK, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					finish();
				}
			});
        // Create the AlertDialog object and return it
        
        AlertDialog alert = builder.create();
        alert.show();
	}
	
	@Override
	public void onBackPressed(){
		//Do Nothing.
	}
}
