package com.VidCoach.myapp;

import com.VidCoach.myapp.R.raw;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.provider.ContactsContract.RawContacts;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;




public class Vidcoach extends ActionBarActivity{
	private LinearLayout layout;
	private Button mainbutton;
	private ImageButton button1;
	private ImageButton button2;
	private ImageButton button3;
	private ImageButton button4;
	private ImageButton button5;
	private ImageButton button6;
	private int videoCounter;
	private Intent in;
	private Editor editor;
	private SharedPreferences pref;
	private Cursor c;
	private SQLiteDatabase db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vidcoach);
		
		setTitle("VidCoach");
		
		pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
		editor = pref.edit();
		
		Context context = getApplicationContext();
		int[] iArray = {1, 8, 22, 32, 41, 56};
		
		try{
			db = openOrCreateDatabase("InterviewerTable", MODE_PRIVATE,null);
			
			c = db.rawQuery("SELECT i.PreUrl FROM VideoTable AS i WHERE i.ID = 1", null);
			c.moveToFirst();
		}
		catch(SQLiteException e){
			db = openOrCreateDatabase("InterviewerTable", MODE_PRIVATE,null);
			db.execSQL("CREATE TABLE IF NOT EXISTS InterviewerTable(ID INT(4),Genre VARCHAR, VideoType VARCHAR);");
			db.execSQL("CREATE TABLE IF NOT EXISTS VideoTable(ID INT(4), PreUrl VARCHAR, PostUrl VARCHAR, RecordUrl VARCHAR);");
			db.execSQL("CREATE TABLE IF NOT EXISTS PromptTable(ID INT(4), PrePrompt VARCHAR, PostPrompt VARCHAR, CorrectAnswer VARCHAR, WrongAnswerFirst VARCHAR, WrongAnswerSecond VARCHAR);");
			
			
		    db.delete("InterviewerTable", null, null);
			db.delete("VideoTable", null, null);
			db.delete("PromptTable", null, null);
			
			
			db.execSQL("CREATE TABLE IF NOT EXISTS InterviewerTable(ID INT(4),Genre VARCHAR, VideoType VARCHAR);");
	
		//	General
		
			db.execSQL("INSERT INTO InterviewerTable VALUES (1 ,'General','Greeting');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (2 ,'General','Resume');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (3 ,'General','About Me');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (4 ,'General','Strengths');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (5 ,'General','Why Should We Hire You');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (6 ,'General','Any Questions');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (7 ,'General','Farewell');");
			
		//Retail with Experience
			db.execSQL("INSERT INTO InterviewerTable VALUES (8 ,'Retail with Experience','Greeting');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (9 ,'Retail with Experience','Resume');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (10,'Retail with Experience','Experience');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (11,'Retail with Experience','What Did You Enjoy');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (12,'Retail with Experience','Leaving Previous Position');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (13,'Retail with Experience','Coworker');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (14,'Retail with Experience','What Do You Like About Retail');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (15,'Retail with Experience','Customer Service');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (16,'Retail with Experience','Upselling');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (17,'Retail with Experience','Tell Me A Time...');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (18,'Retail with Experience','School Values');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (19,'Retail with Experience','Why Should We Hire You');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (20,'Retail with Experience','Any Questions');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (21,'Retail with Experience','Farewell');");
			
		// Retail without Experience
			db.execSQL("INSERT INTO InterviewerTable VALUES (22,'Retail without Experience','Greeting');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (23,'Retail without Experience','Resume');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (24,'Retail without Experience','Experience');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (25,'Retail without Experience','Why Retial');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (26,'Retail without Experience','Customer Service');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (27,'Retail without Experience','Upselling');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (28,'Retail without Experience','School Values');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (29,'Retail without Experience','Why Should We Hire You');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (30,'Retail without Experience','Any Questions');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (31,'Retail without Experience','Farewell');");
			
		// Food Service
			db.execSQL("INSERT INTO InterviewerTable VALUES (32,'Food Service','Greeting');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (33,'Food Service','Resume');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (34,'Food Service','About Me');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (35,'Food Service','Experience');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (36,'Food Service','Tell Me A Time...');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (37,'Food Service','Are You A Team Player');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (38,'Food Service','Why Should We Hire You');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (39,'Food Service','Any Questions');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (40,'Food Service','Farewell');");
			
		// Healthcare
			db.execSQL("INSERT INTO InterviewerTable VALUES (41,'Healthcare','Greeting');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (42,'Healthcare','Resume');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (43,'Healthcare','Experience');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (44,'Healthcare','Previous Position');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (45,'Healthcare','Leaving Previous Position');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (46,'Healthcare','Coworkers');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (47,'Healthcare','Why Healthcare');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (48,'Healthcare','Helping While On Break');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (49,'Healthcare','Tell Me A Time...');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (50,'Healthcare','Nights And Weekends');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (51,'Healthcare','Dress Code');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (52,'Healthcare','School Values');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (53,'Healthcare','Why Should We Hire You');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (54,'Healthcare','Any Questions');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (55,'Healthcare','Farewell');");
			
		// Hospitality
			db.execSQL("INSERT INTO InterviewerTable VALUES (56,'Hospitality','Greeting');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (57,'Hospitality','Resume');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (58,'Hospitality','About Me');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (59,'Hospitality','Customer Service');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (60,'Hospitality','Unhappy Customer');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (61,'Hospitality','Positive Change');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (62,'Hospitality','Why Should We Hire You');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (63,'Hospitality','Any Questions');");
			db.execSQL("INSERT INTO InterviewerTable VALUES (64,'Hospitality','Farewell');");	
			
			
			db.execSQL("CREATE TABLE IF NOT EXISTS VideoTable(ID INT(4), PreUrl VARCHAR, PostUrl VARCHAR, RecordUrl VARCHAR);");
		
			// General
			for (int i=1; i<65; i++){
				db.execSQL("INSERT INTO VideoTable VALUES ("+Integer.toString(i)+",'android.resource://com.VidCoach.myapp/raw/v"+Integer.toString(i*2-1)+"','android.resource://com.VidCoach.myapp/raw/v"+Integer.toString(i*2)+"','');");
				
			}
	
			
//			db.execSQL("INSERT INTO VideoTable VALUES (1 ,'android.resource://com.VidCoach.myapp/raw/v1.mp4','android.resource://com.VidCoach.myapp/raw/v2.mp4','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (2 ,'android.resource://com.VidCoach.myapp/raw/v3','android.resource://com.VidCoach.myapp/raw/v4','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (3 ,'android.resource://com.VidCoach.myapp/raw/v5','android.resource://com.VidCoach.myapp/raw/v6','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (4 ,'android.resource://com.VidCoach.myapp/raw/v7','android.resource://com.VidCoach.myapp/raw/v8','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (5 ,'android.resource://com.VidCoach.myapp/raw/v9','android.resource://com.VidCoach.myapp/raw/v10','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (6 ,'android.resource://com.VidCoach.myapp/raw/v11','android.resource://com.VidCoach.myapp/raw/v12','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (7 ,'android.resource://com.VidCoach.myapp/raw/v13','android.resource://com.VidCoach.myapp/raw/v14','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (8 ,'android.resource://com.VidCoach.myapp/raw/v15','android.resource://com.VidCoach.myapp/raw/v16','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (9 ,'android.resource://com.VidCoach.myapp/raw/v17','android.resource://com.VidCoach.myapp/raw/v18','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (10,'android.resource://com.VidCoach.myapp/raw/v19','android.resource://com.VidCoach.myapp/raw/v20','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (11,'android.resource://com.VidCoach.myapp/raw/v21','android.resource://com.VidCoach.myapp/raw/v22','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (12,'android.resource://com.VidCoach.myapp/raw/v23','android.resource://com.VidCoach.myapp/raw/v24','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (13,'android.resource://com.VidCoach.myapp/raw/v25','android.resource://com.VidCoach.myapp/raw/v26','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (14,'android.resource://com.VidCoach.myapp/raw/v27','android.resource://com.VidCoach.myapp/raw/v28','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (15,'android.resource://com.VidCoach.myapp/raw/v29','android.resource://com.VidCoach.myapp/raw/v30','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (16,'android.resource://com.VidCoach.myapp/raw/v31','android.resource://com.VidCoach.myapp/raw/v32','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (17,'android.resource://com.VidCoach.myapp/raw/v33','android.resource://com.VidCoach.myapp/raw/v34','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (18,'android.resource://com.VidCoach.myapp/raw/v35','android.resource://com.VidCoach.myapp/raw/v36','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (19,'android.resource://com.VidCoach.myapp/raw/v37','android.resource://com.VidCoach.myapp/raw/v38','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (20,'android.resource://com.VidCoach.myapp/raw/v39','android.resource://com.VidCoach.myapp/raw/v40','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (21,'android.resource://com.VidCoach.myapp/raw/v41','android.resource://com.VidCoach.myapp/raw/v42','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (22,'android.resource://com.VidCoach.myapp/raw/v43','android.resource://com.VidCoach.myapp/raw/v44','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (23,'android.resource://com.VidCoach.myapp/raw/v45','android.resource://com.VidCoach.myapp/raw/v46','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (24,'android.resource://com.VidCoach.myapp/raw/v47','android.resource://com.VidCoach.myapp/raw/v48','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (25,'android.resource://com.VidCoach.myapp/raw/v49','android.resource://com.VidCoach.myapp/raw/v50','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (26,'android.resource://com.VidCoach.myapp/raw/v51','android.resource://com.VidCoach.myapp/raw/v52','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (27,'android.resource://com.VidCoach.myapp/raw/v53','android.resource://com.VidCoach.myapp/raw/v54','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (28,'android.resource://com.VidCoach.myapp/raw/v55','android.resource://com.VidCoach.myapp/raw/v56','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (29,'android.resource://com.VidCoach.myapp/raw/v57','android.resource://com.VidCoach.myapp/raw/v58','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (30,'android.resource://com.VidCoach.myapp/raw/v59','android.resource://com.VidCoach.myapp/raw/v60','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (31,'android.resource://com.VidCoach.myapp/raw/v61','android.resource://com.VidCoach.myapp/raw/v62','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (32,'android.resource://com.VidCoach.myapp/raw/v63','android.resource://com.VidCoach.myapp/raw/v64','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (33,'android.resource://com.VidCoach.myapp/raw/v65','android.resource://com.VidCoach.myapp/raw/v66','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (34,'android.resource://com.VidCoach.myapp/raw/v67','android.resource://com.VidCoach.myapp/raw/v68','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (35,'android.resource://com.VidCoach.myapp/raw/v69','android.resource://com.VidCoach.myapp/raw/v70','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (36,'android.resource://com.VidCoach.myapp/raw/v71','android.resource://com.VidCoach.myapp/raw/v72','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (37,'android.resource://com.VidCoach.myapp/raw/v73','android.resource://com.VidCoach.myapp/raw/v74','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (38,'android.resource://com.VidCoach.myapp/raw/v75','android.resource://com.VidCoach.myapp/raw/v76','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (39,'android.resource://com.VidCoach.myapp/raw/v77','android.resource://com.VidCoach.myapp/raw/v78','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (40,'android.resource://com.VidCoach.myapp/raw/v79','android.resource://com.VidCoach.myapp/raw/v80','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (41,'android.resource://com.VidCoach.myapp/raw/v81','android.resource://com.VidCoach.myapp/raw/v82','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (42,'android.resource://com.VidCoach.myapp/raw/v83','android.resource://com.VidCoach.myapp/raw/v84','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (43,'android.resource://com.VidCoach.myapp/raw/v85','android.resource://com.VidCoach.myapp/raw/v86','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (44,'android.resource://com.VidCoach.myapp/raw/v87','android.resource://com.VidCoach.myapp/raw/v88','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (45,'android.resource://com.VidCoach.myapp/raw/v89','android.resource://com.VidCoach.myapp/raw/v90','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (46,'android.resource://com.VidCoach.myapp/raw/v91','android.resource://com.VidCoach.myapp/raw/v92','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (47,'android.resource://com.VidCoach.myapp/raw/v93','android.resource://com.VidCoach.myapp/raw/v94','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (48,'android.resource://com.VidCoach.myapp/raw/v95','android.resource://com.VidCoach.myapp/raw/v96','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (49,'android.resource://com.VidCoach.myapp/raw/v97','android.resource://com.VidCoach.myapp/raw/v98','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (50,'android.resource://com.VidCoach.myapp/raw/v99','android.resource://com.VidCoach.myapp/raw/v100','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (51,'android.resource://com.VidCoach.myapp/raw/v101','android.resource://com.VidCoach.myapp/raw/v102','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (52,'android.resource://com.VidCoach.myapp/raw/v103','android.resource://com.VidCoach.myapp/raw/v104','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (53,'android.resource://com.VidCoach.myapp/raw/v105','android.resource://com.VidCoach.myapp/raw/v106','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (54,'android.resource://com.VidCoach.myapp/raw/v107','android.resource://com.VidCoach.myapp/raw/v108','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (55,'android.resource://com.VidCoach.myapp/raw/v109','android.resource://com.VidCoach.myapp/raw/v110','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (56,'android.resource://com.VidCoach.myapp/raw/v111','android.resource://com.VidCoach.myapp/raw/v112','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (57,'android.resource://com.VidCoach.myapp/raw/v113','android.resource://com.VidCoach.myapp/raw/v114','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (58,'android.resource://com.VidCoach.myapp/raw/v115','android.resource://com.VidCoach.myapp/raw/v116','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (59,'android.resource://com.VidCoach.myapp/raw/v117','android.resource://com.VidCoach.myapp/raw/v118','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (60,'android.resource://com.VidCoach.myapp/raw/v119','android.resource://com.VidCoach.myapp/raw/v120','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (61,'android.resource://com.VidCoach.myapp/raw/v121','android.resource://com.VidCoach.myapp/raw/v122','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (62,'android.resource://com.VidCoach.myapp/raw/v123','android.resource://com.VidCoach.myapp/raw/v124','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (63,'android.resource://com.VidCoach.myapp/raw/v125','android.resource://com.VidCoach.myapp/raw/v126','');");
//			db.execSQL("INSERT INTO VideoTable VALUES (64,'android.resource://com.VidCoach.myapp/raw/v127','android.resource://com.VidCoach.myapp/raw/v128','');");
//				
			 
	
			db.execSQL("CREATE TABLE IF NOT EXISTS PromptTable(ID INT(4), PrePrompt VARCHAR(255), PostPrompt VARCHAR(255), CorrectAnswer VARCHAR(255), WrongAnswerFirst VARCHAR, WrongAnswerSecond VARCHAR(255));");
					
			// General
			db.execSQL("INSERT INTO PromptTable VALUES (1 ,'If the interviewer extends his/her hand to you, you should shake their hand for about two seconds.', 'How long should you shake hands for?', '2 seconds', '3 minutes', '30 seconds');");
			db.execSQL("INSERT INTO PromptTable VALUES (2 ,'Hand your resume to the interviewer.', 'You should remember to bring your resume. True or False', 'true', 'false', '' );");
			db.execSQL("INSERT INTO PromptTable VALUES (3 ,'Tell the interviewer about yourself! You can talk about school, your goals, interests, and hobbies.', 'Which of the following responses is an appropriate answer?', 'I like to help people and I am involved in community service.', 'I do not want to work here.', 'I once got in trouble for being late to school.' );");
			db.execSQL("INSERT INTO PromptTable VALUES (4 ,'Tell the interviewer about your strengths! You can talk about leadership skills, how you work with others, and awards that you have received.', 'Which of the following responses is an appropriate answer?', 'I get good grades in school, I work well with others, and I learn fast.', 'I am bad at working with others', 'I ate spaghetti for dinner last night.' );");
			db.execSQL("INSERT INTO PromptTable VALUES (5 ,'Tell the interviewer why you want to work for them and why you think you would be a good fit for the position.', 'It is okay if you do not have any work experience. True or false?', 'True', 'false', '' );");
			db.execSQL("INSERT INTO PromptTable VALUES (6 ,'You should ask the interviewer a question, For example you can ask about job responsibilities or your work schedule.', 'Which of the following is an appropriate question to ask?', 'How many hours would I be expected to work per week?', 'Is it okay if I use my phone while at work?', 'How much money would I make' );");
			db.execSQL("INSERT INTO PromptTable VALUES (7 ,'Your interviewer should have answered all of your questions. Make sure to thank your interviewer and shake hands if they extend their hand to you', 'You should end the interview with a thank you. True or false?', 'True', 'false', '' );");
	
			//Retail with Experience
			db.execSQL("INSERT INTO PromptTable VALUES (8 ,'If the interviewer extends his/her hand to you, you should shake their hand for about two seconds.', 'How long should you shake hands', '2 Seconds', '1 minute', '30 Seconds' );");
			db.execSQL("INSERT INTO PromptTable VALUES (9 ,'Hand your resume to the interviewer', 'You should remember to bring your resume. True or false?', 'True', 'false', '' );");
			db.execSQL("INSERT INTO PromptTable VALUES (10,'Talk about a time when you worked a retail job', 'If you have not worked in retail, It is okay.', 'True', 'false', '' );");
			db.execSQL("INSERT INTO PromptTable VALUES (11,'Talk about a positive experience you had in your previous job', 'Which of the following is an appropriate answer', 'I enjoyed working with people', 'I hated my coworkers.', 'I sat in the corner and listened to my ipod.' );");
			db.execSQL("INSERT INTO PromptTable VALUES (12,'Tell the interviewer why you are looking for a new job.', 'Which of the following is an appropriate answer', 'I had to go back to school.', 'I was bad at my job.', 'Everyone was mean' );");
			//db.execSQL("INSERT INTO PromptTable VALUES (13,'','','','','');");
			db.execSQL("INSERT INTO PromptTable VALUES (13,'Think about the interactions you had with coworkers and what positive traits you displayed.', 'Which of the following is an appropriate answer.' , 'They would say I am a hard worker and friendly.', 'They would say I was mean to customers.', 'I do not know.');");
			db.execSQL("INSERT INTO PromptTable VALUES (14,'Tell the interviewer about the positive parts of working in retail', 'Which of the following is an appropriate answer' , 'I like working with others', 'I like going to school.', 'I hate going to work.');");
			db.execSQL("INSERT INTO PromptTable VALUES (15,'Tell the interviewer what customer service means to you.', 'Tell the interviewer what customer service means to you.' , 'Customer service is about assisting the customer.', 'I do not know', 'Customer service is about taking the money of customer.');");
			db.execSQL("INSERT INTO PromptTable VALUES (16,'Tell the interviewer what upselling, also known as basket sale, or add-on sale means.', 'If you do not know what upselling means, you should do which of the following?' , 'Ask the interviewer what he means', 'I do not know', 'Ignore the question.');");
			
			//db.execSQL("INSERT INTO PromptTable VALUES (17,'','','','','');");
			db.execSQL("INSERT INTO PromptTable VALUES (17,'Tell the interviewer about a personal experience when you helped a customer.', 'Which of the following is an appropriate answer' , 'When a customer could not find the size they wanted I looked to see if we had it in the back.', 'I have a dog.', 'I never helped a customer.');");
			db.execSQL("INSERT INTO PromptTable VALUES (18,'Explain different princibles you learned that will help you in job.', 'Which of the following is an appropriate answer' , 'It is important to be respectful to others.', 'I did not learn anything in school.', 'It is cold in here.');");
			db.execSQL("INSERT INTO PromptTable VALUES (19,'Tell the interviewer why you want to work for them and how you would be a good fit for the position', 'Which of the following is an appropriate answer' , 'I would learn a lot and gain a lot of skills. I also think I would be an asset to the team.', 'I do not know', 'I do not really want to work here');");
			db.execSQL("INSERT INTO PromptTable VALUES (20,'Ask the interviewer a question.', 'Which of the following is an appropriate answer' , 'How many hours do you expect me to work?', 'How much do I get paid?', 'Can I arrive late to work?');");
			db.execSQL("INSERT INTO PromptTable VALUES (21,'Make sure to thank the interviewer and shake their hands if they extend their hands to you', 'You should end the inter view with a thank you. True or false?' , 'True', 'False', '');");
	
			//Retail without Experience
			db.execSQL("INSERT INTO PromptTable VALUES (22,'If the interviewer extends his/her hand to you, you should shake their hand for about two seconds.', 'How long should you shake hands for?' , '2 Seconds', '30 seconds', '1 minute');");
			db.execSQL("INSERT INTO PromptTable VALUES (23,'Hand your resume to the interviewer', 'You should remember to bring your resume. True or false?', 'True', 'false', '' );");
			//db.execSQL("INSERT INTO PromptTable VALUES (24,'','','','','');");
			db.execSQL("INSERT INTO PromptTable VALUES (24,'Tell the interviewer about a time when you worked a retail job', 'It is okay to not have any previous retail experience. True or false?' , 'True', 'false', '');");
			db.execSQL("INSERT INTO PromptTable VALUES (25,'Tell the interviewer why you want to work in retail', 'Which of the following is an appropriate answer' , 'I like going to school', 'I want to learn more about working in retail', 'I hate going to work');");
			//db.execSQL("INSERT INTO PromptTable VALUES (26,'', '' , '', '', 'I am not sure.');");
			
			db.execSQL("INSERT INTO PromptTable VALUES (26,'Tell the interviewer what customer service is', 'Which of the following is an appropriate answer' , 'Customer service is about assisting the customer.', 'Customer service is about taking the money of customer.', 'I am not sure.');");
			db.execSQL("INSERT INTO PromptTable VALUES (27,'What does upselling mean to you', 'If you do not know what upselling means, you should do which of the following?' , 'Ask the interviewer what he means.', 'I do not know.', 'Ignore the question.');");
			db.execSQL("INSERT INTO PromptTable VALUES (28,'Explain different principles you learned that will help you in this job.', 'Which of the following is an appropriate answer?' , 'It is important to be respectful to others.', 'It is cold in here.', 'I did not learn anything in school.');");
			db.execSQL("INSERT INTO PromptTable VALUES (29,'Tell the interviewer why you want to work for them and how you would be a good fit for the position.','Which of the following is an appropriated answer?','I would learn a lot and gain a lot of skills. I also think I would be an asset to the team.','I do not know.','I do not really want to work here.');");
			db.execSQL("INSERT INTO PromptTable VALUES (30,'Ask the interviewer a question.','Which of the following is an acceptable question?','How many hours a week does the position require?','Can I arrive late to work?','How much do I get paid?');");
			db.execSQL("INSERT INTO PromptTable VALUES (31,'Make sure you thank the interviewer and shake their hand if they extend their hand to you.','You should end the interview with a thank you. True or false?','True','False','');");	
			
			// Food Service
			db.execSQL("INSERT INTO PromptTable VALUES (32,'If the interviewer extends his/her hand to you, you should shake their hand for about two seconds.','How long should you shake hands for?','2 seconds','30 seconds','1 minute');");
			db.execSQL("INSERT INTO PromptTable VALUES (33,'Hand your resume to the interviewer.','You should remember to bring your resume. True or false?','True','False','');");
			db.execSQL("INSERT INTO PromptTable VALUES (34,'Telling the interviewer about yourself: You should talk about school, goals, interests, and hobbies','Which of the following is an appropriate answer?','I like to help people and I am involved in community service.','I once got in trouble for being late to school.','I do not wnat to work here.');");
			db.execSQL("INSERT INTO PromptTable VALUES (35,'Tell the interviewer what experience you have in the restaurant industry if you have any.','Which of the following is an appropriate answer?','I washed dishes and cleared tables.','People were loud and mean.','I flew a kite yesterday.');");
			db.execSQL("INSERT INTO PromptTable VALUES (36,'Talk about a time you have helped a customer.','Which of the following is an appropriate answer?','When a customer did not like their food, I took it back and gave them a new plate.','I sat in the back and listened to my iPod.','I yelled at a customer when they were mean to me.');");
			db.execSQL("INSERT INTO PromptTable VALUES (37,'Tell the interviewer about a time you were in a team and helped the group in a positive way','You should tell the interviewer you hate working with others. True or false?','False','True','');");
			db.execSQL("INSERT INTO PromptTable VALUES (38,'Tell the interviewer why you want to work for them and how you would be a good fit for the position.','Which of the following is an appropriated answer?','I would learn a lot and gain a lot of skills. I also think I would be an asset to the team.','I do not know.','I do not really want to work here.');");
			db.execSQL("INSERT INTO PromptTable VALUES (39,'Ask the interviewer a question.','Which of the following is an acceptable question?','How many hours a week does the position require?','Can I arrive late to work?','How much do I get paid?');");
			db.execSQL("INSERT INTO PromptTable VALUES (40,'Make sure you thank the interviewer and shake their hand if they extend their hand to you.','You should end the interview with a thank you. True or false?','True','False','');");	
		
	
			//Healthcare
			db.execSQL("INSERT INTO PromptTable VALUES (41,'If the interviewer extends his/her hand to you, you should shake their hand for about two seconds.','How long should you shake hands for?','2 seconds','30 seconds','1 minute');");
			db.execSQL("INSERT INTO PromptTable VALUES (42,'Hand your resume to the interviewer.','You should remember to bring your resume. True or false?','True','False','');");
			db.execSQL("INSERT INTO PromptTable VALUES (43,'Talk about where you have worked for a group in the healthcare industry (hospital, seniorcenter, rehabilitation center).','If you have not worked in healthcare, it is OK. True or false?','True','False','');");
			db.execSQL("INSERT INTO PromptTable VALUES (44,'Talk about a positive experience you had in your previous job.','Which of the following is an appropriate answer?','I enjoyed working with people.','I hated my coworker.','I sat in the corner and listened to my iPod.');");
			db.execSQL("INSERT INTO PromptTable VALUES (45,'Tell the interviewer why you are looking for a new job.','Which of the following is an appropriate answer?','I moved to another city.','Everyone was mean.','I was bad at my job.');");
			db.execSQL("INSERT INTO PromptTable VALUES (46,'Think about the interactions you had with coworkers and what positive traits you displayed.','Which of the following is an appropriate answer?','They would say I am a hard worker and friendly.','They would say I was mean to patients.','I like waffles.');");
			db.execSQL("INSERT INTO PromptTable VALUES (47,'Tell the interviewer about the positive parts of working in healthcare.','Which of the following is an appropriate answer?','I like working with others.','I like going to school.','I hate going to work.');");
			db.execSQL("INSERT INTO PromptTable VALUES (48,'Tell the interviewer how you would help a patient during your break.','Which of the following is an appropriate answer?','I would help them as best I could.','I like to dance.','I would go home.');");
			db.execSQL("INSERT INTO PromptTable VALUES (49,'Talk about a time you have helped a patient.','Which of the following is an appropriate answer?','The patients requested a nurse and I got one for them.','Hospital food is good.','I would not go out of my way to help a patient.');");
			db.execSQL("INSERT INTO PromptTable VALUES (50,'Tell the interviewer if there is any reason why you cannot work certain nights or weekends.','You should tell the interviewer if you have a small conflict. True or false?','True','False','');");	
			db.execSQL("INSERT INTO PromptTable VALUES (51,'Tell the interviewer if you have any concerns about wearing a uniform.','It is OK to wear casual clothing instead of a uniform. True or false?','False','True','');");
			db.execSQL("INSERT INTO PromptTable VALUES (52,'Explain principles you learned that will help you in this job?','Which of the following is an appropriate answer?','It is important to be respectful to others.','It is cold in here.','I did not learn anything in school.');");
			db.execSQL("INSERT INTO PromptTable VALUES (53,'Tell the interviewer why you want to work for them and how you would be a good fit for the position.','Which of the following is an appropriated answer?','I would learn a lot and gain a lot of skills. I also think I would be an asset to the team.','I do not know.','I do not really want to work here.');");
			db.execSQL("INSERT INTO PromptTable VALUES (54,'Ask the interviewer a question.','Which of the following is an acceptable question?','How many hours a week does the position require?','Can I arrive late to work?','How much do I get paid?');");
			db.execSQL("INSERT INTO PromptTable VALUES (55,'Make sure you thank the interviewer and shake their hand if they extend their hand to you.','You should end the interview with a thank you. True or false?','True','False','');");	
			
			//Hospitality 		
			db.execSQL("INSERT INTO PromptTable VALUES (56,'If the interviewer extends his/her hand to you, you should shake their hand for about two seconds.','How long should you shake hands for?','2 seconds','30 seconds','1 minute');");
			db.execSQL("INSERT INTO PromptTable VALUES (57,'Hand your resume to the interviewer.','You should remember to bring your resume. True or false?','True','False','');");
			db.execSQL("INSERT INTO PromptTable VALUES (58,'Telling the interviewer about yourself: You should talk about school, goals, interests, and hobbies','Which of the following is an appropriate answer?','I like to help people and I am involved in community service.','I once got in trouble for being late to school.','I do not wnat to work here.');");
			db.execSQL("INSERT INTO PromptTable VALUES (59,'Tell the interviewer what customer service means to you','Which of the following is an appropriate answer?','Customer service is about assisting the customer.','I do not know.','Customer service is about taking the money from customer.');");
			db.execSQL("INSERT INTO PromptTable VALUES (60,'Tell the interviewer about a personal experience when you helped a customer.','Which of the following is an appropriated answer?','When a customer needed another blanket in his room because he was cold, I got it for him.','I never helped a customer.','I have a dog.');");
			db.execSQL("INSERT INTO PromptTable VALUES (61,'Share an experience where you did something that improved your workplace.','Which of the following is an appropriated answer?','I suggested that they create a system for substituions in case someone called in sick.','I do not know.','I sat in the basement and listened to my ipod.');");
			db.execSQL("INSERT INTO PromptTable VALUES (62,'Tell the interviewer why you want to work for them and how you would be a good fit for the position.','Which of the following is an appropriated answer?','I would learn a lot and gain a lot of skills. I also think I would be an asset to the team.','I do not know.','I do not really want to work here.');");
			db.execSQL("INSERT INTO PromptTable VALUES (63,'Ask the interviewer a question.','Which of the following is an acceptable question?','How many hours a week does the position require?','Can I arrive late to work?','How much do I get paid?');");
			db.execSQL("INSERT INTO PromptTable VALUES (64,'Make sure you thank the interviewer and shake their hand if they extend their hand to you.','You should end the interview with a thank you. True or false?','True','False','');");	
		
			
			c = db.rawQuery("SELECT i.PreUrl FROM VideoTable AS i WHERE i.ID = 1", null);
			c.moveToFirst();
		}
		//orm (object oriented)
		 // suger orm android
		
		
		

