package JsonClasses;

public class Users implements java.io.Serializable
{
	
	private static final long serialVersionUId = 1L;
	private String overallId = "logIn";
	private String email;
	private String password;
	private boolean isAdmin;
	private boolean isActive;
	
	public Users(int userId, String email, String password, boolean isAdmin, boolean, isActive) {
		
	}
	
	public boolean isActive() {
		return isActive;
	}
	public void setAuthenticated(boolean isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}
	
	public String getOverallId() {
		return overallId;
	}
	public void setOverallId(String overallId) {
		this.overallId = overallId;
	}
	public String getAuthUserEmail() {
		return email;
	}
	public void setAuthUserEmail(String email) {
		this.email = email;
	}
	public String getAuthUserPassword() {
		return password;
	}
	public void setAuthUserPassword(String userName) {
		this.password = password;
	}
	public boolean getAuthUserIsAdmin() {
		return isAdmin;
	}
	public void setAuthUserIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

}