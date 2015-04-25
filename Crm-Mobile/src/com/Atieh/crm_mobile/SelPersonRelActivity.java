package com.Atieh.crm_mobile;

import java.util.List;

import dataBase.database;
import adapters.CmListFromAtcivityToServices;
import adapters.SelPersonReladapter;
import android.R.array;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SelPersonRelActivity extends Activity {

	TextView tv_title;
	ListView lv_person;
	String[] arrayID;
	String[] arraytitle;
	String[] arrayRelation;
	String[] arrayRole;
	String txtid;
	database db;
	SelPersonReladapter as2;
	AlertDialog alertDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sel_person_relation);

		tv_title = (TextView) findViewById(R.id.tv_customername_selpersonrel);
		lv_person = (ListView) findViewById(R.id.lv_relativeperson_selpersonrel);
		alertDialog = new AlertDialog.Builder(this).create();
		db = new database(this);
		db.database();
		db.open();

		String customerid = getIntent().getExtras().getString("customerIDsel");

		Cursor c = db.GetPersonRelationsByCustomerId(customerid);

		tv_title.setText(getIntent().getExtras().getString("customername"));
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

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				TextView tv = (TextView) arg1
						.findViewById(R.id.tv_id_selpersonrel);
				txtid = tv.getText().toString();
				Toast.makeText(getApplicationContext(), txtid, 1).show();
				
//				alertDialog.setIcon(R.drawable.ic_launcher);
				alertDialog.setTitle("انتخاب مشتری و شخص رابط");
				alertDialog.setMessage("آیا مشتری و شخص رابط انتخاب گردد؟");
				alertDialog.setButton("بله",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								
								
								
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
		startActivity(intent);
	}

}
