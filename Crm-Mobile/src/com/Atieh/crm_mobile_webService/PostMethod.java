package com.Atieh.crm_mobile_webService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

import com.Atieh.crm_mobile.HomeActivity;
import com.google.gson.Gson;

public class PostMethod extends AsyncTask<String, String, String> {
	
	Object object;
	String url;
	String postResult;
	Activity activity;
	
	public PostMethod(){
	}
	

	public PostMethod (Activity a , Object obj , String URL) {
		this.object = obj;
		this.url = URL;
		this.activity = a;
	}

		@Override
		protected String doInBackground(String... arg0) {

			BufferedReader reader = null;

			String uri = HomeActivity.baseURL+"/" + url;
			URL url = null;
			try {
				url = new URL(uri);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HttpURLConnection con = null;
			try {
				con = (HttpURLConnection) url.openConnection();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				con.setRequestMethod("POST");
			} catch (ProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con.setDoOutput(true);
			con.setRequestProperty("Content-Type", "application/json");
			OutputStreamWriter writer = null;
			try {
				writer = new OutputStreamWriter(con.getOutputStream());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				writer.write(convert(object));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				writer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			StringBuilder sb = new StringBuilder();
			try {
				reader = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String line;

			try {
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return sb.toString();
		}

		@Override
		protected void onPostExecute(String result) {
			
			Toast.makeText(activity, result, 1).show();
			postResult = result;
		}

		public String convert(Object pc) {

			Gson gson = new Gson();

			String jsonRepresentation = gson.toJson(pc);

			return jsonRepresentation;
		}


		public String getPostResult() {
			return postResult;
		}


		public void setPostResult(String postResult) {
			this.postResult = postResult;
		}
	
}
