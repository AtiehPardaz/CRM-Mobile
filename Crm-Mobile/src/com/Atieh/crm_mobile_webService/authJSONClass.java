package com.Atieh.crm_mobile_webService;
public class authJSONClass {

	private String Salt;
	private authStatus Status;
	private String Token;
	
	
	public String getSalt() {
		return Salt;
	}
	public void setSalt(String salt) {
		Salt = salt;
	}
	public authStatus getStatus() {
		return Status;
	}
	public void setStatus(authStatus status) {
		Status = status;
	}
	public String getToken() {
		return Token;
	}
	public void setToken(String token) {
		Token = token;
	}

}