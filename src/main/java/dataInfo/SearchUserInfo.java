package dataInfo;


public class SearchUserInfo {
	
	private String userName;
	private String userRole;
	private String empNameFull;
	private String empNamePart;
	private String empStatus;
	
	public SearchUserInfo(String userName, String userRole, String empNameFull, String empNamePart,
			String empStatus) {

		this.userName = userName;
		this.userRole = userRole;
		this.empNameFull = empNameFull;
		this.empNamePart = empNamePart;
		this.empStatus = empStatus;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getUserRole() {
		return userRole;
	}
	
	public String getEmpNameFull() {
		return empNameFull;
	}
	
	public String getEmpNamePart() {
		return empNamePart;
	}
	
	public String getEmpStatus() {
		return empStatus;
	}

	@Override
	public String toString() {
		return "UserInfo [userName=" + userName + ", userRole=" + userRole + ", empNameFull=" + empNameFull
				+ ", empNamePart=" + empNamePart + ", empStatus=" + empStatus + "]";
	}
	

}
