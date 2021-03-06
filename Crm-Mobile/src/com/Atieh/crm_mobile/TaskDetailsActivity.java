package com.Atieh.crm_mobile;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import dataBase.database;

public class TaskDetailsActivity extends Activity {

	database db;
	String[] TaskDetail = new String[12];
	TextView txt_taskTitle1 ;
	TextView txt_taskCustomer ;
	TextView txt_relationPerson ;
	TextView txt_taskDate ;
	TextView txt_taskFromhour ;
	TextView txt_taskTohour ;
	TextView txt_taskProductTitle ;
	TextView txt_taskDetail ;
	TextView txt_taskMustNotified ;
	ImageView img_edit_task;
	ImageView img_save_new_customer;
	String ID;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_details);
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
		
		ID = getIntent().getStringExtra("id");
		
		img_save_new_customer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent();
				intent.setClass(TaskDetailsActivity.this, NewActivitiesFromTaskActivity.class);
				intent.putExtra("ParentTaskID",ID );
				startActivity(intent);		
				}
		});
		img_edit_task.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(TaskDetailsActivity.this, EditTaskActivity.class);
				intent.putExtra("TaskID", getIntent().getStringExtra("id"));
				startActivity(intent);
				
			}
		});
		
		db = new database(this);
		db.database();
		
		String ID = getIntent().getStringExtra("id");
		db.open();

		Cursor taskCursor = db.GetTaskByID(ID);

		while (taskCursor.moveToNext()) {
			for (int i = 0; i < 12; i++) {
				TaskDetail[i] = taskCursor.getString(i);
			}
		}

		
		txt_taskTitle1.setText(TaskDetail[10]);
		
		Cursor customer = db.GetCustomersByID(TaskDetail[1]) ;
		
		while (customer.moveToNext()) {
			txt_taskCustomer.setText(customer.getString(0));
		}
		
		Cursor relativePerson = db.GetPersonRelationsByCustomerIdAndID(TaskDetail[1],TaskDetail[7]);

		while (relativePerson.moveToNext()) {
			txt_relationPerson.setText(relativePerson.getString(0));
		}
		
		String[] fromdateHour = TaskDetail[3].split(" ");
		txt_taskDate.setText(fromdateHour[0]); // task day
		
		
		txt_taskFromhour.setText(fromdateHour[1]);
		
		String[] todateHour = TaskDetail[11].split(" ");
		txt_taskTohour.setText(todateHour[1]);
		
		
		String taskProductsString = "";
		Cursor taskProducts = db.GetTaskProductsByID(ID);
		while (taskProducts.moveToNext()) {
			taskProductsString = taskProductsString + " - " + taskProducts.getString(0) ;
		}
		txt_taskProductTitle.setText(taskProductsString);
		
		Cursor taskDetails = db.GetTaskDetailsByID(ID);
		while (taskDetails.moveToNext()) {
			txt_taskDetail.setText(taskDetails.getString(0));
		}
		
		
		
		
		db.close();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.task_details, menu);
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
		
		txt_taskTitle1 = (TextView) findViewById(R.id.txt_ActivityTitle);
		txt_taskCustomer = (TextView) findViewById(R.id.txt_taskCustomer);
		txt_relationPerson = (TextView) findViewById(R.id.txt_AtcivityRelationPerson);
		txt_taskDate = (TextView) findViewById(R.id.txt_taskDate);
		txt_taskFromhour = (TextView) findViewById(R.id.txt_taskFromhour);
		txt_taskTohour = (TextView) findViewById(R.id.txt_taskTohour);
		txt_taskProductTitle = (TextView) findViewById(R.id.txt_taskProductTitle);
		txt_taskDetail = (TextView) findViewById(R.id.txt_taskDetail);
		txt_taskMustNotified = (TextView) findViewById(R.id.txt_taskMustNotified);
		img_edit_task = (ImageView) findViewById(R.id.img_edit_task);
		img_save_new_customer =(ImageView) findViewById(R.id.img_save_new_customer);
	}
}
