package com.Atieh.crm_mobile;

import GetActivitiesPack.GetActivities;
import GetActivitiesPack.GetActivitiesInterface;
import GetActivityStatusPack.GetActivityStatus;
import GetActivityStatusPack.GetActivityStatusInterface;
import GetCustomersPack.GetCustomers;
import GetCustomersPack.GetCustomersInterface;
import GetProductAndServicespack.GetProductAndServicesInterface;
import GetProductAndServicespack.GetProductsAndServices;
import GetRelationRolesPack.GetRelationRoles;
import GetRelationRolesPack.GetRelationRolesInterface;
import GetTasksPack.GetTasks;
import GetTasksPack.GetTasksInterface;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.Atieh.crm_mobile_calendar.CivilDate;
import com.Atieh.crm_mobile_calendar.DateConverter;
import com.Atieh.crm_mobile_calendar.PersianDate;
import com.Atieh.crm_mobile_webService.ServiceGenerator;

import dataBase.database;

public class HomeActivity extends Activity {

	Button btnmounthview;
	Button btnproductservises;
	Button btnNewTask;
	Button btnNewActivity,btn_yearview,download;
	public static ProgressBar progressBar1;
	
	
	Button btnDayView;

	public void initview() {
		btnmounthview = (Button) findViewById(R.id.btn_monthview);
		btnproductservises = (Button) findViewById(R.id.btn_productservises);
		btnNewTask = (Button) findViewById(R.id.btn_newtask);
		btnNewActivity = (Button) findViewById(R.id.btn_newactivity);
		btnDayView = (Button) findViewById(R.id.btn_dayview);
		btn_yearview = (Button) findViewById(R.id.btn_yearview);
		download = (Button) findViewById(R.id.btn_download);
		progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
	}

	database db;

	public static final String baseURL = "http://webservice.atiehpardaz.com/CrmService/CrmService.svc";
	public static final String EnterCustomersListStat = "EnterCustomersListStat";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		initview();
	
		progressBar1.setVisibility(View.GONE);
		
		HomeWatcher mHomeWatcher = new HomeWatcher(this);
		mHomeWatcher.setOnHomePressedListener(new OnHomePressedListener() {
		    @Override
		    public void onHomePressed() {
		    	
		    	Intent intent = new Intent();
		    	intent.setClass(HomeActivity.this, MainActivity.class);
		    	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

		    	startActivity(intent);
		    	System.exit(0);		    }
		    @Override
		    public void onHomeLongPressed() {
		    }
		});
		mHomeWatcher.startWatch();
		
