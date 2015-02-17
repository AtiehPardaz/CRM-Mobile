package GetCustomersPack;

import retrofit.http.GET;
import retrofit.http.Query;

public interface GetCustomersInterface {
	
	@GET("/GetCustomers")
	public GetCustomers getCustomers(@Query("token") String token);
}
