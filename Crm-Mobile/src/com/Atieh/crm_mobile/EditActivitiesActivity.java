package com.Atieh.crm_mobile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import singleTones.TempActivityID;
import adapters.CalendarTool;
import adapters.NothingSelectedSpinnerAdapter;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import dataBase.database;

public class EditActivitiesActivity extends Activity {

	EditText et_title;
	EditText et_sharh;
	TextView date, txtPSroductServises, txt_sel_customer, txt_rel_customer;
	Button seldate;
	ImageView save;
	ImageView savenew;
	ImageView close;
	Spinner spnr_azsaat;
	Spinner spnr_tasaat;
	Spinner spnr_vazife;
	Spinner spnr_mahsolvakhadamat;
	Spinner spnr_vaziat;
	Spinner spnr_ghararbadi;
	CalendarTool calendarTool;
	database db;

	String statt;
	
	String fromDate;
	String toDate;

	List<String> customersList;
	List<String> customersIDsList;
	List<String> stats;
	List<String> statsID;

	List<String> relativePersonList;
	List<String> relativePersonIDsList;
	List<String> tasksList;
	List<String> productsList;
	List<String> srvicesList;
	List<String> vazeeyatLst;
	List<String> hasNextActivityList;
	
	String activityID;
	String CustomerID;
	String RelativePersonID;

	public void initview() {

		et_title = (EditText) findViewById(R.id.et_newactivitytitle);
		et_sharh = (EditText) findViewById(R.id.et_sharh_newactivity);
		txt_sel_customer = (TextView) findViewById(R.id.txt_sel_customer);
		txt_rel_customer = (TextView) findViewById(R.id.txt_rel_customer);

		spnr_azsaat = (Spinner) findViewById(R.id.spnr_newactivity_azsaat);
		spnr_tasaat = (Spinner) findViewById(R.id.spnr_newactivity_tasaat);
		spnr_vazife = (Spinner) findViewById(R.id.spnr_newactivity_task);
		spnr_vaziat = (Spinner) findViewById(R.id.spnr_newactivity_vaziat);
		spnr_ghararbadi = (Spinner) findViewById(R.id.spnr_newactivity_ghararbadi);
		save = (ImageView) findViewById(R.id.img_save_newtask);
		savenew = (ImageView) findViewById(R.id.img_savenew_newtask);
		close = (ImageView) findViewById(R.id.img_close_newtask);
		seldate = (Button) findViewById(R.id.btn__newactivity_seldate);
		date = (TextView) findViewById(R.id.txt_newactivity_Date);
		txtPSroductServises = (TextView) findViewById(R.id.txt_product_services);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activities_new);

		initview();

		HomeWatcher mHomeWatcher = new HomeWatcher(this);
		mHomeWatcher.setOnHomePressedListener(new OnHomePressedListener() {
			@Override
			public void onHomePressed() {

				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), MainActivity.class);
				startActivity(intent);
				System.exit(0);
			}

