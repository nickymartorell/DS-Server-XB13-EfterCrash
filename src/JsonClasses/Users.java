package JsonClasses;

public class Users implements java.io.Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String overallId = "logIn";
	private int userId;
	private String email;
	private String password;
	private boolean isAdmin;
	private boolean isActive;
	
	public Users() {	
	}
	
	public Users(int userId, String email, String password, boolean isAdmin, boolean isActive) {
		super();
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.isAdmin = isAdmin;
		this.isActive = isActive;			
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public boolean getIsActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public boolean getIsAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}	
	public String getOverallId() {
		return overallId;
	}
	public void setOverallId(String overallId) {
		this.overallId = overallId;
	}
}