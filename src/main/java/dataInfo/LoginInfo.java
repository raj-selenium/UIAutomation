package dataInfo;


public class LoginInfo {
	

	private String username;
	private String password;
	private String expected;
	
	
	public LoginInfo(String username, String password, String expected) {
		this.username = username;
		this.password = password;
		this.expected = expected;
	}


	public String getUsername() {
		return username;
	}


	public String getPassword() {
		return password;
	}


	public String getExpected() {
		return expected;
	}
		
	
	@Override
	public String toString() {
		return "LoginInfo [username=" + username + ", password=" + password + ", expected=" + expected + "]";
	}
}
