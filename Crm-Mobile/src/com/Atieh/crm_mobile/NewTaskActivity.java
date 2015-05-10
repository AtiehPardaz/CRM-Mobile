package com.Atieh.crm_mobile;

import dataBase.database;
import singleTones.TempActivityID;
import singleTones.TempTaskID;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class NewTaskActivity extends Activity {

	EditText et_title;
	EditText et_sharh;
	EditText et_ghabl;

	TextView date,txtPSroductServises;
	
	ImageButton datepicker;
	ImageView save;
	ImageView savenew;
	ImageView close;

	TextView txt_customer;
	TextView txt_rabet;
	Spinner spnr_azsaat;
	Spinner spnr_tasaat;

	Spinner spnr_mahsolvakhadamat;

	RadioButton rdbtn_hamanlahze;
	RadioButton rdbtn_ghabl;
	database db;
	String fromDate;
	String toDate;
	
	String TaskID ;

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
		
		datepicker=(ImageButton) findViewById(R.id.img_datenewtask);
		save = (ImageView) findViewById(R.id.img_save_newtask);
		savenew = (ImageView) findViewById(R.id.img_savenew_newtask);
		close = (ImageView) findViewById(R.id.img_close_newtask2);


		rdbtn_ghabl = (RadioButton) findViewById(R.id.rdbtn_newtask_ghabl);
		rdbtn_hamanlahze = (RadioButton) findViewById(R.id.rdbtn_remember_newtask);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_new);

		initview();
		
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
						
//				db.mydb.rawQuery("delete from TasksServices  where TasksServices.[TaskGUID] = '"+TaskID+"'", null);
//				db.close();
				finish();
			}
		});
		
		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
						
				save.setOnClickListener(null);
				convertToDateTime();
				
				InsertTask();
				
				finish();
				
			}
		});

		
//		datepicker.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
//				
//				
//			}
//		});

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
	        if(resultCode == RESULT_OK){
	        	String ps = "";
	        	db = new database(this);
	        	db.database();
	        	db.open();
	        	Cursor c = db.GetTaskProduct(TaskID);
	        	while (c.moveToNext()) {
	        		ps = ps + c.getString(0) + " و ";
				}
	        	
	        	Cursor c2 = db.GetTaskService(TaskID);
	        	while (c2.moveToNext()) {
	        		ps = ps + c2.getString(0)+ " و ";
				}
	        	
	        	if(ps.length() > 3){
	        		txtPSroductServises.setText(ps.substring(0, ps.length()-2));
	        	}
	        	
	        }
	        if (resultCode == RESULT_CANCELED) {
	            //Write your code if there's no result
	        }
	    }
		
		else if (requestCode == 2) {
			
			//if(resultCode == RESULT_OK){
				String p = getIntent().getStringExtra("result2");
				txt_customer.setText( CustomerListActivity.RelCustomerName);
				txt_rabet.setText( CustomerListActivity.RelName);

		//	}
		}
		
	}
	
	public void convertToDateTime(){
		
		String datee = DatepickerActivity.myYear + ":"
				+ DatepickerActivity.myMonth + ":"
				+ DatepickerActivity.myDay+" ";
		
		fromDate = datee + spnr_azsaat.getSelectedItem().toString();
		toDate = datee + spnr_tasaat.getSelectedItem().toString();
		
		Toast.makeText(getApplicationContext(), fromDate + toDate, 1).show();
		
		
		
	}
	
	public void InsertTask(){
		
		
		
			db.InsertTasks(TaskID,
					CustomerListActivity.RelCustomerID,
					et_sharh.getText().toString(), 
					fromDate,
					"0",
					"1",
					"1",
					CustomerListActivity.RelID,
					"1",
					"1",
					et_title.getText().toString(), 
					toDate );

			db.close();
	}

	
}
