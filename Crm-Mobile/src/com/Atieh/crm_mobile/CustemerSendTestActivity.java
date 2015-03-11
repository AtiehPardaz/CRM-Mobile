package com.Atieh.crm_mobile;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import postCustomersPack.LocalActivity;
import postCustomersPack.PostCustomers;
import postMethods.PostTempCustomer;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import GetCustomersPack.Inserted;
import GetCustomersPack.PersonRelation;
import GetCustomersPack.sendCustomer;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.Atieh.crm_mobile_webService.ServiceGenerator;
import com.google.gson.Gson;

public class CustemerSendTestActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custemer_send_test);
		
		String  uniqueID = UUID.randomUUID().toString(); 

		//PostTempCustomer post = ServiceGenerator.createService(PostTempCustomer.class, HomeActivity.baseURL);

		
		
//		Map<String, String> query = new HashMap<>();
//		query.put("id", UUID.randomUUID().toString());
//		query.put("title", "کارمند");
//		query.put("address", "اشرفی اصفهانی");
//		query.put("tel", "09153356025");
//		query.put("description", "");
//		query.put("isLegal", "true");
//		query.put("relatedPersonTitle", "آقی کشوری");
//		query.put("relationRoleId", UUID.randomUUID().toString());
//		query.put("token", "4");

		
		GetCustomersPack.Inserted insertCustomer = new GetCustomersPack.Inserted();
		
		insertCustomer.setAddress("اشرفی اصفهانی");
		insertCustomer.setDescription( "");
		insertCustomer.setId(UUID.randomUUID().toString());
		insertCustomer.setIsLegal(true);
		insertCustomer.setTel("09153356025");
		insertCustomer.setTitle("آقی کشوری");
		
		PostCustomers pc = new PostCustomers();


		
		LocalActivity la = new LocalActivity();
		
		la.setActivityStatusId("test");
		la.setCustomerId("test");
		la.setDescription("test");
		la.setFromDateTime("test");
		la.setHasNextTask(true);
		la.setId("test");
		la.setParentActivityId("test");
		la.setPersonRelationId("test");
		la.setTaskId("test");
		la.setTemporaryCustomerId("test");
		la.setTitle("test");
		la.setToDateTime("test");
		
		List<LocalActivity> lla = new ArrayList<LocalActivity>();
		lla.add(la);
//
		pc.setToken("4");
		pc.setLocalActivities(lla);
		
		Toast.makeText(CustemerSendTestActivity.this,convert(pc), Toast.LENGTH_LONG).show();

		RestAdapter restAdapter = new RestAdapter.Builder()
	    .setEndpoint(HomeActivity.baseURL)
	    .build();

	PostTempCustomer service = restAdapter.create(PostTempCustomer.class);
	
	service.postCustomer(pc, new Callback<String>() {
		
		@Override
		public void success(String arg0, Response arg1) {
			// TODO Auto-generated method stub
			Toast.makeText(CustemerSendTestActivity.this,arg0.toString(), Toast.LENGTH_LONG).show();

		}
		
		@Override
		public void failure(RetrofitError arg0) {
			// TODO Auto-generated method stub
			
		}
	});
	
//		post.postCustomer( pc , new Callback<String>() {
//
//			@Override
//			public void failure(RetrofitError arg0) {
//				// TODO Auto-generated method stub
//				Toast.makeText(CustemerSendTestActivity.this,arg0.toString(), Toast.LENGTH_LONG).show();
//
//			}
//
//			@Override
//			public void success(String arg0, Response arg1) {
//				// TODO Auto-generated method stub
//				Toast.makeText(CustemerSendTestActivity.this, arg0, Toast.LENGTH_LONG).show();
//
//			}
//
//		
//
//		});

//		post.postCustomer(new Callback<String>() {
//
//			@Override
//			public void failure(RetrofitError arg0) {
//				// TODO Auto-generated method stub
//				Toast.makeText(CustemerSendTestActivity.this, "Mmm", Toast.LENGTH_LONG).show();
//
//			}
//
//			@Override
//			public void success(String arg0, Response arg1) {
//				// TODO Auto-generated method stub
//				Toast.makeText(CustemerSendTestActivity.this, arg0, Toast.LENGTH_LONG).show();
//
//			}
//
//		
//
//	
//		});


		
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
	
	public String  convert(PostCustomers pc) {
		
		Gson gson = new Gson();

		String jsonRepresentation = gson.toJson(pc);
		
		return jsonRepresentation;
	}
}
