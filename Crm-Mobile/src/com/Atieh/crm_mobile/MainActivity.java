package com.Atieh.crm_mobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
	String user;
	String pass;
	boolean flgchk_remember;

	public void initview() {
		et_user = (EditText) findViewById(R.id.et_username);
		et_pass = (EditText) findViewById(R.id.et_password);
		btn_login = (Button) findViewById(R.id.btn_login);
		chk_remember = (CheckBox) findViewById(R.id.chk_remember);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initview();

		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				user = et_user.getText().toString();
				pass = et_pass.getText().toString();
				if (chk_remember.isChecked() == true) {
					flgchk_remember = true;

				} else {
					flgchk_remember = false;
				}
				if (user.equals("") && pass.equals("")) {
					Toast.makeText(getApplicationContext(),
							"لطفا نام کاربری و کلمه عبور  را وارد نمایید",
							Toast.LENGTH_LONG).show();
				} else if (pass.equals("")) {
					Toast.makeText(getApplicationContext(),
							"لطفا  کلمه عبور را وارد نمایید", Toast.LENGTH_LONG)
							.show();
				} else if (user.equals("")) {
					Toast.makeText(getApplicationContext(),
							"لطفا نام کاربری را وارد نمایید", Toast.LENGTH_LONG)
							.show();
				} else {
					// vorod be home activity -
					// motaghayer haye user & pass havi name karbari va password
					// mibashand
					// flgchk_remmember baraye yadavari
					Toast.makeText(getApplicationContext(),
							"remmember=" + flgchk_remember + "", 1).show();
					Intent login = new Intent();
					login.setClass(getApplicationContext(), HomeActivity.class);
					startActivity(login);
				}
			}

		});
	}
}
