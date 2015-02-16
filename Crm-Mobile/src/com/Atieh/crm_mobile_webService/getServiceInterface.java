package com.Atieh.crm_mobile_webService;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

public interface getServiceInterface {
	@GET("/GetServices")
	public List<getServiceClass> get(@Query("token") String token  );

}
