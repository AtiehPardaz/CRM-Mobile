package GetActivitiesPack;

import retrofit.http.GET;
import retrofit.http.Query;

public interface GetActivitiesInterface {

	@GET("/GetActivities")
	public GetActivities getActivities(@Query("token") String token);
	
}
