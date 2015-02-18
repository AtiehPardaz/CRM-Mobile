package GetCustomersPack;

import java.util.HashMap;
import java.util.Map;

public class DeletedPersonRelation {
	
	private String CustomerId;
	private String Id;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	
	public String getCustomerId() {
		return CustomerId;
	}
	public void setCustomerId(String customerId) {
		CustomerId = customerId;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}
	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}

}
