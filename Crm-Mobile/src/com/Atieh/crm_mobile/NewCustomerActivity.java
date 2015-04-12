package com.Atieh.crm_mobile;

import java.util.ArrayList;
import java.util.List;

import dataBase.database;

import adapters.NothingSelectedSpinnerAdapter;
import android.app.Activity;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;

public class NewCustomerActivity extends Activity {

	Spinner typeSpinner;
	Spinner roleSpinner;
	Spinner relationRoleTypeSpinner;
	EditText relationPersonName, name, address, tell;
	Button btnRelativePerson;
	LinearLayout layout;
	ImageView save;
	ImageView discared;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_customer);

		initview();
		relationRoleTypeSpinner.setVisibility(View.GONE);
		relationPersonName.setVisibility(View.GONE);
		btnRelativePerson.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (relationRoleTypeSpinner.getVisibility() == View.GONE) {
					relationRoleTypeSpinner.setVisibility(View.VISIBLE);
					relationPersonName.setVisibility(View.VISIBLE);
					layout.setBackgroundColor(Color.parseColor("#DFDFDF"));

				} else {
					relationRoleTypeSpinner.setVisibility(View.GONE);
					relationPersonName.setVisibility(View.GONE);
					layout.setBackgroundColor(Color.parseColor("#FFFFFF"));

				}
			}
		});

		List<String> roleTypes = new ArrayList<String>();
		List<String> roleIDs = new ArrayList<String>();
		List<String> customerTypes = new ArrayList<String>();

		customerTypes.add("حقیقی");
		customerTypes.add("حقوقی");

		final database db;
		db = new database(this);
		db.database();
		db.open();

		Cursor relationRoles = db.getRelationRoles();

		while (relationRoles.moveToNext()) {
			roleTypes.add(relationRoles.getString(1));
			roleIDs.add(relationRoles.getString(0));
		}


		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, roleTypes);

		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		ArrayAdapter<String> customerTypesdataAdapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_item, customerTypes);

		customerTypesdataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		typeSpinner.setAdapter(new NothingSelectedSpinnerAdapter(dataAdapter,
				R.layout.customer_type__nothing_selected, this));

		roleSpinner.setAdapter(new NothingSelectedSpinnerAdapter(dataAdapter,
				R.layout.relation_roles_nothing_selected, this));

		relationRoleTypeSpinner.setAdapter(new NothingSelectedSpinnerAdapter(
				dataAdapter, R.layout.relation_roles_nothing_selected, this));

		save.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String customerID = java.util.UUID.randomUUID().toString();
				db.InsertCustomer(customerID, name.getText().toString(), 
						"",
						(int)typeSpinner.getSelectedItemId(),
						address.getText().toString(),
						tell.getText().toString(),
						0);
				
				db.InsertPersonRelations(customerID,
						java.util.UUID.randomUUID().toString(),
						RelationRoleId,
						Title,
						IsUplodedToServer
						)
			}
		});
		
		db.close();

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
		relationRoleTypeSpinner = (Spinner) findViewById(R.id.sp_relation_role_type);
		relationPersonName = (EditText) findViewById(R.id.et_relation_person_name);
		btnRelativePerson = (Button) findViewById(R.id.btn_relative_person);
		layout = (LinearLayout) findViewById(R.id.layout_relative_person);
		save = (ImageView) findViewById(R.id.img_save_new_customer);
		discared = (ImageView) findViewById(R.id.img_discared_new_customer);
		name = (EditText) findViewById(R.id.et_name_new_customer);
		address = (EditText) findViewById(R.id.et_address_new_customer);
		tell = (EditText) findViewById(R.id.et_tell_new_customer);

	}
}
