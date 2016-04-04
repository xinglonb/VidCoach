package com.VidCoach.myapp;

import java.io.File;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;

public class Recording extends ActionBarActivity {
	public static final int MEDIA_TYPE_VIDEO = 2;
	private Uri fileUri;
	private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 200;
	private int ID = 1;
	private boolean taken = false;
	
	Editor editor;
	SharedPreferences pref; 
//	/** Create a file Uri for saving an image or video */
//	private static Uri getOutputMediaFileUri(int type){
//	      return Uri.fromFile(getOutputMediaFile(type));
//	}
//
//	/** Create a File for saving an image or video */
//	private static File getOutputMediaFile(int type){
//	    // To be safe, you should check that the SDCard is mounted
//	    // using Environment.getExternalStorageState() before doing this.
//
//	    File mediaStorageDir = new File("android.resource://com.xinglonb.myapp/raw");
//	    // This location works best if you want the created images to be shared
//	    // between applications and persist after your app has been uninstalled.
//
//	    // Create the storage directory if it does not exist
//	    if (! mediaStorageDir.exists()){
//	        if (! mediaStorageDir.mkdirs()){
//	            Log.d("MyCameraApp", "failed to create directory");
//	            return null;
//	        }
//	    }
//
//	    // Create a media file name
//	    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date(0));
//	    File mediaFile;
//	    if(type == MEDIA_TYPE_VIDEO) {
//	        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
//	        "VID_"+ timeStamp + ".mp4");
//	    } else {
//	        return null;
//	    }
//
//	    return mediaFile;
//	}
//	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_to_recording);
		
		pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
		
		String[] titleList = {"Watch Model", "Practice", "Watch Practice"};
		setTitle(titleList[pref.getInt("model", 1) - 1]);
		
		Intent i = getIntent();
		ID = i.getIntExtra("ID", 1);
		
		Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
		
		File videosFolder = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "MyVideos");
	       videosFolder.mkdirs();
		File video = new File(videosFolder, "video_00" + Integer.toString(ID) +".mp4");
		fileUri = Uri.fromFile(video);
//		fileUri = getOutputMediaFileUri(MEDIA_TYPE_VIDEO);  // create a file to save the video
	    intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);  // set the image file name
	    intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
	     // set the video image quality to high

	    // start the Video Capture Intent
	    startActivityForResult(intent, CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE);
	    
	    //Toast.makeText(this, "After that 1", Toast.LENGTH_LONG).show();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE) {
	        if (resultCode == RESULT_OK) {
	            // Video captured and saved to fileUri specified in the Intent
	        	
	        	editor = pref.edit();
	            editor.putBoolean("isRecorded" + Integer.toString(ID), true);
	            editor.commit();
//	            Toast.makeText(this, "Video saved to:\n" +
//	                     data.getData(), Toast.LENGTH_LONG).show();
	            showAlert();
	            
	            
	        } else if (resultCode == RESULT_CANCELED) {
	            // User cancelled the video capture
	        	editor = pref.edit();
	            editor.putBoolean("isRecorded" + Integer.toString(ID), false);
	            editor.commit();
	        	
	        	startActivity(new Intent(Recording.this, MainActivity.class));
	        	
	        		
	        		
	        } else {
	            // Video capture failed, advise user
	        }
	    }
	}
	
	public void onBackPressed(){
		Intent i = new Intent(Recording.this, MainActivity.class);
		//i.putExtra("practice", practice);
		startActivity(i);
	}
	
	private void showAlert() {
		AlertDialog.Builder builder = new AlertDialog.Builder(Recording.this);
    
	    builder.setPositiveButton("Retake", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				Intent i = new Intent(Recording.this, Recording.class);
        		int counter = getIntent().getIntExtra("counter",0);
        		i.putExtra("counter", counter);
				i.putExtra("ID", ID);
				startActivity(i);
			}
		});
	    builder.setNegativeButton("Use Video", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				Intent i = new Intent(Recording.this, MainActivity.class);
	            if (pref.getBoolean("watchAll", false) == true && ID < pref.getInt("end", -1)){
					//if have not played all yet
					i = new Intent(Recording.this, ToGreeting.class);
					 // increment ID in order to play next subcategory
				}
	            int counter = getIntent().getIntExtra("counter",0);
				i.putExtra("counter", counter+1);
				i.putExtra("ID", ID+1);
	            
				taken = true;
				
	            startActivity(i);
			}
		});
	    
	    builder.setCancelable(false);
		AlertDialog alert = builder.create();
		alert.show();
	}		
	
}


