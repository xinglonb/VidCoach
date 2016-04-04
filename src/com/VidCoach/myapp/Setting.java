package com.VidCoach.myapp;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.CompoundButton;
import android.widget.Switch;

public class Setting extends ActionBarActivity{
	Editor editor;
	SharedPreferences pref; 
	Boolean pre_on_off = true;
	Boolean post_on_off = true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
		editor = pref.edit();
		pre_on_off = pref.getBoolean("pre", true);
		
		Switch Pre = (Switch) findViewById(R.id.switch1);
		Pre.setChecked(pre_on_off);
		Pre.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        if (isChecked) {
		        	editor.putBoolean("pre", true);
		        	editor.commit();
		        } else {
		        	editor.putBoolean("pre", false);
		        	editor.commit();
		        }
		    }
		});
		
		post_on_off = pref.getBoolean("post", true);
		Switch Post = (Switch) findViewById(R.id.switch2);
		Post.setChecked(post_on_off);
		Post.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        if (isChecked) {
		    		editor.putBoolean("post", true);
		    		editor.commit();
		    		
		        } else {
		    		editor.putBoolean("post", false);
		    		editor.commit();
		        }
		    }
		});
		
	}

}
