package com.Atieh.crm_mobile_webService;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Query;

public interface getProductInterface {
	@GET("/GetProducts")
	public List<getProductClass> get(@Query("token") String token);

}
