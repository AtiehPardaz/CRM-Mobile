package GetRelationRolesPack;

import retrofit.http.GET;
import retrofit.http.Query;

public interface GetRelationRolesInterface {
	
	@GET("/GetRelationRoles")
	public GetRelationRoles getRelationRoles(@Query("token") String token);
}
