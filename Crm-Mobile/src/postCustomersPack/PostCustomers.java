package postCustomersPack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostCustomers {

private String token;
private List<LocalActivity> localActivities = new ArrayList<LocalActivity>();

/**
* 
* @return
* The token
*/
public String getToken() {
return token;
}

/**
* 
* @param token
* The token
*/
public void setToken(String token) {
this.token = token;
}

/**
* 
* @return
* The localActivities
*/
public List<LocalActivity> getLocalActivities() {
return localActivities;
}

/**
* 
* @param localActivities
* The localActivities
*/
public void setLocalActivities(List<LocalActivity> localActivities) {
this.localActivities = localActivities;
}


}