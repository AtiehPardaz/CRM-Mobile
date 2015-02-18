package GetCustomersPack;

import java.util.HashMap;
import java.util.Map;



public class PersonRelation_ {

	private String CustomerId;
	private String Id;
	private String RelationRoleId;
	private String Title;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return
	 * The CustomerId
	 */
	public String getCustomerId() {
		return CustomerId;
	}

	/**
	 * 
	 * @param CustomerId
	 * The CustomerId
	 */
	public void setCustomerId(String CustomerId) {
		this.CustomerId = CustomerId;
	}

	/**
	 * 
	 * @return
	 * The Id
	 */
	public String getId() {
		return Id;
	}

	/**
	 * 
	 * @param Id
	 * The Id
	 */
	public void setId(String Id) {
		this.Id = Id;
	}

	/**
	 * 
	 * @return
	 * The RelationRoleId
	 */
	public String getRelationRoleId() {
		return RelationRoleId;
	}

	/**
	 * 
	 * @param RelationRoleId
	 * The RelationRoleId
	 */
	public void setRelationRoleId(String RelationRoleId) {
		this.RelationRoleId = RelationRoleId;
	}

	/**
	 * 
	 * @return
	 * The Title
	 */
	public String getTitle() {
		return Title;
	}

	/**
	 * 
	 * @param Title
	 * The Title
	 */
	public void setTitle(String Title) {
		this.Title = Title;
	}

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
