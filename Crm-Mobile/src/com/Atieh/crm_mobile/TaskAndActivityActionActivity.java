package com.Atieh.crm_mobile;

import java.util.ArrayList;
import java.util.List;

import adapters.ActivityListAdapter;
import adapters.TaskListAdapter;
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

public class TaskAndActivityActionActivity extends Activity {

	boolean taskFlag = false;
	boolean activityFlag = false;
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
	List<List<String[]>> activitiesList;

	database db;
	ListView listView;

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

	List<String[]> t0 = new ArrayList<String[]>();
	List<String[]> t1 = new ArrayList<String[]>();
	List<String[]> t2 = new ArrayList<String[]>();
	List<String[]> t3 = new ArrayList<String[]>();
	List<String[]> t4 = new ArrayList<String[]>();
	List<String[]> t5 = new ArrayList<String[]>();
	List<String[]> t6 = new ArrayList<String[]>();
	List<String[]> t7 = new ArrayList<String[]>();
	List<String[]> t8 = new ArrayList<String[]>();
	List<String[]> t9 = new ArrayList<String[]>();
	List<String[]> t10 = new ArrayList<String[]>();
	List<String[]> t11 = new ArrayList<String[]>();
	List<String[]> t12 = new ArrayList<String[]>();
	List<String[]> t13 = new ArrayList<String[]>();
	List<String[]> t14 = new ArrayList<String[]>();
	List<String[]> t15 = new ArrayList<String[]>();
	List<String[]> t16 = new ArrayList<String[]>();
	List<String[]> t17 = new ArrayList<String[]>();
	List<String[]> t18 = new ArrayList<String[]>();
	List<String[]> t19 = new ArrayList<String[]>();
	List<String[]> t20 = new ArrayList<String[]>();
	List<String[]> t21 = new ArrayList<String[]>();
	List<String[]> t22 = new ArrayList<String[]>();
	List<String[]> t23 = new ArrayList<String[]>();

	String date;

