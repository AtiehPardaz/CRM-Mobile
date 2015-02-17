package GetCustomersPack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetCustomers {

	private List<GetCustomersPack.Deleted> Deleted = new ArrayList<GetCustomersPack.Deleted>();
	private List<GetCustomersPack.Inserted> Inserted = new ArrayList<GetCustomersPack.Inserted>();
	private List<GetCustomersPack.Updated> Updated = new ArrayList<GetCustomersPack.Updated>();
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return
	 * The Deleted
	 */
	public List<GetCustomersPack.Deleted> getDeleted() {
		return Deleted;
	}

	/**
	 * 
	 * @param Deleted
	 * The Deleted
	 */
	public void setDeleted(List<GetCustomersPack.Deleted> Deleted) {
		this.Deleted = Deleted;
	}

	/**
	 * 
	 * @return
	 * The Inserted
	 */
	public List<GetCustomersPack.Inserted> getInserted() {
		return Inserted;
	}

	/**
	 * 
	 * @param Inserted
	 * The Inserted
	 */
	public void setInserted(List<GetCustomersPack.Inserted> Inserted) {
		this.Inserted = Inserted;
	}

	/**
	 * 
	 * @return
	 * The Updated
	 */
	public List<GetCustomersPack.Updated> getUpdated() {
		return Updated;
	}

	/**
	 * 
	 * @param Updated
	 * The Updated
	 */
	public void setUpdated(List<GetCustomersPack.Updated> Updated) {
		this.Updated = Updated;
	}

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}