//		String id = c.getString(c.getColumnIndex("PreUrl"));
//		CharSequence text = ("ID is "+id);
//		
//		Toast toast = Toast.makeText(context, text, 3);
//		toast.show();
		
		//add buttons below
		
//		layout = (LinearLayout) findViewById(R.id.layout1);
//		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//		layout.setOrientation(LinearLayout.VERTICAL);
// implement buttons and actions for each button
		button1 = (ImageButton) findViewById(R.id.ImageButton1);
		button2 = (ImageButton) findViewById(R.id.ImageButton6);
		button3 = (ImageButton) findViewById(R.id.ImageButton2);
		button4 = (ImageButton) findViewById(R.id.ImageButton3);
		button5 = (ImageButton) findViewById(R.id.ImageButton4);
		button6 = (ImageButton) findViewById(R.id.ImageButton5);
		ImageButton[] buttons = {button1, button2, button3, button4,button5, button6};
		for (int i = 0; i < 6; i++){
			c = db.rawQuery("SELECT i.Genre FROM InterviewerTable AS i WHERE i.ID = "+ Integer.toString(iArray[i]), null);
			c.moveToFirst();
			String genre = c.getString(c.getColumnIndex("Genre"));
			
			videoCounter = i;
			
			buttons[i].setId(i);
			
			in = new Intent(Vidcoach.this, Models.class);
			
			buttons[i].setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					// + Integer.toString(videoCounter)
					editor.putInt("videoCounter", v.getId());
		        	editor.commit();
					//in.putExtra("videoCounter", v.getId());
					startActivity(in);
					}
				
			}); 
		}
		
		
		
		
		
		db.close();
		//context.deleteDatabase("InterviewerTable");
		
		
		
		
//		Button b = (Button) findViewById(R.id.button1);
//		
//		b.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Intent i = new Intent(Vidcoach.this, Models.class);
//				//i.putExtra("practice", practice);
//				startActivity(i);
//				}
//			
//		}); 
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent i = new Intent(Vidcoach.this, Setting.class);
			startActivity(i);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	public void onBackPressed(){
		
	}
	
}
