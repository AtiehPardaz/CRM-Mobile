package postCustomersPack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostCustomers {

	private List<TemporaryCustomer> temporaryCustomers = new ArrayList<TemporaryCustomer>();
	private String token;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The temporaryCustomers
	 */
	public List<TemporaryCustomer> getTemporaryCustomers() {
		return temporaryCustomers;
	}

	/**
	 * 
	 * @param temporaryCustomers
	 *            The temporaryCustomers
	 */
	public void setTemporaryCustomers(List<TemporaryCustomer> temporaryCustomers) {
		this.temporaryCustomers = temporaryCustomers;
	}

	/**
	 * 
	 * @return The token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * 
	 * @param token
	 *            The token
	 */
	public void setToken(String token) {
		this.token = token;
	}

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}