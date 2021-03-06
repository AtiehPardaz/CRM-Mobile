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
import android.content.SharedPreferences;

import android.graphics.Typeface;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

import android.widget.LinearLayout;

import android.widget.ProgressBar;

import android.widget.Toast;

import com.Atieh.crm_mobile_webService.ServiceGenerator;
import com.Atieh.crm_mobile_webService.authJSONClass;
import com.Atieh.crm_mobile_webService.authServiceInterface;

public class MainActivity extends Activity {

	EditText et_user;
	EditText et_pass;
	Button btn_login;
	CheckBox chk_remember;
	SharedPreferences prefs;
	LinearLayout linear1;

	ProgressBar progbar_progress;

	public void initview() {
		et_user = (EditText) findViewById(R.id.et_username);
		et_pass = (EditText) findViewById(R.id.et_password);
		btn_login = (Button) findViewById(R.id.btn_login);
		chk_remember = (CheckBox) findViewById(R.id.chk_remember);
		progbar_progress = (ProgressBar) findViewById(R.id.progressBar1);
		progbar_progress.setVisibility(View.INVISIBLE);
		linear1 = (LinearLayout) findViewById(R.id.linearpb);
		linear1.setVisibility(View.INVISIBLE);
	}

	// ProgressBar progbar_progress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		prefs = new adapters.ObscuredSharedPreferences(this,
				this.getSharedPreferences("pref", Context.MODE_PRIVATE));
//		prefs.edit().putString("ua", "mojtaba")
//		.commit();
		
		String us = prefs.getString("ua", null);
		String pa = prefs.getString("pa", null);

		
		
		if (!isNetworkAvailable()) {
			if (us != null && pa != null) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, HomeActivity.class);
				startActivity(intent);
			}
		}

		initview();

		Typeface font_nazanin = Typeface.createFromAsset(getAssets(),
				"nazanin.ttf");

		// et_user.setTypeface(font_nazanin);
		// et_pass.setTypeface(font_nazanin);
		chk_remember.setTypeface(font_nazanin);
		btn_login.setTypeface(font_nazanin);
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (et_user.getText().toString().matches("")
						&& et_pass.getText().toString().matches("")) {
					Toast.makeText(MainActivity.this,
							"نام کاربری و رمز عبور را وارد نمایید",
							Toast.LENGTH_LONG).show();

				} else if (et_pass.getText().toString().matches("")) {
					Toast.makeText(MainActivity.this,
							"رمز عبور را وارد نمایید", Toast.LENGTH_LONG)
							.show();
				} else if (et_user.getText().toString().matches("")) {
					Toast.makeText(MainActivity.this,
							"نام کاربری راوارد نمایید", Toast.LENGTH_LONG)
							.show();
				} else {

					// asyncTask as = new asyncTask(); // checking network
					// // status
					// as.execute("P");

					startActivity(new Intent(MainActivity.this,
							HomeActivity.class));

				}
			}
		});

	

		// chk_remember.setOnCheckedChangeListener(new OnCheckedChangeListener()
		// {
		//
		// @Override
		// public void onCheckedChanged(CompoundButton buttonView, boolean
		// isChecked) {
		// if (isChecked) {
		//
		// prefs.edit().putString("us","123q").commit();
		// String rt = prefs.getString("ua", null);
		// rt = ""
		// }
		//
		// }
		// });
		et_pass.setGravity(Gravity.RIGHT);
		et_pass.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (et_pass.getText().length() == 0) {
					et_pass.setGravity(Gravity.RIGHT);
				} else {
					et_pass.setGravity(Gravity.LEFT);
				}

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

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
			resCode = 1000; // our code for no network connected or connecting
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
			
		case 407:
			message = "کاربر با پروکسی اجازه دسترسی ندارد.";
			break;
			


		case 1000: // our code for no network connected or connecting
			message = "لطفا از روشن بودن دیتای موبایل و یا وایرلس خود و اتصال به اینترنت اطمینان حاصل نمایید.";
			break;

		default:
			message = "خطای ناشناخته شماره :" + Integer.toString(responseCode);
		}

		return message;
	}

	private boolean isNetworkAvailable() {

		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		} else {
			return false;
		}

	}

	public class asyncTask extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {

			progbar_progress.setVisibility(View.VISIBLE);
			linear1.setVisibility(View.VISIBLE);
		}

		@Override
		protected String doInBackground(String... arg0) {

			// progbar_progress.setVisibility(View.VISIBLE);
			return httpRequestMessage(netStatus("http://atiehpardaz.com/default.aspx?lng=fa"));
		}

		@Override
		protected void onPostExecute(String result) {

			progbar_progress.setVisibility(View.INVISIBLE);
			linear1.setVisibility(View.INVISIBLE);
			if (result != "") {
				Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT)
						.show();
			} else {
				Toast.makeText(MainActivity.this, " اتصال به سرور برقرار شد",
						Toast.LENGTH_SHORT).show();
				asyncAuthentication asyncauthentication = new asyncAuthentication();
				asyncauthentication.execute("P");

			}
		}
	}

	public class asyncAuthentication extends AsyncTask<String, String, Integer> {

		authJSONClass authe;

		@Override
		protected Integer doInBackground(String... arg0) {

			authServiceInterface auth = null;

			auth = ServiceGenerator
					.createService(authServiceInterface.class,
							"http://webservice.atiehpardaz.com/CrmService/CrmService.svc");

			if (auth == null) {

				Toast.makeText(MainActivity.this,
						"در حال حاضر سرور پاسخگو نمی باشد.", Toast.LENGTH_SHORT)
						.show();
			}

			else {
				Map<String, String> querymap = new HashMap<>();

				querymap.put("userName", et_user.getText().toString());
				querymap.put("password", et_pass.getText().toString());
				querymap.put("cultureId", "1065"); // MUST BE CORRECT
				querymap.put("deviceName", android.os.Build.MODEL);

				authe = new authJSONClass();

				try {

					authe = auth.authorize(querymap);
				} catch (Exception e) {
					Toast.makeText(MainActivity.this,
							"اتصال به سروربرقرار نیست", Toast.LENGTH_SHORT)
							.show();

				}

				authInfo.getInstance().setSalt(authe.getSalt());
				authInfo.getInstance().setToken(authe.getToken());

			}

			return authe.getStatus().getCode();
		}

		@Override
		protected void onPostExecute(Integer result) {

			if (result == 1) {
				// Toast.makeText(MainActivity.this,
				// " عملیات با موفقیت انجام شد",
				// Toast.LENGTH_SHORT).show();

				if (chk_remember.isChecked()) {
					prefs.edit().putString("us", et_user.getText().toString())
							.commit();
					prefs.edit().putString("pa", et_pass.getText().toString())
							.commit();

				}

				Intent login = new Intent();
				login.setClass(getApplicationContext(), HomeActivity.class);
				startActivity(login);
			}

			else {
				Toast.makeText(MainActivity.this,
						authe.getStatus().getMessageDetails(),
						Toast.LENGTH_LONG).show();
			}
		}

	}

	@Override
	public void onBackPressed() {
//		// Intent intent = getIntent();
//		// startActivity(intent);
//		finish();
//		System.exit(0);
	}

}
