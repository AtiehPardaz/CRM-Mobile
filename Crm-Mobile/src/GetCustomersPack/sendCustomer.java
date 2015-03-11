package GetCustomersPack;

import java.util.List;

public class sendCustomer {

	String token ;
	List<Inserted> temporaryCustomers;

	public List<Inserted> getInserted() {
		return temporaryCustomers;
	}

	public void setInserted(List<Inserted> inserted) {
		this.temporaryCustomers = inserted;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
