package singleTones;

public class authInfo {
	private static authInfo mInstance = null;

	private String Salt;
	private String Token;


	private authInfo(){
		Salt = "";
		Token = "";
	}

	public static authInfo getInstance(){
		if(mInstance == null)
		{
			mInstance = new authInfo();
		}
		return mInstance;
	}

	public String getSalt() {
		return Salt;
	}

	public void setSalt(String salt) {
		Salt = salt;
	}

	public String getToken() {
		return Token;
	}

	public void setToken(String token) {
		Token = token;
	}
}

