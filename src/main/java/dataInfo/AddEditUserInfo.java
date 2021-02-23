package dataInfo;

public class AddEditUserInfo extends SearchUserInfo {
	
	private String password;
	private String cpassword;
	
	public AddEditUserInfo(String username, String userRole, String empNameFull,
				String empNamePart, String status, String password, String cpassword) {
		super(username, userRole, empNameFull, empNamePart, status);
		this.password = password;
		this.cpassword = cpassword;
	}


	public String getPassword() {
		return password;
	}

	public String getCpassword() {
		return cpassword;
	}

	@Override
	public String toString() {
		return super.toString().replace("]", "")+" password=" + password + ", cpassword=" + cpassword + "]";
	}
	
	
}
