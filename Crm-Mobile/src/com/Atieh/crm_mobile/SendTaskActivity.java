package com.Atieh.crm_mobile;

import java.util.ArrayList;
import java.util.List;

import com.Atieh.crm_mobile_webService.PostMethod;

import GetTasksPack.Inserted;
import GetTasksPack.ProductsId;
import GetTasksPack.ServicesId;
import PostTasksPack.PostTasks;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class SendTaskActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_task);

		HomeWatcher mHomeWatcher = new HomeWatcher(this);
		mHomeWatcher.setOnHomePressedListener(new OnHomePressedListener() {
		    @Override
		    public void onHomePressed() {
		       Intent intent = new Intent();intent.setClass(getApplicationContext(), MainActivity.class);startActivity(intent);System.exit(0);
		    }
		    @Override
		    public void onHomeLongPressed() {
		    }
		});
		mHomeWatcher.startWatch();
		
		
		PostTasks pt = new PostTasks();
		List<GetTasksPack.Inserted> tasksList = new ArrayList<GetTasksPack.Inserted>();
		
		GetTasksPack.Inserted task = new Inserted();
		GetTasksPack.ProductsId ProductsIds = new ProductsId();
		GetTasksPack.ServicesId ServicesIds = new ServicesId();
		List<GetTasksPack.ProductsId> ProductsIdsList = new ArrayList<ProductsId>();
		List<GetTasksPack.ServicesId> ServicesIdsList = new ArrayList<ServicesId>();
		
		ProductsIds.setId("a8b36e51-c853-4c4c-85db-d72819705588");
		ServicesIds.setId("a8b36e51-c853-4c4c-85db-d72819705588");
		ProductsIdsList.add(ProductsIds);
		ServicesIdsList.add(ServicesIds);
		
		task.setCustomerId("a8b36e51-c853-4c4c-85db-d72819705588");
		task.setDescription("       ");
		task.setFromDateTime("1393:12:20 10:20");
		task.setId("a8b36e51-c853-4c4c-85db-d72819705588");
		task.setIsAm(null);
		task.setParentActivityId("a8b36e51-c853-4c4c-85db-d72819705588");
		task.setParentTaskId("a8b36e51-c853-4c4c-85db-d72819705588");
		task.setPersonRelationId("a8b36e51-c853-4c4c-85db-d72819705588");
		task.setProductsIds(ProductsIdsList);
		task.setServicesIds(ServicesIdsList);
		task.setTemporaryCustomerId("a8b36e51-c853-4c4c-85db-d72819705588");
		task.setTemporaryCustomerPersonRelationsId("a8b36e51-c853-4c4c-85db-d72819705588");
		task.setTitle("title");
		task.setToDateTime("1393:12:20 10:20");
		
		tasksList.add(task);
		pt.setLocalTasks(tasksList);
		pt.setToken("4");
		
		
		
		PostMethod sendTasks = new PostMethod(this, pt, "SendTaskPost");
		sendTasks.execute(" ");
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.send_task, menu);
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
