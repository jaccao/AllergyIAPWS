package com.allergyiap.beans;

public class Customer implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String userName;
	private String password;
	private String companyName;
	private String pharmacy_location;
	
	

	public Customer(long id, String userName, String userPassword, String companyName, String pharmacy_location) {
		this.id = id;
		this.userName = userName;
		this.password = userPassword;
		this.companyName = companyName;
		this.pharmacy_location = pharmacy_location;
	}

	public Customer(String userName, String userPassword, String companyName, String pharmacy_location) {
		this.userName = userName;
		this.password = userPassword;
		this.companyName = companyName;
		this.pharmacy_location = pharmacy_location;
	}

	public Customer() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getPharmacy_location() {
		return pharmacy_location;
	}

	public boolean isAdmin(){
		return this.userName.equals("admin");
	}
}