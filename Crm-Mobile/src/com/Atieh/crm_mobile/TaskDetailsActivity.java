package com.Atieh.crm_mobile;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
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


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_details);
		initview();
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
		
		txt_taskTitle1 = (TextView) findViewById(R.id.txt_TaskTitle);
		txt_taskCustomer = (TextView) findViewById(R.id.txt_taskCustomer);
		txt_relationPerson = (TextView) findViewById(R.id.txt_relationPerson);
		txt_taskDate = (TextView) findViewById(R.id.txt_taskDate);
		txt_taskFromhour = (TextView) findViewById(R.id.txt_taskFromhour);
		txt_taskTohour = (TextView) findViewById(R.id.txt_taskTohour);
		txt_taskProductTitle = (TextView) findViewById(R.id.txt_taskProductTitle);
		txt_taskDetail = (TextView) findViewById(R.id.txt_taskDetail);
		txt_taskMustNotified = (TextView) findViewById(R.id.txt_taskMustNotified);
	}
}
