package com.Atieh.crm_mobile;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
					asyncTask as = new asyncTask(); // checking network status
					as.execute("P");
				}


				//				Intent login=new Intent();
				//				login.setClass(getApplicationContext(), HomeActivity.class);
				//				startActivity(login);
			}
		});
	}



	public int netStatus(String url) {

		int resCode;
		if (isNetworkAvailable()) {

			HttpGet httpRequest = null;
			try {
				httpRequest = new HttpGet(new URI(url));
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			
			HttpClient httpclient = new DefaultHttpClient();
			HttpResponse response = null;
			
			try {
				response = httpclient.execute(httpRequest);
				
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			resCode = response.getStatusLine().getStatusCode();
		}

		else {
			resCode = 1000; //our code for no network connected or connecting
		}
		
		return resCode;
	}
	

	public String httpRequestMessage(int responseCode) {
		String message = "";
		switch (responseCode) {
		case 200:
			message = "";
			break;
		case 401:
			message = "عدم دسترسی لازم جهت اتصال به سرور";
			break;
		case 400:
			message = "خطا در برقرای ارتباط. لطفا مجددا تلاش نمایید";
			break;

		case 404:
			message = "وب سایت پذیرنده در دسترس نمی باشد. لطفا در زمانی دیگر تلاش نمایید.";
			break;

		case 1000: //our code for no network connected or connecting
			message = "لطفا از روشن بودن دیتای موبایل و یا وایرلس خود و اتصال به اینترنت اطمینان حاصل نمایید.";
			break;

		default:
			message="خطای ناشناخته شماره :" + Integer.toString(responseCode);
		}

		return message;
	}
	
	
	private boolean isNetworkAvailable() { 
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		else{
			return false;
		}

	}
	
	public class asyncTask extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... arg0) {	

			return httpRequestMessage(netStatus("http://atiehpardaz.com"));
		}

		@Override
		protected void onPostExecute(String result) {
			if (result != "") {
				Toast.makeText(MainActivity.this,result, Toast.LENGTH_LONG).show();
			}
			else
				Toast.makeText(MainActivity.this,"اتصال به سرور برقرار شد", Toast.LENGTH_LONG).show();
		}
	}
	
}
