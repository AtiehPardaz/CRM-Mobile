package com.Atieh.crm_mobile;

import java.util.ArrayList;
import java.util.List;

import dataBase.database;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;

public class NewCustomerActivity extends Activity {

	Spinner typeSpinner;
	Spinner roleSpinner;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_customer);
		
		initview();
		
		List<String> roleTypes = new ArrayList<String>();
		List<String> roleIDs = new ArrayList<String>();

		
		database db;
		db = new database(this);
		db.database();
		db.open();

		Cursor relationRoles = db.getRelationRoles();
		
		while (relationRoles.moveToNext()) {
			roleTypes.add(relationRoles.getString(1));
			roleIDs.add(relationRoles.getString(0));
		}

		db.close();
		
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
        (this, android.R.layout.simple_spinner_item,roleTypes);
		
		dataAdapter.setDropDownViewResource
        (android.R.layout.simple_spinner_dropdown_item);
		
		roleSpinner.setAdapter(dataAdapter);
		
		
		
		
		
		
		
		
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_customer, menu);
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
		typeSpinner = (Spinner) findViewById(R.id.sp_customer_type);
		roleSpinner = (Spinner) findViewById(R.id.sp_customer_role);

	}
}
