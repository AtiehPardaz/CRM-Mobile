package com.Atieh.crm_mobile;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import singleTones.authInfo;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.Atieh.crm_mobile_webService.ServiceGenerator;
import com.Atieh.crm_mobile_webService.authServiceInterface;
import com.Atieh.crm_mobile_webService.authenticationJSONClass;


public class MainActivity extends Activity {

	EditText et_user;
	EditText et_pass;
	Button btn_login;
	CheckBox chk_remember;
	//ProgressBar progbar_progress;

	public void initview(){
		et_user=(EditText) findViewById(R.id.et_username);
		et_pass=(EditText) findViewById(R.id.et_password);
		btn_login=(Button) findViewById(R.id.btn_login);
		//progbar_progress = (ProgressBar) findViewById(R.id.progressBar1);
		//progbar_progress.setVisibility(View.INVISIBLE);

	}

	@Override
	protected void onCreate( Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initview();

		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				if(et_user.getText().toString().matches("") || et_pass.getText().toString().matches(""))
					Toast.makeText(MainActivity.this, "وجود کادر خالی غیر مجاز است", Toast.LENGTH_LONG).show();

				else {

					asyncTask as = new asyncTask(); // checking network status
					as.execute("P");

				}
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
		protected void onPreExecute() {
			//progbar_progress.setVisibility(View.VISIBLE);

		}

		@Override
		protected String doInBackground(String... arg0) {	

			return httpRequestMessage(netStatus("http://atiehpardaz.com"));
		}

		@Override
		protected void onPostExecute(String result) {

			//progbar_progress.setVisibility(View.INVISIBLE);

			if (result != "") {
				Toast.makeText(MainActivity.this,result, Toast.LENGTH_SHORT).show();
			}
			else{
				Toast.makeText(MainActivity.this," اتصال به سرور برقرار شد", Toast.LENGTH_SHORT).show();
				asyncAuthentication asyncauthentication = new asyncAuthentication();
				asyncauthentication.execute("P"); 
			}
		}
	}


	public class asyncAuthentication extends AsyncTask<String, String, Integer> {
		authenticationJSONClass authe;
		@Override
		protected Integer doInBackground(String... arg0) {

			authServiceInterface auth = ServiceGenerator.createService(authServiceInterface.class, "http://webservice.atiehpardaz.com/CrmService/CrmService.svc");
			Map<String, String> querymap = new HashMap<>();
			querymap.put("userName", et_user.getText().toString());
			querymap.put("password", et_pass.getText().toString());
			querymap.put("cultureId", "1065");
			querymap.put("deviceName", android.os.Build.MODEL);

			authe = new authenticationJSONClass();

			authe  = auth.authorize(querymap);

			authInfo.getInstance().setSalt(authe.getSalt());
			authInfo.getInstance().setToken(authe.getToken());
			
			return authe.getStatus().getCode();
		}

		@Override
		protected void onPostExecute(Integer result) {
			if(result == 1){
				Toast.makeText(MainActivity.this, " عملیات با موفقیت انجام شد", Toast.LENGTH_SHORT).show();	
				Intent login=new Intent();
				login.setClass(getApplicationContext(), HomeActivity.class);
				startActivity(login);
			}
			
			else {
				Toast.makeText(MainActivity.this, authe.getStatus().getMessageDetails(), Toast.LENGTH_LONG).show();
			}
		}

	}

}


