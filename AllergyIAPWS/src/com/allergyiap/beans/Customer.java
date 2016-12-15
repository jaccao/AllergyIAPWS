package com.allergyiap.beans;

public class Customer implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private long idcustomer;
	private String user_name;
	private String user_password;
	private String company_name;
	private String pharmacy_location;
	
	
	public Customer(long id, String userName, String userPassword, String companyName, String pharmacy_location) {
		this.idcustomer = id;
		this.user_name = userName;
		this.user_password = userPassword;
		this.company_name = companyName;
		this.pharmacy_location = pharmacy_location;
	}

	public Customer(String userName, String userPassword, String companyName, String pharmacy_location) {
		this.user_name = userName;
		this.user_password = userPassword;
		this.company_name = companyName;
		this.pharmacy_location = pharmacy_location;
	}

	public Customer() {

	}

	public long getId() {
		return idcustomer;
	}

	public void setId(long id) {
		this.idcustomer = id;
	}

	public String getUserName() {
		return user_name;
	}

	public void setUserName(String userName) {
		this.user_name = userName;
	}

	public String getPassword() {
		return user_password;
	}

	public void setPassword(String password) {
		this.user_password = password;
	}

	public String getCompanyName() {
		return company_name;
	}

	public void setCompanyName(String companyName) {
		this.company_name = companyName;
	}
	
	public String getPharmacy_location() {
		return pharmacy_location;
	}

	public boolean isAdmin(){
		return this.user_name.equals("admin");
	}
}