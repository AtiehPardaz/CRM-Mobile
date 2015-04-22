package com.Atieh.crm_mobile;

import java.util.ArrayList;
import java.util.List;

import adapters.NothingSelectedSpinnerAdapter;
import singleTones.TempActivityID;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
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
import dataBase.database;

public class NewActivitiesActivity extends Activity {

	EditText et_title;
	EditText et_sharh;

	TextView date;
	Button seldate;
	Button selprodecservices;
	ImageView save;
	ImageView savenew;
	ImageView close;

	Spinner spnr_customer;
	Spinner spnr_rabet;
	Spinner spnr_azsaat;
	Spinner spnr_tasaat;
	Spinner spnr_vazife;
	Spinner spnr_mahsolvakhadamat;
	Spinner spnr_vaziat;
	Spinner spnr_ghararbadi;
	database db;

	List<String> customersList;
	List<String> customersIDsList;

	List<String> relativePersonList;
	List<String> relativePersonIDsList;
	List<String> tasksList;
	List<String> productsList;
	List<String> srvicesList;
	List<String> vazeeyatLst;
	List<String> hasNextActivityList;

	public void initview() {

		et_title = (EditText) findViewById(R.id.et_newactivitytitle);
		et_sharh = (EditText) findViewById(R.id.et_sharh_newactivity);
		spnr_customer = (Spinner) findViewById(R.id.spnr_newactivity_customer);
		spnr_rabet = (Spinner) findViewById(R.id.spnr_newactivity_shakhsrabet);
		spnr_azsaat = (Spinner) findViewById(R.id.spnr_newactivity_azsaat);
		spnr_tasaat = (Spinner) findViewById(R.id.spnr_newactivity_tasaat);
		spnr_vazife = (Spinner) findViewById(R.id.spnr_newactivity_task);
		spnr_mahsolvakhadamat = (Spinner) findViewById(R.id.spnr_newactivity_mahsolkhadamat);
		spnr_vaziat = (Spinner) findViewById(R.id.spnr_newactivity_vaziat);
		spnr_ghararbadi = (Spinner) findViewById(R.id.spnr_newactivity_ghararbadi);
		save = (ImageView) findViewById(R.id.img_save_newtask);
		savenew = (ImageView) findViewById(R.id.img_savenew_newtask);
		close = (ImageView) findViewById(R.id.img_close_newtask);
		seldate = (Button) findViewById(R.id.btn__newactivity_seldate);
		date = (TextView) findViewById(R.id.txt_newactivity_Date);
		selprodecservices = (Button) findViewById(R.id.btn_newactivity_mahsolvakhadamat);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activities_new);

		initview();
		spnr_rabet.setVisibility(View.GONE);
		db = new database(this);
		db.database();
		db.open();

		customersList = new ArrayList<String>();
		customersIDsList = new ArrayList<String>();

		Cursor customersCursor = db.GetCustomers();

		while (customersCursor.moveToNext()) {

			customersList.add(customersCursor.getString(1));
			customersIDsList.add(customersCursor.getString(0));
		}

		ArrayAdapter<String> customersListAdapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_item, customersList);

		customersListAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spnr_customer
				.setAdapter(new NothingSelectedSpinnerAdapter(
						customersListAdapter,
						R.layout.customers_nothing_selected, this));

		spnr_customer.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {

				if (arg2 != 0) {
					spnr_rabet.setVisibility(View.VISIBLE);

					db = new database(NewActivitiesActivity.this);
					db.database();
					db.open();
					String s = customersIDsList.get(arg2 - 1);
					relativePersonList = new ArrayList<String>();
					relativePersonIDsList = new ArrayList<String>();

					Cursor relativePersonCursor = db
							.GetPersonRelationsByCustomerId(s);
					while (relativePersonCursor.moveToNext()) {

						relativePersonList.add(relativePersonCursor
								.getString(3));
						relativePersonIDsList.add(relativePersonCursor
								.getString(0));

					}

					ArrayAdapter<String> relativePersonAdapter = new ArrayAdapter<String>(
							NewActivitiesActivity.this,
							android.R.layout.simple_spinner_item,
							relativePersonList);

					relativePersonAdapter
							.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

					spnr_rabet.setAdapter(new NothingSelectedSpinnerAdapter(
							relativePersonAdapter,
							R.layout.relative_customers_nothing_selected,
							NewActivitiesActivity.this));

					db.close();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

		String activityID = java.util.UUID.randomUUID().toString();

		TempActivityID.getInstance().setTempActivityID(activityID);

		close.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();

			}
		});

		selprodecservices.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(NewActivitiesActivity.this,
						SelectProducteServicesActivity.class));

			}
		});

		seldate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(NewActivitiesActivity.this,
						DatepickerActivity.class));

			}
		});

		db.close();

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (DatepickerActivity.myDay != null) {

			date.setText(DatepickerActivity.myDay + "/"
					+ DatepickerActivity.myMonth + "/"
					+ DatepickerActivity.myYear);

		}
	}

}
