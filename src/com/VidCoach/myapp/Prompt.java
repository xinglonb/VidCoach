package com.VidCoach.myapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class Prompt extends ActionBarActivity{
	Editor editor;
	SharedPreferences pref;
	private int ID = 1;
	private String correct = "";
	private String prompt = "";
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_prompt);
        
        SQLiteDatabase db = openOrCreateDatabase("InterviewerTable", MODE_PRIVATE,null);
        
        pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
		editor = pref.edit();
		
		String[] titleList = {"Watch Model", "Practice", "Watch Practice"};
		setTitle(titleList[pref.getInt("model", 1) - 1]);
		
		Intent i = getIntent();
		ID = i.getIntExtra("ID", 1);
		
		Cursor c = db.rawQuery("SELECT i.PrePrompt FROM PromptTable AS i WHERE i.ID = " + Integer.toString(ID), null);
		c.moveToFirst();
		prompt = c.getString(c.getColumnIndex("PrePrompt"));
		c = db.rawQuery("SELECT i.CorrectAnswer FROM PromptTable AS i WHERE i.ID = " + Integer.toString(ID), null);
		c.moveToFirst();
		correct  = c.getString(c.getColumnIndex("CorrectAnswer"));
		db.close();
		int answer = i.getIntExtra("answer", 3);
		if (pref.getBoolean("pre", true) && answer == 3)
			showAlert();
		else if (pref.getBoolean("post", true) && answer != 3)
			showAlert();
		else if (!pref.getBoolean("pre", true) && answer == 3){
			Intent in = new Intent(Prompt.this, ToGreeting.class);
			if (pref.getInt("model", 1) == 2)
				in = new Intent(Prompt.this, Recording.class);
			int counter = getIntent().getIntExtra("counter",0);
			in.putExtra("counter", counter);
			in.putExtra("ID", ID);
			startActivity(in);
		}
		
		showAlert();
	}
	
	private void showAlert() {
		AlertDialog.Builder builder = new AlertDialog.Builder(Prompt.this);
		TruePrompt.Builder customBuilder = new
				TruePrompt.Builder(Prompt.this);
        int answer = getIntent().getIntExtra("answer", 3);
		if (answer != 0 && answer != 1 && answer !=2){
			builder.setMessage(prompt); //R.string.shake_hand_notice
		    builder.setNeutralButton(R.string.OK, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Intent i = new Intent(Prompt.this, ToGreeting.class);
					if (pref.getInt("model", 1) == 2)
						i = new Intent(Prompt.this, Recording.class);
					int counter = getIntent().getIntExtra("counter",0);
					i.putExtra("counter", counter);
					i.putExtra("ID", ID);
					dialog.dismiss();
					startActivity(i);					
				
				}
			});
		}
		else{
//			builder.setNeutralButton(R.string.OK, new DialogInterface.OnClickListener() {
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//					// TODO Auto-generated method stub
//					Intent i = new Intent(Prompt.this, PlayBack.class);
//					
//					
//					// implementation for watch all
//					if (pref.getBoolean("watchAll", false) == true){
//						if (ID < pref.getInt("end", -1)){ //if have not played all yet
//							i = new Intent(Prompt.this, ToGreeting.class);
//							ID += 1; // increment ID in order to play next subcategory
//						}
//						else{
//							ID = pref.getInt("start", 1); // if have played all set the ID to the first video
//						}
//					}
//					// implementation for watch all
//					
//					int counter = getIntent().getIntExtra("counter",0);
//					i.putExtra("counter", counter);
//					i.putExtra("ID", ID);
//					startActivity(i);
//				}
//			});
			customBuilder.setNeutralButton(R.string.OK, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Intent i = new Intent(Prompt.this, PlayBack.class);
					
					
					// implementation for watch all
					if (pref.getBoolean("watchAll", false) == true){
						if (ID < pref.getInt("end", -1)){ //if have not played all yet
							i = new Intent(Prompt.this, ToGreeting.class);
							ID += 1; // increment ID in order to play next subcategory
						}
						else{
							ID = pref.getInt("start", 1); // if have played all set the ID to the first video
						}
					}
					// implementation for watch all
					
					int counter = getIntent().getIntExtra("counter",0);
					i.putExtra("counter", counter);
					i.putExtra("ID", ID);
					
					dialog.dismiss();
					startActivity(i);
				}
			});
		}
		if (answer != 0 && answer != 1 && answer !=2){
			builder.setCancelable(false);
			AlertDialog alert = builder.create();
			alert.show();
		}
		else{
			if (answer == 0){
				customBuilder.setTitle("Good Job!")
							 .setMessage("Correct Answer!");}
			else if((answer == 1) || (answer == 2)){
				customBuilder.setTitle("Nice Try!")
				             .setMessage("The Correct Answer is " + correct)
				             .setColor("#ff3030");}
			Dialog alert = customBuilder.create();
			
			alert.show();
		}
        
//		if (answer == 0){
//        	alert.setTitle(Html.fromHtml("<font color='#227209'>Good Job!</font>"));
//			alert.setMessage(Html.fromHtml("<font color='#227209'>Correct Answer! </font>"));}
//		else if((answer == 1) || (answer == 2)){
//			alert.setTitle(Html.fromHtml("<font color='#FF0000'>Nice Try!</font>"));
//			alert.setMessage(Html.fromHtml("<font color='#FF0000'>The Correct Answer is " + correct +" </font>"));}
//        
        
	}	
	
	@Override
	public void onBackPressed(){
		showAlert();
		//Do Nothing.
	}
	/*public static void colorAlertDialogTitle(AlertDialog builder, int color, int answer) {
	    //int dividerId = dialog.getContext().getResources().getIdentifier("android:id/titleDivider", null, null);
	    if (answer == 0) {
	        View divider = builder.findViewById(R.lat);
	        divider.setBackgroundColor(color);
	    }

	    int textViewId = builder.getContext().getResources().getIdentifier("android:id/alertTitle", null, null);
	    if ((answer == 1) || (answer == 2)) {
	        TextView tv = (TextView) builder.findViewById(textViewId);
	        tv.setTextColor(color);
	    }

	    int iconId = builder.getContext().getResources().getIdentifier("android:id/icon", null, null);
	    if (iconId != 0) {
	        ImageView icon = (ImageView) dialog.findViewById(iconId);
	        icon.setColorFilter(color);
	    }
	}*/
}