			@Override
			public void onHomeLongPressed() {
			}
		});
		mHomeWatcher.startWatch();



		// date.setText(text);
		db = new database(this);
		db.database();
		db.open();

		activityID = getIntent().getExtras().getString("ActivityID");
		TempActivityID.getInstance().setTempActivityID(activityID);

		
		Cursor cc = db.GetActivityByID(activityID);
		while (cc.moveToNext()) {
			et_title.setText(cc.getString(1));
			et_sharh.setText(cc.getString(4));
			CustomerID = cc.getString(3);
			RelativePersonID = cc.getString(8);
			spnr_azsaat.setSelection(Integer.valueOf(cc.getString(5).split(" ")[1].split(":")[0]));
			spnr_tasaat.setSelection(Integer.valueOf(cc.getString(11).split(" ")[1].split(":")[0]));
			
			date.setText(cc.getString(5).split(" ")[0].replace(":", "/"));
			statt = cc.getString(2);
		}

		Cursor c11 = db.GetPersonRelationsByCustomerIdAndID(CustomerID,RelativePersonID);
		while(c11.moveToNext()){
			txt_rel_customer.setText(c11.getString(0));
		}
		
		Cursor c21 = db.GetCustomersByID(CustomerID);
		while (c21.moveToNext()) {
			txt_sel_customer.setText(c21.getString(0));
		}
		
		String ps = "";
		Cursor c31 = db.mydb.rawQuery("select productName as a from products p inner join ActivitiesProducts tp on p.[productGUID] = tp.[productGUID]  where tp.[ActivityGUID] = '"+activityID+"' union select serviceName as a from services p2 inner join ActivitiesServices tp2 on p2.[serviceGUID] = tp2.[serviceGUID] where tp2.[ActivityGUID] = '"+activityID+"'",null);
		while (c31.moveToNext()) {
			ps = ps + " " + c31.getString(0);
		}
		
		
		
		txtPSroductServises.setText(ps);
		
		
		

		
		
		stats = new ArrayList<String>();
		statsID = new ArrayList<String>();

		Cursor statsCursor = db.mydb.rawQuery("select * from activityStatus",
				null);
		while (statsCursor.moveToNext()) {
			stats.add(statsCursor.getString(1));
			statsID.add(statsCursor.getString(0));

		}
		
	

		ArrayAdapter<String> statsAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, stats);

		statsAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spnr_vaziat.setAdapter(new NothingSelectedSpinnerAdapter(statsAdapter,
				R.layout.activity_stats_nothing_selected, this));

		
		for(int i =0 ; i<statsID.size();i++){
			if(statsID.get(i).equals(statt)){
				spnr_vaziat.setSelection(i);
				break;
			}
		}
		
		close.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();

			}
		});
		

		spnr_tasaat.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				if ( id <= spnr_azsaat.getSelectedItemId()) {
					Toast.makeText(EditActivitiesActivity.this,
							"ساعت پایان نباید کمتر از ساعت شروع باشد",
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

		txt_sel_customer.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent(EditActivitiesActivity.this,
						CustomerListActivity.class);
				i.putExtra(HomeActivity.EnterCustomersListStat, "faliyat");
				startActivityForResult(i, 2);
			}
		});

		txtPSroductServises.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(EditActivitiesActivity.this,
						SelectProducteServicesActivity.class);

				startActivityForResult(i, 1);
			}
		});

		seldate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(EditActivitiesActivity.this,
						DatepickerActivity.class));

			}
		});

		save.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				save.setOnClickListener(null);

				convertToDateTime();
				InsertTask();

			}
		});

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
				txtPSroductServises.setText("");
				db = new database(this);
				db.database();
				db.open();
				Cursor c = db.GetActivityProduct(activityID);
				while (c.moveToNext()) {
					ps = ps + c.getString(0) + " ";
				}

				Cursor c2 = db.GetActivityService(activityID);
				while (c2.moveToNext()) {
					ps = ps + c2.getString(0) + " ";
				}

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
			txt_sel_customer.setText(CustomerListActivity.RelCustomerName);
			txt_rel_customer.setText(CustomerListActivity.RelName);

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

		if (spnr_azsaat.getSelectedItemId() < spnr_tasaat.getSelectedItemId()) {
			fromDate = datee + spnr_azsaat.getSelectedItem().toString();
			toDate = datee + spnr_tasaat.getSelectedItem().toString();
		}

		// Toast.makeText(getApplicationContext(), fromDate + toDate,
		// Toast.LENGTH_LONG).show();

	}

	public void InsertTask() {

		if (spnr_azsaat.getSelectedItemId() <= spnr_tasaat.getSelectedItemId()) {

			db.DeleteActivities(activityID);
			db.InsertActivities(activityID, et_title.getText().toString(),
					statsID.get((int) spnr_vaziat.getSelectedItemId()),
					CustomerListActivity.RelCustomerID.equals("")? CustomerID:CustomerListActivity.RelCustomerID ,
					
					et_sharh.getText()  ////////////////////////////////////////////////////////
							.toString(), fromDate, "1", "1",
					CustomerListActivity.RelID.equals("")? RelativePersonID : CustomerListActivity.RelID ,
					"1", "1", toDate);

			CustomerListActivity.RelCustomerID = "";
			CustomerListActivity.RelID = "";
			
			db.close();

			Intent intent = new Intent();
			intent.setClass(EditActivitiesActivity.this, HomeActivity.class);
			startActivity(intent);
		} else {
			Toast.makeText(EditActivitiesActivity.this,
					"ساعت پایان نباید کمتر از ساعت شروع باشد",
					Toast.LENGTH_LONG).show();
			spnr_tasaat.setBackgroundColor(Color.RED);
		}
	}

}
