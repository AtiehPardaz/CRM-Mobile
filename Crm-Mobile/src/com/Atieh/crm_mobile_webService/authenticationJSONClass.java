package com.Atieh.crm_mobile_webService;


public class authenticationJSONClass {

	private String Salt;
	private Status Status;
	private String Token;
	
	
	public String getSalt() {
		return Salt;
	}
	public void setSalt(String salt) {
		Salt = salt;
	}
	public Status getStatus() {
		return Status;
	}
	public void setStatus(Status status) {
		Status = status;
	}
	public String getToken() {
		return Token;
	}
	public void setToken(String token) {
		Token = token;
	}

}