		download.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

//				db = new database(HomeActivity.this);
//				asyncGetProductAndService gps = new asyncGetProductAndService();
//				gps.execute("4");
				
			}
		});
		
		btn_yearview.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				progressBar1.setVisibility(View.VISIBLE);
				Intent intent = new Intent();
				intent.setClass(HomeActivity.this, YearViewActivity.class);
				startActivity(intent);
			}
		});
		
		
		btnDayView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				PersianDate persianDate = DateConverter.civilToPersian(new CivilDate());
				Intent intent=new Intent();
				intent.setClass(v.getContext(), TaskAndActivityActionActivity.class);
				intent.putExtra("date", persianDate.getYear()+":" + persianDate.getMonth()+ ":" + +persianDate.getDayOfMonth());
				intent.putExtra("parent", "home");
				startActivity(intent);
			}
		});
		
		btnmounthview.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(HomeActivity.this,
						com.Atieh.crm_mobile_calendar.MainActivity.class);
				startActivity(intent);

			}
		});
		btnproductservises.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				startActivity(new Intent(HomeActivity.this,
						ProductServisesActivity.class));

			}
		});

		Button btn = (Button) findViewById(R.id.btn_customer);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent CustomersList = new Intent();
				CustomersList.putExtra(EnterCustomersListStat, "mostaghim");
				CustomersList.setClass(getApplicationContext(),
						CustomerListActivity.class);
				startActivity(CustomersList);
			}
		});
		
		btnNewTask.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				Intent sendTask = new Intent();
				sendTask.setClass(getApplicationContext(),
						NewTaskActivity.class);
				startActivity(sendTask);				
			}
		});
		
		btnNewActivity.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				Intent sendActivity = new Intent();
				sendActivity.setClass(getApplicationContext(),
						NewActivitiesActivity.class);
				startActivity(sendActivity);				
			}
		});

		

	}

	public class asyncGetProductAndService extends
	AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... arg0) {

			return updateAll("4");
		}

		@Override
		protected void onPostExecute(String result) {
			//Toast.makeText(HomeActivity.this, result, Toast.LENGTH_LONG).show();
		}

	}

	public String updateAll(String token) {

		GetProductsAndServices PandSs = null;
		db.database();
		db.open();

		// get products and swervices
		GetProductAndServicesInterface PS = ServiceGenerator.createService(
				GetProductAndServicesInterface.class, baseURL);
		PandSs = new GetProductsAndServices();

		try {
			PandSs = PS.getps(token);

		} catch (Exception e) {
			Toast.makeText(HomeActivity.this, "ERROR ON WEBSERVICE",
					Toast.LENGTH_LONG).show();
		}

		// GetCustomers
		GetCustomersInterface CustomersAdapter = ServiceGenerator
				.createService(GetCustomersInterface.class, baseURL);
		GetCustomers customers = new GetCustomers();
		try {
			customers = CustomersAdapter.getCustomers(token);

		} catch (Exception e) {
			Toast.makeText(HomeActivity.this, "ERROR ON WEBSERVICE",
					Toast.LENGTH_LONG).show();
		}

		// GetRelationsRoles
		GetRelationRolesInterface RelationsAdapter = ServiceGenerator
				.createService(GetRelationRolesInterface.class, baseURL);
		GetRelationRoles relations = RelationsAdapter.getRelationRoles(token);

		// getActivityStatus
		GetActivityStatusInterface ActivityStatusAdapter = ServiceGenerator
				.createService(GetActivityStatusInterface.class, baseURL);
		GetActivityStatus activitystatus = new GetActivityStatus();
		activitystatus = ActivityStatusAdapter.getActivityStatus(token);

		//GetTasks
		GetTasksInterface TasksAdapter = ServiceGenerator
				.createService(GetTasksInterface.class, baseURL);
		GetTasks gettask = new GetTasks();
		gettask = TasksAdapter.gettasks(token);


		//Get Activities
		GetActivitiesInterface ActivitiesAdapter = ServiceGenerator
				.createService(GetActivitiesInterface.class, baseURL);
		GetActivities activities = new GetActivities();
		activities = ActivitiesAdapter.getActivities(token);


		
		// insert products
//		if (PandSs.getProducts().getInserted() != null) {
//			for (int i = 0; i < PandSs.getProducts().getInserted().size(); i++) {
//
//				db.InsertProduct(PandSs.getProducts().getInserted().get(i)
//						.getId(), PandSs.getProducts().getInserted().get(i)
//						.getName(), 0);
//			}
//		}
//
//		// insert services
//		if (PandSs.getServices().getInserted() != null) {
//			for (int i = 0; i < PandSs.getServices().getInserted().size(); i++) {
//
//				db.InsertService(PandSs.getServices().getInserted().get(i)
//						.getId(), PandSs.getServices().getInserted().get(i)
//						.getName(), 0);
//			}
//		}
//
//		// update products
//		if (PandSs.getProducts().getUpdated() != null) {
//			for (int i = 0; i < PandSs.getProducts().getUpdated().size(); i++) {
//
//				db.UpdateProduct(PandSs.getProducts().getUpdated().get(i)
//						.getId(), PandSs.getProducts().getUpdated().get(i)
//						.getName(), 0);
//			}
//		}
//
//		// update services
//		if (PandSs.getServices().getUpdated() != null) {
//			for (int i = 0; i < PandSs.getServices().getUpdated().size(); i++) {
//
//				db.UpdateService(PandSs.getServices().getUpdated().get(i)
//						.getId(), PandSs.getServices().getUpdated().get(i)
//						.getName(), 0);
//			}
//		}
//
//		// delete products
//		if (PandSs.getProducts().getDeleted() != null) {
//			for (int i = 0; i < PandSs.getProducts().getDeleted().size(); i++) {
//
//				db.DeleteProduct(PandSs.getProducts().getDeleted().get(i)
//						.getId());
//			}
//		}
//
//		// delete services
//		if (PandSs.getServices().getDeleted() != null) {
//			for (int i = 0; i < PandSs.getServices().getDeleted().size(); i++) {
//
//				db.DeleteService(PandSs.getServices().getDeleted().get(i)
//						.getId());
//			}
//		}
//
//		// insert customer
//		if (customers.getInserted() != null) {
//
//			for (int i = 0; i < customers.getInserted().size(); i++) {
//
//				db.InsertCustomer(customers.getInserted().get(i).getId(),
//						customers.getInserted().get(i).getTitle(), customers
//						.getInserted().get(i).getDescription(),
//						customers.getInserted().get(i).isIsLegal() ? 1 : 0,
//								customers.getInserted().get(i).getAddress(), customers
//								.getInserted().get(i).getTel());
//				if (customers.getInserted().get(i).getPersonRelations() != null) {
//					for (int j = 0; j < customers.getInserted().get(i)
//							.getPersonRelations().size(); j++) {
//						db.InsertPersonRelations(customers.getInserted().get(i)
//								.getPersonRelations().get(j).getCustomerId(),
//								customers.getInserted().get(i)
//								.getPersonRelations().get(j).getId(),
//								customers.getInserted().get(i)
//								.getPersonRelations().get(j)
//								.getRelationRoleId(), customers
//								.getInserted().get(i)
//								.getPersonRelations().get(j).getTitle());
//					}
//
//				}
//			}
//
//		}
//
//		// update customers
//		if (customers.getUpdated() != null) {
//
//			for (int i = 0; i < customers.getUpdated().size(); i++) {
//
//				db.UpdateCustomer(customers.getUpdated().get(i).getId(),
//						customers.getUpdated().get(i).getTitle(), customers
//						.getUpdated().get(i).getDescription(),
//						customers.getUpdated().get(i).isIsLegal() ? 1 : 0,
//								customers.getUpdated().get(i).getAddress(), customers
//								.getUpdated().get(i).getTel());
//
//				if (customers.getUpdated().get(i).getPersonRelations() != null) {
//					for (int j = 0; j < customers.getUpdated().get(i)
//							.getPersonRelations().size(); j++) {
//						db.UpdatePersonRelations(customers.getUpdated().get(i)
//								.getPersonRelations().get(j).getCustomerId(),
//								customers.getUpdated().get(i)
//								.getPersonRelations().get(j).getId(),
//								customers.getUpdated().get(i)
//								.getPersonRelations().get(j)
//								.getRelationRoleId(), customers
//								.getUpdated().get(i)
//								.getPersonRelations().get(j).getTitle());
//					}
//
//				}
//			}
//
//		}
//
//		// DeletedPersonRelation
//		if (customers.getDeletedPersonRelation() != null) {
//			for (int i = 0; i < customers.getDeletedPersonRelation().size(); i++) {
//				db.DeletePersonRelations(customers.getDeletedPersonRelation()
//						.get(i).getCustomerId(), customers
//						.getDeletedPersonRelation().get(i).getId());
//			}
//		}
//
//		// delete Customers
//		if (customers.getDeleted() != null) {
//			for (int i = 0; i < customers.getDeleted().size(); i++) {
//				db.DeleteCustomer(customers.getDeleted().get(i).getId());
//			}
//
//		}
//
//		// insert RelationRoles
//		if (relations.getInserted() != null) {
//			for (int i = 0; i < relations.getInserted().size(); i++) {
//				db.InsertRelationRoles(relations.getInserted().get(i).getId(),
//						relations.getInserted().get(i).getTitle());
//			}
//		}
//
//		// update RelationRoles
//		if (relations.getUpdated() != null) {
//			for (int i = 0; i < relations.getUpdated().size(); i++) {
//				db.UpdateRelationRoles(relations.getUpdated().get(i).getId(),
//						relations.getUpdated().get(i).getTitle());
//			}
//		}
//
//		// delete relationRoles
//		if (relations.getDeleted() != null) {
//			for (int i = 0; i < relations.getDeleted().size(); i++) {
//				db.DeleteRelationRoles(relations.getDeleted().get(i).getId());
//			}
//		}
//
//		// insert activitystatus
//		if (activitystatus.getInserted() != null) {
//			for (int i = 0; i < activitystatus.getInserted().size(); i++) {
//				db.InsertActivityStatus(activitystatus.getInserted().get(i)
//						.getId(), activitystatus.getInserted().get(i)
//						.getTitle());
//			}
//		}
//
//		// update activitystatus
//		if (activitystatus.getUpdated() != null) {
//			for (int i = 0; i < activitystatus.getUpdated().size(); i++) {
//				db.UpdateActivityStatus(activitystatus.getUpdated().get(i)
//						.getId(), activitystatus.getUpdated().get(i).getTitle());
//			}
//		}
//
//		// delete activitystatus
//		if (activitystatus.getDeleted() != null) {
//			for (int i = 0; i < activitystatus.getDeleted().size(); i++) {
//				db.DeleteActivityStatus(activitystatus.getDeleted().get(i)
//						.getId());
//			}
//		}


//		//insert Tasks
//		if (gettask.getInserted() != null) {
//			for (int i = 0; i < gettask.getInserted().size(); i++) {
//				db.InsertTasks(gettask.getInserted().get(i).getId(),
//						gettask.getInserted().get(i).getCustomerId(),
//						gettask.getInserted().get(i).getDescription(), 
//						gettask.getInserted().get(i).getFromDateTime(),
//						gettask.getInserted().get(i).isIsAm() == null ? "null":gettask.getInserted().get(i).isIsAm().toString(),
//								gettask.getInserted().get(i).getParentActivityId() == null ? "": gettask.getInserted().get(i).getParentActivityId().toString(),
//										gettask.getInserted().get(i).getParentTaskId()== null ? "": gettask.getInserted().get(i).getParentTaskId().toString() , 
//												gettask.getInserted().get(i).getPersonRelationId()== null ? "": gettask.getInserted().get(i).getPersonRelationId().toString(),
//														gettask.getInserted().get(i).getTemporaryCustomerId()== null ? "": gettask.getInserted().get(i).getTemporaryCustomerId().toString(), 
//																gettask.getInserted().get(i).getTemporaryCustomerPersonRelationsId()== null ? "": gettask.getInserted().get(i).getTemporaryCustomerPersonRelationsId().toString(),
//																		gettask.getInserted().get(i).getTitle(), 
//																		gettask.getInserted().get(i).getToDateTime()== null ? "": gettask.getInserted().get(i).getToDateTime().toString() );
//
//				if(gettask.getInserted().get(i).getProductsIds() != null){
//
//					for (int j = 0; j < gettask.getInserted().get(i).getProductsIds().size(); j++) {
//
//						db.InsertTasksProducts(gettask.getInserted().get(i).getId(),
//								gettask.getInserted().get(i).getProductsIds().get(j).getId());	
//					}
//				}
//
//				if(gettask.getInserted().get(i).getServicesIds() != null){
//
//					for (int j = 0; j < gettask.getInserted().get(i).getServicesIds().size(); j++) {
//
//						db.InsertTasksServices(gettask.getInserted().get(i).getId(),
//								gettask.getInserted().get(i).getServicesIds().get(j).getId());	
//					}
//				}
//
//
//			}
//
//		}
//
//
//
//		//update Tasks
//		if (gettask.getUpdated() != null) {
//			for (int i = 0; i < gettask.getUpdated().size(); i++) {
//				db.UpdateTasks(gettask.getUpdated().get(i).getId(),
//						gettask.getUpdated().get(i).getCustomerId(),
//						gettask.getUpdated().get(i).getDescription(), 
//						gettask.getUpdated().get(i).getFromDateTime(),
//						gettask.getInserted().get(i).isIsAm() == null ? "null":gettask.getInserted().get(i).isIsAm().toString(),
//								gettask.getUpdated().get(i).getParentActivityId() == null ? "": gettask.getUpdated().get(i).getParentActivityId().toString(),
//										gettask.getUpdated().get(i).getParentTaskId()== null ? "": gettask.getUpdated().get(i).getParentTaskId().toString() , 
//												gettask.getUpdated().get(i).getPersonRelationId()== null ? "": gettask.getUpdated().get(i).getPersonRelationId().toString(),
//														gettask.getUpdated().get(i).getTemporaryCustomerId()== null ? "": gettask.getUpdated().get(i).getTemporaryCustomerId().toString(), 
//																gettask.getUpdated().get(i).getTemporaryCustomerPersonRelationsId()== null ? "": gettask.getUpdated().get(i).getTemporaryCustomerPersonRelationsId().toString(),
//																		gettask.getUpdated().get(i).getTitle(), 
//																		gettask.getUpdated().get(i).getToDateTime()== null ? "": gettask.getUpdated().get(i).getToDateTime().toString() );
//
//				if(gettask.getUpdated().get(i).getProductsIds() != null){
//
//					for (int j = 0; j < gettask.getUpdated().get(i).getProductsIds().size(); j++) {
//
//						db.InsertTasksProducts(gettask.getUpdated().get(i).getId(),
//								gettask.getUpdated().get(i).getProductsIds().get(j).getId());	
//					}
//				}
//
//				if(gettask.getUpdated().get(i).getServicesIds() != null){
//
//					for (int j = 0; j < gettask.getUpdated().get(i).getServicesIds().size(); j++) {
//
//						db.InsertTasksServices(gettask.getUpdated().get(i).getId(),
//								gettask.getUpdated().get(i).getServicesIds().get(j).getId());	
//					}
//				}
//
//			}
//		}
//
//
//		//delete Tasks
//		if (gettask.getDeleted() != null) {
//			for (int i = 0; i < gettask.getDeleted().size(); i++) {
//				db.DeleteTasks(gettask.getDeleted().get(i).getId());
//			}
//		}
//

//		//insert activities
//		if(activities.getInserted() != null){
//			for (int i = 0; i < activities.getInserted().size(); i++) {
//				db.InsertActivities(activities.getInserted().get(i).getId(),
//						activities.getInserted().get(i).getTitle()== null ? "": activities.getInserted().get(i).getTitle().toString(),
//						activities.getInserted().get(i).getActivityStatusId(),
//						activities.getInserted().get(i).getCustomerId()== null ? "": activities.getInserted().get(i).getCustomerId().toString(),
//						activities.getInserted().get(i).getDescription(),
//						activities.getInserted().get(i).getFromDateTime(),
//						activities.getInserted().get(i).isHasNextTask() ? "1" : "0",
//						activities.getInserted().get(i).getParentActivityId()== null ? "": activities.getInserted().get(i).getParentActivityId().toString(), 
//						activities.getInserted().get(i).getPersonRelationId()== null ? "": activities.getInserted().get(i).getPersonRelationId().toString(), 
//						activities.getInserted().get(i).getTaskId()== null ? "": activities.getInserted().get(i).getTaskId().toString(),
//						activities.getInserted().get(i).getTemporaryCustomerId()== null ? "": activities.getInserted().get(i).getTemporaryCustomerId().toString(), 
//						activities.getInserted().get(i).getToDateTime()== null ? "": activities.getInserted().get(i).getToDateTime().toString()
//								);
//				
//			}
//		}
//		
//		//update activities
//		if(activities.getUpdated() != null){
//			for (int i = 0; i < activities.getUpdated().size(); i++) {
//				db.UpdateActivities(activities.getUpdated().get(i).getId(),
//						activities.getInserted().get(i).getTitle()== null ? "": activities.getInserted().get(i).getTitle().toString(),
//						activities.getUpdated().get(i).getActivityStatusId(),
//						activities.getUpdated().get(i).getCustomerId()== null ? "": activities.getUpdated().get(i).getCustomerId().toString(),
//						activities.getUpdated().get(i).getDescription(),
//						activities.getUpdated().get(i).getFromDateTime(),
//						activities.getUpdated().get(i).isHasNextTask() ? "1" : "0",
//						activities.getUpdated().get(i).getParentActivityId()== null ? "": activities.getUpdated().get(i).getParentActivityId().toString(), 
//						activities.getUpdated().get(i).getPersonRelationId()== null ? "": activities.getUpdated().get(i).getPersonRelationId().toString(), 
//						activities.getUpdated().get(i).getTaskId()== null ? "": activities.getUpdated().get(i).getTaskId().toString(),
//						activities.getUpdated().get(i).getTemporaryCustomerId()== null ? "": activities.getUpdated().get(i).getTemporaryCustomerId().toString(), 
//						activities.getUpdated().get(i).getToDateTime()== null ? "": activities.getUpdated().get(i).getToDateTime().toString()
//								);
//				
//			}
//		}
//			
//		//delete activities
//		if(activities.getDeleted() != null){
//			for (int i = 0; i < activities.getUpdated().size(); i++) {
//				db.DeleteActivities(activities.getDeleted().get(i).getId());
//				
//			}
//		}


		
		db.close();

		return "succeed";
	}
	
@Override
public void onBackPressed() {
	
	Intent intent = new Intent();
	intent.setClass(HomeActivity.this, MainActivity.class);
	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
	startActivity(intent);
}

}
