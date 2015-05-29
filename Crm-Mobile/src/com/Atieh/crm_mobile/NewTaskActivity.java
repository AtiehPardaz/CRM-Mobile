package com.Atieh.crm_mobile;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import dataBase.database;
import singleTones.TempTaskID;
import adapters.CalendarTool;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class NewTaskActivity extends Activity {

	EditText et_title;
	EditText et_sharh;
	EditText et_ghabl;

	TextView date, txtPSroductServises;

	ImageButton datepicker;
	ImageView save;
	ImageView savenew;
	ImageView close;

	TextView txt_customer;
	TextView txt_rabet;
	Spinner spnr_azsaat;
	Spinner spnr_tasaat;
	
	CheckBox chk_remebber_task;
	
	LinearLayout lnr_layout_remember;

	Spinner spnr_mahsolvakhadamat;

	RadioButton rdbtn_hamanlahze;
	RadioButton rdbtn_ghabl;

	database db;
	String fromDate;
	String toDate;

	String TaskID;

	public void initview() {
		et_title = (EditText) findViewById(R.id.et_newtasktitle);
		et_sharh = (EditText) findViewById(R.id.et_sharh_newtask);
		et_ghabl = (EditText) findViewById(R.id.et_daghigheghabl_newtask);
		txt_customer = (TextView) findViewById(R.id.txt_newtask_customer);
		txt_rabet = (TextView) findViewById(R.id.txt__newtask_shakhsrabet);
		spnr_azsaat = (Spinner) findViewById(R.id.spnr_newtask_azsaat);
		spnr_tasaat = (Spinner) findViewById(R.id.spnr_newtask_tasaat);
		txtPSroductServises = (TextView) findViewById(R.id.txtPSroductServises);

		date = (TextView) findViewById(R.id.txt_taskDate);

		datepicker = (ImageButton) findViewById(R.id.img_datenewtask);
		save = (ImageView) findViewById(R.id.img_save_newtask);
		savenew = (ImageView) findViewById(R.id.img_savenew_newtask);
		close = (ImageView) findViewById(R.id.img_close_newtask2);
		

		rdbtn_ghabl = (RadioButton) findViewById(R.id.rdbtn_newtask_ghabl);
		rdbtn_hamanlahze = (RadioButton) findViewById(R.id.rdbtn_remember_newtask);

		lnr_layout_remember = (LinearLayout) findViewById(R.id.lnr_layout_remember);
		chk_remebber_task = (CheckBox) findViewById(R.id.chk_remember_task);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_new);

		initview();
		
		lnr_layout_remember.setVisibility(View.GONE);
		chk_remebber_task.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					lnr_layout_remember.setVisibility(View.VISIBLE);
				}
				else {
					lnr_layout_remember.setVisibility(View.GONE);

				}
			}
		});
		
		HomeWatcher mHomeWatcher = new HomeWatcher(this);
		mHomeWatcher.setOnHomePressedListener(new OnHomePressedListener() {
			@Override
			public void onHomePressed() {
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), MainActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
						| Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivity(intent);
				System.exit(0);
			}

			@Override
			public void onHomeLongPressed() {
			}
		});
		mHomeWatcher.startWatch();



		Calendar calendar = Calendar.getInstance();

		int yearNow = calendar.get(Calendar.YEAR);
		int mounthNow = (calendar.get(Calendar.MONTH)) + 1;
		int dayNow = calendar.get(Calendar.DAY_OF_MONTH);

		CalendarTool calendarTool = new CalendarTool();
		calendarTool.setGregorianDate(yearNow, mounthNow, dayNow);
		date.setText(calendarTool.getIranianDate());

		db = new database(this);
		db.database();
		db.open();

		TaskID = java.util.UUID.randomUUID().toString();

		TempTaskID.getInstance().setTempTaskID(TaskID);

		txt_customer.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent(NewTaskActivity.this,
						CustomerListActivity.class);
				i.putExtra(HomeActivity.EnterCustomersListStat, "vazifeh");
				startActivityForResult(i, 2);
			}
		});

		SimpleDateFormat sdf = new SimpleDateFormat("HH");
		int currentDateandTime = Integer.valueOf(sdf.format(new Date()));
		if (currentDateandTime == 23) {
			currentDateandTime--;
		}
		spnr_azsaat.setSelection(currentDateandTime);
		spnr_tasaat.setSelection(currentDateandTime + 1);

		spnr_tasaat.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				if (((int) id) <= (int) spnr_azsaat.getSelectedItemId()) {
					Toast.makeText(NewTaskActivity.this,
							"ساعت شروع نباید از ساعت پایان کمتر باشد.",
							Toast.LENGTH_LONG).show();
					spnr_tasaat.setBackgroundColor(Color.RED);
				} else {
					spnr_tasaat.setBackgroundResource(R.drawable.spinner_back);
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		txtPSroductServises.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i2 = new Intent(NewTaskActivity.this,
						SelectProducteServicesTask.class);
				startActivityForResult(i2, 1);

			}
		});

		datepicker.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(NewTaskActivity.this,
						DatepickerActivity.class));

			}
		});

		close.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				// db.mydb.rawQuery("delete from TasksServices  where TasksServices.[TaskGUID] = '"+TaskID+"'",
				// null);
				// db.close();
				finish();
			}
		});

		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				save.setOnClickListener(null);

				convertToDateTime();

				InsertTask();

			}
		});

		// datepicker.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View arg0) {
		//
		//
		// }
		// });

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (DatepickerActivity.myDay != null) {

			date.setText(DatepickerActivity.myYear + "/"
					+ DatepickerActivity.myMonth + "/"
					+ DatepickerActivity.myDay);

		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == 1) {
			if (resultCode == RESULT_OK) {
				String ps = "";
				db = new database(this);
				db.database();
				db.open();
				Cursor c = db.GetTaskProductServices(TaskID);
				while (c.moveToNext()) {
					ps = ps + c.getString(0) + " و ";
				}
				//
				// Cursor c2 = db.GetTaskService(TaskID);
				// while (c2.moveToNext()) {
				// ps = ps + c2.getString(0)+ " و ";
				// }

				if (ps.length() > 3) {
					txtPSroductServises
							.setText(ps.substring(0, ps.length() - 2));
				}

			}
			if (resultCode == RESULT_CANCELED) {
				// Write your code if there's no result
			}
		}

		else if (requestCode == 2) {

			// if(resultCode == RESULT_OK){
			txt_customer.setText(CustomerListActivity.RelCustomerName);
			txt_rabet.setText(CustomerListActivity.RelName);

			// }
		}

	}

	public void convertToDateTime() {

		String datee = "";
		String mounth_ = "";
		String year_ = "";
		String day_ = "";
		if (DatepickerActivity.myMonth == null
				|| DatepickerActivity.myYear == null
				|| DatepickerActivity.myDay == null) {

			Utilities util;

			util = new Utilities();
			Date date2 = new Date();

			if (util.getMonth(date2) < 10) {
				mounth_ = "0" + util.getMonth(date2);
			} else {
				mounth_ = Integer.toString(util.getMonth(date2));
			}
			if (util.getDay(date2) < 10) {
				day_ = "0" + util.getDay(date2);
			} else {
				day_ = Integer.toString(util.getDay(date2));
			}

			datee = util.getYear(date2) + ":" + mounth_ + ":" + day_ + " ";

		} else {
			if (Integer.valueOf(DatepickerActivity.myMonth) < 10) {
				mounth_ = "0" + DatepickerActivity.myMonth;
			} else {
				mounth_ = DatepickerActivity.myMonth;
			}

			if (Integer.valueOf(DatepickerActivity.myDay) < 10) {
				day_ = "0" + DatepickerActivity.myDay;
			} else {
				day_ = DatepickerActivity.myDay;
			}
			datee = DatepickerActivity.myYear + ":" + mounth_ + ":" + day_
					+ " ";
		}

		fromDate = datee + spnr_azsaat.getSelectedItem().toString();
		toDate = datee + spnr_tasaat.getSelectedItem().toString();

		Toast.makeText(getApplicationContext(), fromDate + toDate,
				Toast.LENGTH_LONG).show();

	}

	public void InsertTask() {

		if ((int) spnr_azsaat.getSelectedItemId() <= (int) spnr_tasaat
				.getSelectedItemId()) {

			db.InsertTasks(TaskID, CustomerListActivity.RelCustomerID, et_sharh
					.getText().toString(), fromDate, "0", "1", "1",
					CustomerListActivity.RelID, "1", "1", et_title.getText()
							.toString(), toDate);
			CustomerListActivity.RelCustomerID = "";
			CustomerListActivity.RelID = "";
			db.close();
			Intent intent = new Intent();
			intent.setClass(NewTaskActivity.this, HomeActivity.class);
			startActivity(intent);
		} else {
			Toast.makeText(NewTaskActivity.this,
					"ساعت پایان نباید کمتر از ساعت شروع باشد",
					Toast.LENGTH_LONG).show();
			spnr_tasaat.setBackgroundColor(Color.RED);
		}

	}

}
