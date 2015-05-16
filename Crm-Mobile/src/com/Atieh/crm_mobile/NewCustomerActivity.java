package com.Atieh.crm_mobile;

import java.util.ArrayList;
import java.util.List;

import adapters.NothingSelectedSpinnerAdapter;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import dataBase.database;
import android.app.Dialog;
import android.widget.TextView;
import android.widget.Button;

public class NewCustomerActivity extends Activity {

	Spinner typeSpinner;
	Spinner roleSpinner;
	EditText relationPersonName, name, address, tell;
	ImageView save;
	ImageView discared;
	List<String> roleIDs;
	database dbase;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_customer);

		initview();
		
		HomeWatcher mHomeWatcher = new HomeWatcher(this);
		mHomeWatcher.setOnHomePressedListener(new OnHomePressedListener() {
		    @Override
		    public void onHomePressed() {
		       Intent intent = new Intent();intent.setClass(getApplicationContext(), MainActivity.class);startActivity(intent);System.exit(0);
		    }
		    @Override
		    public void onHomeLongPressed() {
		    }
		});
		mHomeWatcher.startWatch();
		

		List<String> roleTypes = new ArrayList<String>();
		roleIDs = new ArrayList<String>();
		List<String> customerTypes = new ArrayList<String>();

		customerTypes.add("حقیقی");
		customerTypes.add("حقوقی");

		dbase = new database(this);
		dbase.database();
		dbase.open();

		Cursor relationRoles = dbase.getRelationRoles();

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

		typeSpinner.setAdapter(new NothingSelectedSpinnerAdapter(
				customerTypesdataAdapter,
				R.layout.customer_type__nothing_selected, this));

		roleSpinner.setAdapter(new NothingSelectedSpinnerAdapter(dataAdapter,
				R.layout.relation_roles_nothing_selected, this));

		dbase.close();

		save.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				String customerID = java.util.UUID.randomUUID().toString();
				String alert = "";
				if (address.getText().toString().equals("")) {
					alert = alert + "آدرس را وارد نمایید" + "\n";
				}
				String addressString = address.getText().toString();

				if ((int) typeSpinner.getSelectedItemId() == -1) {
					alert = alert + "نوع مشتری را انتخاب کنید." + "\n";
				}

				Integer typeInt = (int) typeSpinner.getSelectedItemId();

				if (tell.getText().toString().equals("")) {
					alert = alert + "شماره تلفن را وارد نمایید." + "\n";
				}

				String tellString = tell.getText().toString();

				if (name.getText().toString().equals("")) {
					alert = alert + "نام شرکت را وارد نمایید." + "\n";
				}

				String nameString = name.getText().toString();

				if (relationPersonName.getText().toString().equals("")) {
					alert = alert + "نام شخص رابط را وارد نمایید." + "\n";
				}

				String relationPersonname = relationPersonName.getText()
						.toString();

				if (roleSpinner.getSelectedItemId() == -1) {
					alert = alert + "نوع رابطه را انتخاب نمایید." + "\n";
				}
				if (!alert.equals("")) {
					Toast.makeText(NewCustomerActivity.this, alert,
							Toast.LENGTH_LONG).show();
				}

				else {
					dbase = new database(NewCustomerActivity.this);
					dbase.database();
					dbase.open();

					dbase.InsertCustomer(customerID, nameString, "m", typeInt,
							addressString, tellString, 0);

					dbase.InsertPersonRelations(customerID, customerID,
							roleIDs.get((int) roleSpinner.getSelectedItemId()),
							relationPersonname, 0);

					dbase.close();

					save.setOnClickListener(null);

					final Dialog dialog = new Dialog(arg0.getContext());
					dialog.setContentView(R.layout.custom_dialog);
					dialog.setTitle("ذخیره مشتری");

					// set the custom dialog components - text, image and button
					TextView text = (TextView) dialog.findViewById(R.id.text);
					text.setText("مشتری ذخیره گردید.");
					ImageView image = (ImageView) dialog
							.findViewById(R.id.image);
					image.setImageResource(R.drawable.ic_launcher);

					Button dialogButton = (Button) dialog
							.findViewById(R.id.dialogButtonOK);
					// if button is clicked, close the custom dialog
					dialogButton.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							dialog.dismiss();

							Intent intent = new Intent();
							intent.setClass(NewCustomerActivity.this,
									CustomerListActivity.class);
							intent.putExtra("onResume", true);
							intent.putExtra("EnterCustomersListStat", "new");
							startActivity(intent);

						}
					});

					dialog.show();
				}

			}
		});

		discared.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				finish();
			}
		});

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
		relationPersonName = (EditText) findViewById(R.id.et_relation_person_name);
		save = (ImageView) findViewById(R.id.img_save_new_customer);
		discared = (ImageView) findViewById(R.id.img_discared_new_customer);
		name = (EditText) findViewById(R.id.et_name_new_customer);
		address = (EditText) findViewById(R.id.et_address_new_customer);
		tell = (EditText) findViewById(R.id.et_tell_new_customer);

	}
}
