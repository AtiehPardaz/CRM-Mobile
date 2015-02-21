package com.Atieh.crm_mobile;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import postMethods.PostTempCustomer;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import com.Atieh.crm_mobile_webService.ServiceGenerator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class CustemerSendTestActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custemer_send_test);
		
		String  uniqueID = UUID.randomUUID().toString(); 

		PostTempCustomer post = ServiceGenerator.createService(PostTempCustomer.class, HomeActivity.baseURL);
		Map<String, String> query = new HashMap<>();
		query.put("id", UUID.randomUUID().toString());
		query.put("title", "کارمند");
		query.put("address", "اشرفی اصفهانی");
		query.put("tel", "09153356025");
		query.put("description", "");
		query.put("isLegal", "true");
		query.put("relatedPersonTitle", "آقی کشوری");
		query.put("relationRoleId", UUID.randomUUID().toString());
		query.put("token", "4");
		
		post.postCustomer(query, new Callback<String>() {
			
			@Override
			public void success(String arg0, Response arg1) {
				Toast.makeText(CustemerSendTestActivity.this, arg0, Toast.LENGTH_LONG).show();
				
			}
			
			@Override
			public void failure(RetrofitError arg0) {
				Toast.makeText(CustemerSendTestActivity.this, arg0.getCause().getMessage(), Toast.LENGTH_LONG).show();
				
			}
		});


		
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
