package GetActivitiesPack;

import java.util.HashMap;
import java.util.Map;

public class Updated {

	private String ActivityStatusId;
	private Object CustomerId;
	private String Description;
	private String FromDateTime;
	private boolean HasNextTask;
	private String Id;
	private Object ParentActivityId;
	private Object PersonRelationId;
	private Object TaskId;
	private Object TemporaryCustomerId;
	private Object ToDateTime;
	private Object Title;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
	
	public String getActivityStatusId() {
		return ActivityStatusId;
	}
	public void setActivityStatusId(String activityStatusId) {
		ActivityStatusId = activityStatusId;
	}
	public Object getCustomerId() {
		return CustomerId;
	}
	public void setCustomerId(Object customerId) {
		CustomerId = customerId;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getFromDateTime() {
		return FromDateTime;
	}
	public void setFromDateTime(String fromDateTime) {
		FromDateTime = fromDateTime;
	}
	public boolean isHasNextTask() {
		return HasNextTask;
	}
	public void setHasNextTask(boolean hasNextTask) {
		HasNextTask = hasNextTask;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public Object getParentActivityId() {
		return ParentActivityId;
	}
	public void setParentActivityId(Object parentActivityId) {
		ParentActivityId = parentActivityId;
	}
	public Object getPersonRelationId() {
		return PersonRelationId;
	}
	public void setPersonRelationId(Object personRelationId) {
		PersonRelationId = personRelationId;
	}
	public Object getTaskId() {
		return TaskId;
	}
	public void setTaskId(Object taskId) {
		TaskId = taskId;
	}
	public Object getTemporaryCustomerId() {
		return TemporaryCustomerId;
	}
	public void setTemporaryCustomerId(Object temporaryCustomerId) {
		TemporaryCustomerId = temporaryCustomerId;
	}
	public Object getToDateTime() {
		return ToDateTime;
	}
	public void setToDateTime(Object toDateTime) {
		ToDateTime = toDateTime;
	}
	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}
	public Object getTitle() {
		return Title;
	}
	public void setTitle(Object title) {
		Title = title;
	}
	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}
	

}