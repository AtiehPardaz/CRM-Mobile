package com.Atieh.crm_mobile;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.Atieh.crm_mobile.R.color;

import dataBase.database;

public class TaskActionActivity extends Activity {
	ImageButton btnsearch;
	ImageButton btnhome;
	ImageButton btnmonthview;
	ImageButton btnclearsearchtext;
	Button btnaction;
	Button btntask;
	TextView titlemonthTextView;
	LinearLayout ll_loading;
	LinearLayout ll_hidesearch;
	ListView lv_taskaction;
	EditText et_search;
    ArrayList<ArrayList<String>> tasksList;
	database db;


	public void initview() {
		btnsearch = (ImageButton) findViewById(R.id.btn_search_customelist);
		btnhome = (ImageButton) findViewById(R.id.btn_home_customerlist);
		btnmonthview = (ImageButton) findViewById(R.id.btn_monthview_taskaction);
		btnclearsearchtext = (ImageButton) findViewById(R.id.imgbtn_clearsearch_taskaction);
		btnaction = (Button) findViewById(R.id.btn_action_taskaction);
		btntask = (Button) findViewById(R.id.btn_task_taskaction);
		titlemonthTextView = (TextView) findViewById(R.id.tv_title_monthview_taskaction);
		lv_taskaction = (ListView) findViewById(R.id.lv_task_action);
		et_search = (EditText) findViewById(R.id.et_search_taskaction);
		ll_hidesearch = (LinearLayout) findViewById(R.id.ll_search_taskaction);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_action);

		initview();
		db = new database(this);
		db.database();

		// ll_hidesearch.setVisibility(View.GONE);
		Intent intent = getIntent();
		String date = intent.getStringExtra("date");
		
		String[] seprated = date.split(":");
		
		if(seprated[1].length() == 1)
			seprated[1] = "0"+seprated[1];
		
		if(seprated[2].length() == 1)
			seprated[2] = "0"+seprated[2];
		
		date = seprated[0] +":"+ seprated[1] +":" + seprated[2];
		
		db.open();
		
		
		Cursor tasksCursor = db.GetTasks(date);
		
		List<String> taskslist = new ArrayList<String>();
		while (tasksCursor.moveToNext()) {
		    taskslist.add(tasksCursor.getString(0));
		}
		
		Toast.makeText(TaskActionActivity.this,
				taskslist.get(0) == null ? "null":taskslist.get(0),
				1).show();

		db.close();

		
		
		
		btnsearch.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				ll_hidesearch.setVisibility(View.VISIBLE);

			}
		});

		btnclearsearchtext.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				ll_hidesearch.setVisibility(View.GONE);
				et_search.setText("");

			}
		});
		btnhome.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(TaskActionActivity.this,
						HomeActivity.class));

			}
		});
		btnmonthview.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(TaskActionActivity.this,
						com.Atieh.crm_mobile_calendar.MainActivity.class));

			}
		});
		btntask.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				btntask.setBackgroundResource(color.bg_action_green);
				btnaction.setBackgroundResource(color.bg_action_gray);
				Toast.makeText(getApplicationContext(), "وظیفه", 1).show();
			}
		});
		btnaction.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				btntask.setBackgroundResource(color.bg_action_gray);
				btnaction.setBackgroundResource(color.bg_action_green);
				// btnservisec.setTextColor(color.first_row_background_color);
				Toast.makeText(getApplicationContext(), "فعالیت", 1).show();
			}
		});

	}

}
