package com.Atieh.crm_mobile;

import java.util.ArrayList;
import java.util.List;

import GetTasksPack.TasksListAdapter;
import android.R.string;
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
	List<List<String[]>> tasksList;
	database db;
	ListView listView ;

	List<String[]> l0 = new ArrayList<String[]>();
	List<String[]> l1 = new ArrayList<String[]>();
	List<String[]> l2 = new ArrayList<String[]>();
	List<String[]> l3 = new ArrayList<String[]>();
	List<String[]> l4 = new ArrayList<String[]>();
	List<String[]> l5 = new ArrayList<String[]>();
	List<String[]> l6 = new ArrayList<String[]>();
	List<String[]> l7 = new ArrayList<String[]>();
	List<String[]> l8 = new ArrayList<String[]>();
	List<String[]> l9 = new ArrayList<String[]>();
	List<String[]> l10 = new ArrayList<String[]>();
	List<String[]> l11 = new ArrayList<String[]>();
	List<String[]> l12 = new ArrayList<String[]>();
	List<String[]> l13 = new ArrayList<String[]>();
	List<String[]> l14 = new ArrayList<String[]>();
	List<String[]> l15 = new ArrayList<String[]>();
	List<String[]> l16 = new ArrayList<String[]>();
	List<String[]> l17 = new ArrayList<String[]>();
	List<String[]> l18 = new ArrayList<String[]>();
	List<String[]> l19 = new ArrayList<String[]>();
	List<String[]> l20 = new ArrayList<String[]>();
	List<String[]> l21 = new ArrayList<String[]>();
	List<String[]> l22 = new ArrayList<String[]>();
	List<String[]> l23 = new ArrayList<String[]>();



	public void initview() {
		btnsearch = (ImageButton) findViewById(R.id.btn_search_customelist);
		btnhome = (ImageButton) findViewById(R.id.btn_home_customerlist);
		btnmonthview = (ImageButton) findViewById(R.id.btn_monthview_taskaction);
		btnclearsearchtext = (ImageButton) findViewById(R.id.imgbtn_clearsearch_taskaction);
		btnaction = (Button) findViewById(R.id.btn_action_taskaction);
		btntask = (Button) findViewById(R.id.btn_task_taskaction);
		titlemonthTextView = (TextView) findViewById(R.id.tv_title_monthview_taskaction);
		lv_taskaction = (ListView) findViewById(R.id.lv_tasks);
		et_search = (EditText) findViewById(R.id.et_search_taskaction);
		ll_hidesearch = (LinearLayout) findViewById(R.id.ll_search_taskaction);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_action);

		listView = (ListView) findViewById(R.id.lv_tasks);

		initview();
		
		updateTasksList();



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
				updateTasksList();
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

	private void updateTasksList() {
		
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
		tasksList = new ArrayList<List<String[]>>();
		boolean[][] titleSet = new boolean[24][30];
		String[][] TaskIDs = new String[24][30];
		int size = tasksCursor.getCount();
		//fillListEmpty(size);
		
		String[] ss = new String[14];

		for (int j = 0; j <14; j++) {
			ss[j] = "";
		}


		int position = 0;
		while (tasksCursor.moveToNext()) {

			String[] s = new String[14];

			for (int i = 0; i < 12; i++) {
				s[i] = tasksCursor.getString(i);
			}
		

			//calculating fromdatetimes
			String[] fromdateHour = s[3].split(" ");
			s[3] = fromdateHour[0]; // task day
			
			String[] fromhours = fromdateHour[1].split(":");
			int fromhour = Integer.parseInt(fromhours[0]);
			s[12] = fromhours[0]; // is the first in a culumn to write the title only one times

			//calculating TodateTime
			String[] todateHour = s[11].split(" ");
			String[] tohours = todateHour[1].split(":");
			int tohour = Integer.parseInt(tohours[0]);
			s[13] = tohours[0];

			
			for (int i = 0; i < 24; i++) {
				if (i<fromhour || i> tohour) {
					getList(i).add(ss);
					titleSet[i][position]= false;
					TaskIDs[i][position]= s[0];

				}
				
				else {
					
					if(i == fromhour){
						getList(i).add(s);
						titleSet[i][position]= true;
						TaskIDs[i][position]= s[0];

					}
					else {
						getList(i).add(s);
						titleSet[i][position]= false;
						TaskIDs[i][position]= s[0];


					}
				}
			}
			
			position++;

		}

		for (int i = 0; i < 24; i++) {
			tasksList.add(getList(i));
		}
		


		listView.setAdapter(new TasksListAdapter(TaskActionActivity.this, tasksList , position ,titleSet,TaskIDs));

		db.close();
	}

	
	public List<String[]> getList(int i){
		switch (i) {
		case	0	  : return 	 l0	;
		case	1	  : return 	 l1	;
		case	2	  : return 	 l2	;
		case	3	  : return 	 l3	;
		case	4	  : return 	 l4	;
		case	5	  : return 	 l5	;
		case	6	  : return 	 l6	;
		case	7	  : return 	 l7	;
		case	8	  : return 	 l8	;
		case	9	  : return 	 l9	;
		case	10	  : return 	 l10	;
		case	11	  : return 	 l11	;
		case	12	  : return 	 l12	;
		case	13	  : return 	 l13	;
		case	14	  : return 	 l14	;
		case	15	  : return 	 l15	;
		case	16	  : return 	 l16	;
		case	17	  : return 	 l17	;
		case	18	  : return 	 l18	;
		case	19	  : return 	 l19	;
		case	20	  : return 	 l20	;
		case	21	  : return 	 l21	;
		case	22	  : return 	 l22	;
		case	23	  : return 	 l23	;
		

		default:
			return null;
		}

	}

}
