package dataBase;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class database extends SQLiteOpenHelper {


	public final String path="data/data/com.Atieh.crm_mobile/databases/";
	public final String Name="db";
	public SQLiteDatabase mydb;

	private final Context mycontext;

	public database(Context context) {
		super(context, "db", null, 1);
		mycontext=context;

	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	public void database(){

		boolean checkdb=checkdb();

		if(checkdb){

		}else{

			this.getReadableDatabase();

			try{
				copydatabase();
			}catch(IOException e){

			}			
		}		
	}

	public void open(){

		mydb=SQLiteDatabase.openDatabase(path+Name, null, SQLiteDatabase.OPEN_READWRITE);

	}

	public void close(){
		mydb.close();
	}

	public boolean checkdb(){

		SQLiteDatabase db=null;
		try{	
			db=SQLiteDatabase.openDatabase(path+Name, null, SQLiteDatabase.OPEN_READONLY);
		}
		catch(SQLException e)
		{

		}
		return db !=null ? true:false ;

	}

	public void copydatabase() throws IOException{
		OutputStream myOutput = new FileOutputStream(path+Name);
		byte[] buffer = new byte[1024];
		int length;

		InputStream myInput = mycontext.getAssets().open("db");
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}
		myInput.close();
		myOutput.flush();
		myOutput.close();
	}
	

	public long InsertProduct (String productGUID ,String productName, int isDeleted){//address choose

		ContentValues values = new ContentValues();
		values.put("productGUID", productGUID);
		values.put("productName", productName);
		values.put("isDeleted", isDeleted);
		long ID = mydb.insert("products", null, values);
		return ID ;
	}
	
	public long UpdateProduct (String productGUID ,String productName, int isDeleted){//address choose

		String strFilter = "productGUID =" +"'"+ productGUID+"'";
		ContentValues values = new ContentValues();
		values.put("productGUID", productGUID);
		values.put("productName", productName);
		values.put("isDeleted", isDeleted);
		long ID = mydb.update("products",values,strFilter,null);
		return ID ;
	}
	
	public long DeleteProduct (String productGUID){

		String strFilter = "productGUID =" +"'"+ productGUID+"'";
		ContentValues values = new ContentValues();
		values.put("isDeleted", 1);
		long ID = mydb.update("products",values,strFilter,null);
		return ID ;
	}

	
	
	public long InsertService (String ServiceGUID ,String ServiceName, int isDeleted){//address choose

		ContentValues values = new ContentValues();
		values.put("serviceGUID", ServiceGUID);
		values.put("serviceName", ServiceName);
		values.put("isDeleted", isDeleted);
		long ID = mydb.insert("services", null, values);
		return ID ;
	}
	
	public long UpdateService (String ServiceGUID ,String ServiceName, int isDeleted){//address choose

		String strFilter = "serviceGUID =" +"'"+ ServiceGUID+"'";
		ContentValues values = new ContentValues();
		values.put("serviceGUID", ServiceGUID);
		values.put("serviceName", ServiceName);
		values.put("isDeleted", isDeleted);
		long ID = mydb.update("services", values, strFilter,null);
		return ID ;
	}
	
	public long DeleteService (String ServiceGUID){

		String strFilter = "serviceGUID =" +"'"+ ServiceGUID+"'";
		ContentValues values = new ContentValues();
	
		values.put("isDeleted", 1);
		long ID = mydb.update("services", values, strFilter,null);
		return ID ;
	}

	
	
	public void InsertCustomer(String Id,String Title , String Description , int IsLegal,String Address,String Tel){
		
		ContentValues values = new ContentValues();
		values.put("Id", Id);
		values.put("Title",Title );
		values.put("Description", Description);
		values.put("IsLegal", IsLegal);
		values.put("Address",Address );
		values.put("Tel", Tel);
		values.put("IsDeleted", 0);
		mydb.insert("custemers", null, values);
	}
	
	public void UpdateCustomer(String Id,String Title , String Description , int IsLegal,String Address,String Tel){
		
		String strFilter = "Id =" +"'"+ Id+"'";
		ContentValues values = new ContentValues();
		values.put("Title",Title );
		values.put("Description", Description);
		values.put("IsLegal", IsLegal);
		values.put("Address",Address );
		values.put("Tel", Tel);
		values.put("IsDeleted", 0);
		long ID = mydb.update("custemers", values, strFilter,null);

	}

	public void DeleteCustomer(String Id){
		
		String strFilter = "Id =" +"'"+ Id+"'";
		ContentValues values = new ContentValues();
		values.put("IsDeleted", 1);
		long ID = mydb.update("custemers", values, strFilter,null);

	}

	
	
	public void InsertPersonRelations(String CustomerId,String Id,String RelationRoleId,String Title){
		ContentValues values = new ContentValues();
		values.put("CustomerId", CustomerId);
		values.put("Id", Id);
		values.put("RelationRoleId", RelationRoleId);
		values.put("Title", Title);
		values.put("IsDeleted", 0);
		mydb.insert("personRelations", null, values);
	}

	public void UpdatePersonRelations(String CustomerId,String Id,String RelationRoleId,String Title){
		
		String strFilter = "CustomerId =" +"'"+ CustomerId+"' and Id ="+"'"+ Id+"'";
		ContentValues values = new ContentValues();
		values.put("RelationRoleId", RelationRoleId);
		values.put("Title", Title);
		values.put("IsDeleted", 0);
		long ID = mydb.update("personRelations", values, strFilter,null);

	}

	public void DeletePersonRelations(String CustomerId,String Id){
		
		String strFilter = "CustomerId = '"+CustomerId+"' AND Id ='"+Id+"'";
		ContentValues values = new ContentValues();
		values.put("IsDeleted", 1);
		long ID = mydb.update("personRelations", values, strFilter,null);

	}

	
	
	public void InsertRelationRoles(String Id,String Title){
		ContentValues values = new ContentValues();
		values.put("Id", Id);
		values.put("Title", Title);
		values.put("IsDeleted", 0);
		mydb.insert("RelationRoles", null, values);
	}
	
	public void UpdateRelationRoles(String Id,String Title){
		
		String strFilter = "Id = '"+Id+"'";
		ContentValues values = new ContentValues();
		values.put("Title", Title);
		values.put("IsDeleted", 0);
		long ID = mydb.update("RelationRoles", values, strFilter,null);
	}
	
	public void DeleteRelationRoles(String Id){
		
		String strFilter = "Id = '"+Id+"'";
		ContentValues values = new ContentValues();
		values.put("IsDeleted", 1);
		long ID = mydb.update("RelationRoles", values, strFilter,null);
	}
	
	
	
	public void InsertActivityStatus(String Id,String Title){
		ContentValues values = new ContentValues();
		values.put("Id", Id);
		values.put("Title", Title);
		values.put("IsDeleted", 0);
		mydb.insert("activityStatus", null, values);
	}
	
	public void UpdateActivityStatus(String Id,String Title){
		
		String strFilter = "Id = '"+Id+"'";
		ContentValues values = new ContentValues();
		values.put("Title", Title);
		values.put("IsDeleted", 0);
		long ID = mydb.update("activityStatus", values, strFilter,null);
	}
	
	public void DeleteActivityStatus(String Id){
		
		String strFilter = "Id = '"+Id+"'";
		ContentValues values = new ContentValues();
		values.put("IsDeleted", 1);
		long ID = mydb.update("activityStatus", values, strFilter,null);
	}


}