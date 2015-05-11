package GetActivityStatusPack;

import retrofit.http.GET;
import retrofit.http.Query;

public interface GetActivityStatusInterface {

	@GET("/GetActivityStatus")
	public GetActivityStatus getActivityStatus(@Query("token") String token);
}
