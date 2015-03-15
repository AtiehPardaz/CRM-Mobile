package com.Atieh.crm_mobile;

import java.util.ArrayList;
import java.util.List;

import postCustomersPack.PersonRelation;
import postCustomersPack.PostCustomers;
import postCustomersPack.TemporaryCustomer;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class CustemerSendActivity extends Activity {

	PostCustomers pc = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custemer_send_test);

		pc = new PostCustomers();
		pc.setToken("4");

		PersonRelation pr = new PersonRelation();
		TemporaryCustomer tc = new TemporaryCustomer();

		pr.setCustomerId("a8b36e51-c853-4c4c-85db-d72819705588");
		pr.setId("a8b36e51-c853-4c4c-85db-d72819705588");
		pr.setRelationRoleId("a8b36e51-c853-4c4c-85db-d72819705588");
		pr.setTitle("mojtaba");
		List<PersonRelation> lpr = new ArrayList<PersonRelation>();

		tc.setAddress("asd");
		tc.setDescription("qwer");
		tc.setId("a8b36e51-c853-4c4c-85db-d72819705588");
		tc.setIsLegal(true);
		tc.setPersonRelations(lpr);
		tc.setTel("09122229999");
		tc.setTitle("mojtaba");
		List<TemporaryCustomer> ltc = new ArrayList<TemporaryCustomer>();

		ltc.add(tc);
		pc.setTemporaryCustomers(ltc);
		pc.setTemporaryCustomers(ltc);

		PostMethod pm = new PostMethod(this , pc, "SendTemporaryCustomersPost");
		pm.execute("ss");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.custemer_send_test, menu);
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
}
