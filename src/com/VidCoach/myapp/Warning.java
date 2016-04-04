package com.VidCoach.myapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class Warning  extends ActionBarActivity{
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_warning);
        
        showAlert();
	}
	
	private void showAlert(){
		AlertDialog.Builder builder = new AlertDialog.Builder(Warning.this);
		
		Intent intent = getIntent();
		if (!intent.getBooleanExtra("practiceAll", true))
			builder.setMessage("Please practice all at first");
		else
			builder.setMessage("You have not practiced this yet"); //R.string.shake_hand_notice
	    builder.setNeutralButton(R.string.OK, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Warning.this, MainActivity.class);
				startActivity(i);
			}
		});
	    
	    AlertDialog alert = builder.create();
	    alert.setCancelable(false);
	    alert.show();
	}

}