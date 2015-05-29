package com.Atieh.crm_mobile;

import java.util.ArrayList;
import java.util.List;

import com.Atieh.crm_mobile_webService.PostMethod;
import PostActivityPack.PostActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class SendActivityActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_activity);
		
		HomeWatcher mHomeWatcher = new HomeWatcher(this);
		mHomeWatcher.setOnHomePressedListener(new OnHomePressedListener() {
		    @Override
		    public void onHomePressed() {
		       Intent intent = new Intent();intent.setClass(getApplicationContext(), MainActivity.class); intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);startActivity(intent);System.exit(0);
		    }
		    @Override
		    public void onHomeLongPressed() {
		    }
		});
		mHomeWatcher.startWatch();
		
		PostActivity postActivity = new PostActivity();
		List<GetActivitiesPack.Inserted> localActivities = new ArrayList<GetActivitiesPack.Inserted>();
		GetActivitiesPack.Inserted anActivity = new GetActivitiesPack.Inserted();
		
		anActivity.setActivityStatusId("a8b36e51-c853-4c4c-85db-d72819705588");
		anActivity.setCustomerId("a8b36e51-c853-4c4c-85db-d72819705588");
		anActivity.setDescription("");
		anActivity.setFromDateTime("1393:12:20 10:20");
		anActivity.setHasNextTask(true);
		anActivity.setId("a8b36e51-c853-4c4c-85db-d72819705588");
		anActivity.setParentActivityId("a8b36e51-c853-4c4c-85db-d72819705588");
		anActivity.setPersonRelationId("a8b36e51-c853-4c4c-85db-d72819705588");
		anActivity.setTaskId("a8b36e51-c853-4c4c-85db-d72819705588");
		anActivity.setTemporaryCustomerId("a8b36e51-c853-4c4c-85db-d72819705588");
		anActivity.setToDateTime("1393:12:20 10:20");
		anActivity.setTitle("");
		
		localActivities.add(anActivity);
		
		postActivity.setLocalActivities(localActivities);
		postActivity.setToken("4");
		
		PostMethod sendActivity = new PostMethod(this, postActivity, "SendActivityPost");
		sendActivity.execute("a");

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.send_activity, menu);
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
