package com.VidCoach.myapp;
import java.util.Random;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class MultipleChoice extends ActionBarActivity{
	private int ID = 1;
	private String question = "";
	String correct = "";
	String wrong1 = "";
	String wrong2 = "";
	Context context;
	String[] shake_hand_answers = {correct, wrong1, wrong2};//{"2 seconds", "30 minutes","3 minutes"};
	String[] shake_hand_answers_shuffled = {correct, wrong1, wrong2};//{"2 seconds", "30 minutes","3 minutes"};
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multiple_choice_prompt);
        context = getApplicationContext();
        SQLiteDatabase db = openOrCreateDatabase("InterviewerTable", MODE_PRIVATE,null);
        
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
		
		String[] titleList = {"Watch Model", "Practice", "Watch Practice"};
		setTitle(titleList[pref.getInt("model", 1) - 1]);
        
        Intent i = getIntent();
		ID = i.getIntExtra("ID", 1);
		
		Cursor c = db.rawQuery("SELECT i.PostPrompt FROM PromptTable AS i WHERE i.ID = " + Integer.toString(ID), null);
		c.moveToFirst();
		question = c.getString(c.getColumnIndex("PostPrompt"));
		
		c = db.rawQuery("SELECT i.CorrectAnswer FROM PromptTable AS i WHERE i.ID = " + Integer.toString(ID), null);
		c.moveToFirst();
		correct  = c.getString(c.getColumnIndex("CorrectAnswer"));
		
		c = db.rawQuery("SELECT i.WrongAnswerFirst FROM PromptTable AS i WHERE i.ID = " + Integer.toString(ID), null);
		c.moveToFirst();
		wrong1  = c.getString(c.getColumnIndex("WrongAnswerFirst"));
		
		c = db.rawQuery("SELECT i.WrongAnswerSecond FROM PromptTable AS i WHERE i.ID = " + Integer.toString(ID), null);
		c.moveToFirst();
		wrong2  = c.getString(c.getColumnIndex("WrongAnswerSecond"));
		
		db.close();
		shake_hand_answers[0] = correct;//{correct, wrong1, wrong2};//{"2 seconds", "30 minutes","3 minutes"};
		shake_hand_answers_shuffled[0] = correct;// {correct, wrong1, wrong2};//{"2 seconds", "30 minutes","3 minutes"};
		shake_hand_answers[1] = wrong1;//{correct, wrong1, wrong2};//{"2 seconds", "30 minutes","3 minutes"};
		shake_hand_answers_shuffled[1] = wrong1;//{correct, wrong1, wrong2};//{"2 seconds", "30 minutes","3 minutes"};
		shake_hand_answers[2] = wrong2;//{correct, wrong1, wrong2};//{"2 seconds", "30 minutes","3 minutes"};
		shake_hand_answers_shuffled[2] = wrong2;//{correct, wrong1, wrong2};//{"2 seconds", "30 minutes","3 minutes"};
		showAlert();
		
	}
	
	//List<Integer> shuffled_indexes_list = new ArrayList<Integer>();
	int[] shuffled_indexes_list ={0,0,0};
	private void shuffleArray(String[] answers){
		Random rnd = new Random();
		for(int i = 0; i<3; i++){
			int index = rnd.nextInt(i+1);
			String a = answers[index];
			answers[index] = answers[i];
			answers[i] = a;
		}
	}
	
	private void showAlert() {
		AlertDialog.Builder builder = new AlertDialog.Builder(MultipleChoice.this);
		//CharSequence text = shake_hand_answers_shuffled[0];
		
		//Toast toast = Toast.makeText(context, text, 6);
		//toast.show();
		
		shuffleArray(shake_hand_answers_shuffled);
		
		//Below is for true or false option
		int emptyString = 0;
		for (int i = 0; i < 3; i++){
			if (shake_hand_answers_shuffled[i].length() == 0){
				emptyString = 1;
				
			}
		}
		String[] copy = new String[2];
		int i = 0;
		if (emptyString == 1){
			for (String s : shake_hand_answers_shuffled){
				if (s.length() != 0){
					copy[i] = s;
					i++;
				}
			}
			shake_hand_answers_shuffled = copy;
		}
			
		//Toast.makeText(this, "emptyString length is " + Integer.toString(emptyString), Toast.LENGTH_LONG).show();
		//above is for true or false option
		
        builder.setTitle(question)//(R.string.shake_hand_question)
        	   .setItems(shake_hand_answers_shuffled, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					for(int l = 0; l<3; l++){
						for(int k =0; k<shake_hand_answers_shuffled.length; k++){
							if (shake_hand_answers[l] == shake_hand_answers_shuffled[k]){
								shuffled_indexes_list[k]=l;
							}
						}	
					}
					Intent i = new Intent(MultipleChoice.this, Prompt.class);
					if(which == 0)
						i.putExtra("answer", shuffled_indexes_list[0]);
					if(which == 1)
						i.putExtra("answer", shuffled_indexes_list[1]);
					if(which == 2)
						i.putExtra("answer", shuffled_indexes_list[2]);
					i.putExtra("ID", ID);
					
					dialog.dismiss();
					startActivity(i);
					}
        	   });
        // Create the AlertDialog object and return it
        builder.setCancelable(false);
        AlertDialog alert = builder.create();
        alert.show();
	}	
	
	@Override
	public void onBackPressed(){
		//Do Nothing.
		//Toast.makeText(this, "Video saved to:\n" +
          //      data.getData(), Toast.LENGTH_LONG).show();
		showAlert();
	}
	
}
