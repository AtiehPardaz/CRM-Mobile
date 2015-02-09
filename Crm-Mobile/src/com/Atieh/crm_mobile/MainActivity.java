package com.Atieh.crm_mobile;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.Atieh.crm_mobile_webService.authServiceInterface;

import android.R.integer;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {

	EditText et_user;
	EditText et_pass;
	Button btn_login;
	CheckBox chk_remember;

	public void initview(){
		et_user=(EditText) findViewById(R.id.et_username);
		et_pass=(EditText) findViewById(R.id.et_password);
		btn_login=(Button) findViewById(R.id.btn_login);
		chk_remember=(CheckBox) findViewById(R.id.chk_remember);
	}

	@Override
	protected void onCreate( Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initview();

		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if(et_user.getText().toString().matches("") && et_pass.getText().toString().matches(""))
					Toast.makeText(MainActivity.this, "نام کاربری و رمز عبور را وارد نمایید", Toast.LENGTH_LONG).show();
				else if(et_user.getText().toString().matches(""))
					Toast.makeText(MainActivity.this, "نام کاربری را وارد نمایید", Toast.LENGTH_LONG).show();
				else if(et_pass.getText().toString().matches(""))
					Toast.makeText(MainActivity.this, "رمز عبور را وارد نمایید", Toast.LENGTH_LONG).show();
				else {
					asyncTask as = new asyncTask();
					as.execute("P");
					
				}
				
				
				//				Intent login=new Intent();
				//				login.setClass(getApplicationContext(), HomeActivity.class);
				//				startActivity(login);
			}
		});
	}

	public void  isConnected()
	{
		
		
	}



	  public class asyncTask extends AsyncTask<String, String, String> {
	
		@Override
		protected String doInBackground(String... arg0) {
			
			
			HttpGet httpRequest = null;
			try {
				 httpRequest = new HttpGet(new URI("http://www.google.com"));
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HttpEntity httpEntity = null;
			HttpClient httpclient = new DefaultHttpClient();
			HttpResponse response = null;
			try {
				 response = httpclient.execute(httpRequest);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int resCode = response.getStatusLine().getStatusCode();
			

			return Integer.toString(resCode);
		}
		  
		@Override
		protected void onPostExecute(String result) {
			Toast.makeText(MainActivity.this,result, Toast.LENGTH_LONG).show();

			
		}
	  }

}