	public void initview() {
		btnsearch = (ImageButton) findViewById(R.id.btn_search_customelist);
		btnhome = (ImageButton) findViewById(R.id.btn_home_customerlist);
		btnmonthview = (ImageButton) findViewById(R.id.btn_monthview_taskaction);
		//btnclearsearchtext = (ImageButton) findViewById(R.id.imgbtn_clearsearch_taskaction);
		btnaction = (Button) findViewById(R.id.btn_action_taskaction);
		btntask = (Button) findViewById(R.id.btn_task_taskaction);
		titlemonthTextView = (TextView) findViewById(R.id.tv_title_monthview_taskaction);
		lv_taskaction = (ListView) findViewById(R.id.lv_tasks);
		//et_search = (EditText) findViewById(R.id.et_search_taskaction);
		//ll_hidesearch = (LinearLayout) findViewById(R.id.ll_search_taskaction);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_action);

		listView = (ListView) findViewById(R.id.lv_tasks);

		initview();

		Intent intent = getIntent();
		date = intent.getStringExtra("date");
		//Toast.makeText(TaskAndActivityActionActivity.this, intent.getStringExtra("day"), 1).show();

		String[] seprated = date.split(":");

		if (seprated[1].length() == 1)
			seprated[1] = "0" + seprated[1];

		if (seprated[2].length() == 1)
			seprated[2] = "0" + seprated[2];

		date = seprated[0] + ":" + seprated[1] + ":" + seprated[2];

		updateTasksList();

//		btnsearch.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				ll_hidesearch.setVisibility(View.VISIBLE);
//
//			}
//		});

//		btnclearsearchtext.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				ll_hidesearch.setVisibility(View.GONE);
//				et_search.setText("");
//
//			}
//		});
		
		btnhome.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(TaskAndActivityActionActivity.this,
						HomeActivity.class));

			}
		});
		btnmonthview.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(TaskAndActivityActionActivity.this,
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
				updateActivitiesList();
			}
		});

	}

	private void updateTasksList() {

		boolean[][] titleSet = new boolean[24][30];
		int position = 0;

		if (!taskFlag) {
			db = new database(this);
			db.database();

			db.open();

			Cursor tasksCursor = db.GetTasks(date);
			tasksList = new ArrayList<List<String[]>>();
			int size = tasksCursor.getCount();

			String[] ss = new String[14];

			for (int j = 0; j < 14; j++) {
				ss[j] = "";
			}

			while (tasksCursor.moveToNext()) {

				String[] s = new String[14];

				for (int i = 0; i < 12; i++) {
					s[i] = tasksCursor.getString(i);
				}

				// calculating fromdatetimes
				String[] fromdateHour = s[3].split(" ");
				s[3] = fromdateHour[0]; // task day

				String[] fromhours = fromdateHour[1].split(":");
				int fromhour = Integer.parseInt(fromhours[0]);
				s[12] = fromhours[0]; // is the first in a culumn to write the
										// title only one times

				// calculating TodateTime
				String[] todateHour = s[11].split(" ");
				String[] tohours = todateHour[1].split(":");
				int tohour = Integer.parseInt(tohours[0]);
				s[13] = tohours[0];

				for (int i = 0; i < 24; i++) {
					if (i < fromhour || i > tohour) {
						getTaskList(i).add(ss);
						titleSet[i][position] = false;

					}

					else {

						if (i == fromhour) {
							getTaskList(i).add(s);
							titleSet[i][position] = true;

						} else {
							getTaskList(i).add(s);
							titleSet[i][position] = false;

						}
					}
				}

				position++;

			}

			for (int i = 0; i < 24; i++) {
				tasksList.add(getTaskList(i));
			}

			listView.setAdapter(new TaskListAdapter(
					TaskAndActivityActionActivity.this, tasksList, position,
					titleSet));
			listView.setSelection(6);

			db.close();
		}

		else {
			listView.setAdapter(new TaskListAdapter(
					TaskAndActivityActionActivity.this, tasksList, position,
					titleSet));
			listView.setSelection(6);


		}
	}

	private void updateActivitiesList() {

		boolean[][] titleSet = new boolean[24][30];
		int position = 0;

		if (!activityFlag) {
			db = new database(this);
			db.database();

			db.open();

			Cursor activitiesCursor = db.getActivity(date);
			activitiesList = new ArrayList<List<String[]>>();

			int size = activitiesCursor.getCount();

			String[] ss = new String[4];

			for (int j = 0; j < 4; j++) {
				ss[j] = "";
			}

			while (activitiesCursor.moveToNext()) {

				String[] s = new String[4];

				for (int i = 0; i < 4; i++) {
					s[i] = activitiesCursor.getString(i);
				}

				// calculating fromdatetimes
				String[] fromdateHour = s[2].split(" ");
				s[2] = fromdateHour[0]; // task day

				String[] fromhours = fromdateHour[1].split(":");
				int fromhour = Integer.parseInt(fromhours[0]);

				// calculating TodateTime
				String[] todateHour = s[3].split(" ");
				String[] tohours = todateHour[1].split(":");
				int tohour = Integer.parseInt(tohours[0]);
				s[3] = tohours[0];

				for (int i = 0; i < 24; i++) {
					if (i < fromhour || i > tohour) {
						getActivitiesList(i).add(ss);
						titleSet[i][position] = false;

					}

					else {

						if (i == fromhour) {
							getActivitiesList(i).add(s);
							titleSet[i][position] = true;

						} else {
							getActivitiesList(i).add(s);
							titleSet[i][position] = false;

						}
					}
				}

				position++;

			}

			for (int i = 0; i < 24; i++) {
				activitiesList.add(getActivitiesList(i));
			}

			listView.setAdapter(new ActivityListAdapter(
					TaskAndActivityActionActivity.this, activitiesList,
					position, titleSet));
			listView.setSelection(6);

			db.close();
		}

		else {

			listView.setAdapter(new ActivityListAdapter(
					TaskAndActivityActionActivity.this, activitiesList,
					position, titleSet));
			listView.setSelection(6);

		}
	}

	public List<String[]> getTaskList(int i) {
		switch (i) {
		case 0:
			return l0;
		case 1:
			return l1;
		case 2:
			return l2;
		case 3:
			return l3;
		case 4:
			return l4;
		case 5:
			return l5;
		case 6:
			return l6;
		case 7:
			return l7;
		case 8:
			return l8;
		case 9:
			return l9;
		case 10:
			return l10;
		case 11:
			return l11;
		case 12:
			return l12;
		case 13:
			return l13;
		case 14:
			return l14;
		case 15:
			return l15;
		case 16:
			return l16;
		case 17:
			return l17;
		case 18:
			return l18;
		case 19:
			return l19;
		case 20:
			return l20;
		case 21:
			return l21;
		case 22:
			return l22;
		case 23:
			return l23;

		default:
			return null;
		}

	}

	public List<String[]> getActivitiesList(int i) {
		switch (i) {
		case 0:
			return t0;
		case 1:
			return t1;
		case 2:
			return t2;
		case 3:
			return t3;
		case 4:
			return t4;
		case 5:
			return t5;
		case 6:
			return t6;
		case 7:
			return t7;
		case 8:
			return t8;
		case 9:
			return t9;
		case 10:
			return t10;
		case 11:
			return t11;
		case 12:
			return t12;
		case 13:
			return t13;
		case 14:
			return t14;
		case 15:
			return t15;
		case 16:
			return t16;
		case 17:
			return t17;
		case 18:
			return t18;
		case 19:
			return t19;
		case 20:
			return t20;
		case 21:
			return t21;
		case 22:
			return t22;
		case 23:
			return t23;

		default:
			return null;
		}

	}

}
