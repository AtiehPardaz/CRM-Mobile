package com.Atieh.crm_mobile;

import dataBase.database;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityDetailsActivity extends Activity {

	TextView txt_ActivityTitle;
	TextView txt_ActivityCustomer;
	TextView txt_AtcivityRelationPerson;
	TextView txt_ActivityDate;
	TextView txt_ActivityTohour;
	TextView txt_ActivityFromhour;
	TextView txt_ActivityTaskTitle;
	TextView txt_ActivityProductTitle;
	TextView txt_ActivityDetail;
	TextView txt_ActivityStatus;
	TextView txt_ActivityNextAppointment;
	ImageView img_edit_task;
	database db;
	String[] ActivityDetail = new String[12];
	String ID ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity_details);

		// Toast.makeText(ActivityDetailsActivity.this,getIntent().getStringExtra("id"),
		// 1).show();

		initview();
		HomeWatcher mHomeWatcher = new HomeWatcher(this);
		mHomeWatcher.setOnHomePressedListener(new OnHomePressedListener() {
		    @Override
		    public void onHomePressed() {
		       Intent intent = new Intent();intent.setClass(getApplicationContext(), MainActivity.class); intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);startActivity(intent);System.exit(0);
		    }
		    @Override
		    public void onHomeLongPressed() {
		    }
		});
		mHomeWatcher.startWatch();
		db = new database(this);
		db.database();
		db.open();

		ID = getIntent().getStringExtra("id");

		Cursor activityCursor = db.GetActivityByID(ID);

		while (activityCursor.moveToNext()) {
			for (int i = 0; i < 12; i++) {
				ActivityDetail[i] = activityCursor.getString(i);
			}
		}

		txt_ActivityTitle.setText(ActivityDetail[1]);

		Cursor customer = db.GetCustomersByID(ActivityDetail[3]);

		while (customer.moveToNext()) {
			txt_ActivityCustomer.setText(customer.getString(0));
		}

		Cursor relativePerson = db.GetPersonRelationsByCustomerIdAndID(
				ActivityDetail[3], ActivityDetail[8]);

		while (relativePerson.moveToNext()) {
			txt_AtcivityRelationPerson.setText(relativePerson.getString(0));
		}

		String[] fromdateHour = ActivityDetail[5].split(" ");
		txt_ActivityDate.setText(fromdateHour[0]); // activity day

		txt_ActivityFromhour.setText(fromdateHour[1]);

		String[] todateHour = ActivityDetail[11].split(" ");
		txt_ActivityTohour.setText(todateHour[1]);
		
		Cursor ActivityTask = db.GetTaskByID(ActivityDetail[9]);
		while (ActivityTask.moveToNext()) {
			txt_ActivityTaskTitle.setText(ActivityTask.getString(10));
		}
		

		img_edit_task.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(ActivityDetailsActivity.this, EditActivitiesActivity.class);
				intent.putExtra("ActivityID", ID);
				startActivity(intent);
			}
		});
		
		txt_ActivityTaskTitle.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(ActivityDetailsActivity.this, TaskDetailsActivity.class);	
				i.putExtra("id", ActivityDetail[9]);
				startActivity(i);
			}
		});
		
		String taskProductsString = "";
		Cursor taskProducts = db.GetTaskProductsByID(ActivityDetail[9]);
		while (taskProducts.moveToNext()) {
			taskProductsString = taskProductsString + " - " + taskProducts.getString(0) ;
		}
		txt_ActivityProductTitle.setText(taskProductsString);
		
		txt_ActivityDetail.setText(ActivityDetail[4]);
		
		
		Cursor activityStatus = db.GetActivityStatusByID(ActivityDetail[2]);
		
		while (activityStatus.moveToNext()) {
			txt_ActivityStatus.setText(activityStatus.getString(0)) ;
		}
		
		txt_ActivityNextAppointment.setText( ActivityDetail[6].equals("1") ? "بلی":"خیر");
				
		db.close();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_details, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void initview() {

		txt_ActivityTitle = (TextView) findViewById(R.id.txt_ActivityTitle);
		txt_ActivityCustomer = (TextView) findViewById(R.id.txt_ActivityCustomer);
		txt_AtcivityRelationPerson = (TextView) findViewById(R.id.txt_AtcivityRelationPerson);
		txt_ActivityDate = (TextView) findViewById(R.id.txt_ActivityDate);
		txt_ActivityTohour = (TextView) findViewById(R.id.txt_ActivityTohour);
		txt_ActivityFromhour = (TextView) findViewById(R.id.txt_ActivityFromhour);
		txt_ActivityTaskTitle = (TextView) findViewById(R.id.txt_ActivityTaskTitle);
		txt_ActivityProductTitle = (TextView) findViewById(R.id.txt_ActivityProductTitle);
		txt_ActivityDetail = (TextView) findViewById(R.id.txt_ActivityDetail);
		txt_ActivityStatus = (TextView) findViewById(R.id.txt_ActivityStatus);
		txt_ActivityNextAppointment = (TextView) findViewById(R.id.txt_ActivityNextAppointment);
		img_edit_task = (ImageView) findViewById(R.id.img_edit_task);
	}
}
