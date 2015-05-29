package com.Atieh.crm_mobile;

import dataBase.database;
import adapters.SelPersonReladapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class SelPersonRelActivity extends Activity {

	TextView tv_title;
	ListView lv_person;
	String[] arrayID;
	String[] arraytitle;
	String[] arrayRelation;
	String[] arrayRole;
	String txtid;
	String txtTitle;
	database db;
	SelPersonReladapter as2;
	AlertDialog alertDialog;
	String customerid;
	String customerName;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sel_person_relation);

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
		
		tv_title = (TextView) findViewById(R.id.tv_customername_selpersonrel);
		lv_person = (ListView) findViewById(R.id.lv_relativeperson_selpersonrel);
		alertDialog = new AlertDialog.Builder(this).create();
		db = new database(this);
		db.database();
		db.open();

		customerid = getIntent().getExtras().getString("customerIDsel");

		Cursor c = db.GetPersonRelationsByCustomerId(customerid);
		customerName = getIntent().getExtras().getString("customername");
		tv_title.setText(customerName);
		// db.close();

		arrayID = new String[c.getCount()];
		arraytitle = new String[c.getCount()];
		arrayRole = new String[c.getCount()];

		int i = 0;

		if (c.moveToFirst()) {

			do {

				try {

					arrayID[i] = c.getString(1); // 0 id
					arraytitle[i] = c.getString(3); // 1 title
					arrayRole[i] = c.getString(2);

					i++;

				} catch (Exception e) {

					e.printStackTrace();
				}

			} while (c.moveToNext());
		}

		arrayRelation = new String[i];

		for (int j = 0; j < i; j++) {

			Cursor c2 = db.mydb.rawQuery(
					"select Title from RelationRoles where Id ='"
							+ arrayRole[j] + "'", null);
			c2.moveToFirst();
			arrayRelation[j] = c2.getString(0);

			c2.close();
		}

		db.close();

		as2 = new SelPersonReladapter(SelPersonRelActivity.this, arrayID,
				arraytitle, arrayRelation, getApplicationContext());
		lv_person.setAdapter(as2);

		lv_person.setOnItemClickListener(new OnItemClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				TextView tv = (TextView) arg1
						.findViewById(R.id.tv_title_selpersonrel);
				
				txtTitle = tv.getText().toString();
				
				TextView tvId = (TextView) arg1.findViewById(R.id.tv_id_selpersonrel);
				txtid = tvId.getText().toString();
				
				
				
				//Toast.makeText(getApplicationContext(), txtid, 1).show();
				
//				alertDialog.setIcon(R.drawable.ic_launcher);
				alertDialog.setTitle("انتخاب مشتری و شخص رابط");
				alertDialog.setMessage("آیا مشتری و شخص مرتبط انتخاب شده اعمال شود؟");
				alertDialog.setButton("بلی",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								
								Intent returnIntent = new Intent();
								returnIntent.setClass(SelPersonRelActivity.this, CustomerListActivity.class);
								returnIntent.putExtra("RelCustomerID",customerid);
								returnIntent.putExtra("RelCustomerName",customerName);
								
								returnIntent.putExtra("RelID",txtid);
								returnIntent.putExtra("RelName",txtTitle);

								returnIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

								startActivity(returnIntent);
								
								return;
							}
						});
				alertDialog.setButton2("خیر",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								alertDialog.dismiss();
								return;
							}
						});
				alertDialog.show();

			}

		});

	}

	@Override
	public void onBackPressed() {

		Intent intent = new Intent();

		intent.setClass(SelPersonRelActivity.this, CustomerListActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		finish();
	}

}
