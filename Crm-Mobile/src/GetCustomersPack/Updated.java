

package GetCustomersPack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Updated {

	private String Address;
	private String Description;
	private String Id;
	private boolean IsLegal;
	private List<PersonRelation_> PersonRelations = new ArrayList<PersonRelation_>();
	private String Tel;
	private String Title;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return
	 * The Address
	 */
	public String getAddress() {
		return Address;
	}

	/**
	 * 
	 * @param Address
	 * The Address
	 */
	public void setAddress(String Address) {
		this.Address = Address;
	}

	/**
	 * 
	 * @return
	 * The Description
	 */
	public String getDescription() {
		return Description;
	}

	/**
	 * 
	 * @param Description
	 * The Description
	 */
	public void setDescription(String Description) {
		this.Description = Description;
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
	 * The IsLegal
	 */
	public boolean isIsLegal() {
		return IsLegal;
	}

	/**
	 * 
	 * @param IsLegal
	 * The IsLegal
	 */
	public void setIsLegal(boolean IsLegal) {
		this.IsLegal = IsLegal;
	}

	/**
	 * 
	 * @return
	 * The PersonRelations
	 */
	public List<PersonRelation_> getPersonRelations() {
		return PersonRelations;
	}

	/**
	 * 
	 * @param PersonRelations
	 * The PersonRelations
	 */
	public void setPersonRelations(List<PersonRelation_> PersonRelations) {
		this.PersonRelations = PersonRelations;
	}

	/**
	 * 
	 * @return
	 * The Tel
	 */
	public String getTel() {
		return Tel;
	}

	/**
	 * 
	 * @param Tel
	 * The Tel
	 */
	public void setTel(String Tel) {
		this.Tel = Tel;
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
