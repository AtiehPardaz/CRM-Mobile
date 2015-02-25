
package GetTasksPack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Updated {

	private String CustomerId;
	private String Description;
	private String FromDateTime;
	private String Id;
	private Object IsAm;
	private Object ParentActivityId;
	private Object ParentTaskId;
	private Object PersonRelationId;
	private List<ProductsId_> ProductsIds = new ArrayList<ProductsId_>();
	private List<ServicesId_> ServicesIds = new ArrayList<ServicesId_>();
	private Object TemporaryCustomerId;
	private Object TemporaryCustomerPersonRelationsId;
	private String Title;
	private Object ToDateTime;
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
	 * The FromDateTime
	 */
	public String getFromDateTime() {
		return FromDateTime;
	}

	/**
	 * 
	 * @param FromDateTime
	 * The FromDateTime
	 */
	public void setFromDateTime(String FromDateTime) {
		this.FromDateTime = FromDateTime;
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
	 * The IsAm
	 */
	public Object isIsAm() {
		return IsAm;
	}

	/**
	 * 
	 * @param IsAm
	 * The IsAm
	 */
	public void setIsAm(Object IsAm) {
		this.IsAm = IsAm;
	}

	/**
	 * 
	 * @return
	 * The ParentActivityId
	 */
	public Object getParentActivityId() {
		return ParentActivityId;
	}

	/**
	 * 
	 * @param ParentActivityId
	 * The ParentActivityId
	 */
	public void setParentActivityId(Object ParentActivityId) {
		this.ParentActivityId = ParentActivityId;
	}

	/**
	 * 
	 * @return
	 * The ParentTaskId
	 */
	public Object getParentTaskId() {
		return ParentTaskId;
	}

	/**
	 * 
	 * @param ParentTaskId
	 * The ParentTaskId
	 */
	public void setParentTaskId(Object ParentTaskId) {
		this.ParentTaskId = ParentTaskId;
	}

	/**
	 * 
	 * @return
	 * The PersonRelationId
	 */
	public Object getPersonRelationId() {
		return PersonRelationId;
	}

	/**
	 * 
	 * @param PersonRelationId
	 * The PersonRelationId
	 */
	public void setPersonRelationId(Object PersonRelationId) {
		this.PersonRelationId = PersonRelationId;
	}

	/**
	 * 
	 * @return
	 * The ProductsIds
	 */
	public List<ProductsId_> getProductsIds() {
		return ProductsIds;
	}

	/**
	 * 
	 * @param ProductsIds
	 * The ProductsIds
	 */
	public void setProductsIds(List<ProductsId_> ProductsIds) {
		this.ProductsIds = ProductsIds;
	}

	/**
	 * 
	 * @return
	 * The ServicesIds
	 */
	public List<ServicesId_> getServicesIds() {
		return ServicesIds;
	}

	/**
	 * 
	 * @param ServicesIds
	 * The ServicesIds
	 */
	public void setServicesIds(List<ServicesId_> ServicesIds) {
		this.ServicesIds = ServicesIds;
	}

	/**
	 * 
	 * @return
	 * The TemporaryCustomerId
	 */
	public Object getTemporaryCustomerId() {
		return TemporaryCustomerId;
	}

	/**
	 * 
	 * @param TemporaryCustomerId
	 * The TemporaryCustomerId
	 */
	public void setTemporaryCustomerId(Object TemporaryCustomerId) {
		this.TemporaryCustomerId = TemporaryCustomerId;
	}

	/**
	 * 
	 * @return
	 * The TemporaryCustomerPersonRelationsId
	 */
	public Object getTemporaryCustomerPersonRelationsId() {
		return TemporaryCustomerPersonRelationsId;
	}

	/**
	 * 
	 * @param TemporaryCustomerPersonRelationsId
	 * The TemporaryCustomerPersonRelationsId
	 */
	public void setTemporaryCustomerPersonRelationsId(Object TemporaryCustomerPersonRelationsId) {
		this.TemporaryCustomerPersonRelationsId = TemporaryCustomerPersonRelationsId;
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

	/**
	 * 
	 * @return
	 * The ToDateTime
	 */
	public Object getToDateTime() {
		return ToDateTime;
	}

	/**
	 * 
	 * @param ToDateTime
	 * The ToDateTime
	 */
	public void setToDateTime(Object ToDateTime) {
		this.ToDateTime = ToDateTime;
	}

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}

