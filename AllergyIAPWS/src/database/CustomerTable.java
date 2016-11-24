package database;

public class CustomerTable implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final String tableName = "customer";

	private long id;
	private String userName;
	private String userPassword;
	private String companyName;
	
	
	public CustomerTable(long id, String userName, String userPassword, String companyName){
		this.id = id;
		this.userName = userName;
		this.userPassword = userPassword;
		this.companyName = companyName;
	}
	
	public long getId(){
		return this.id;
	}
	
	public String getUserName(){
		return this.userName;
	}
	
	public String getUserPassword(){
		return this.userPassword;
	}
	
	public String getCompanyName(){
		return this.companyName;
	}
	
	public static long getSerialversionUId() {
		return serialVersionUID;
	}
	
	public void setgetId(long id){
		this.id = id;
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	public void setUserPassword(String userPassword){
		this.userPassword = userPassword;
	}
	
	public void setCompanyName(String companyName){
		this.companyName = companyName;
	}
	
}