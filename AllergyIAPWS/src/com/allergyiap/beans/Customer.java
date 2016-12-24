package com.allergyiap.beans;

public class Customer implements java.io.Serializable {

	private static final long serialVersionUID = 2L;

	private long idcustomer;
	private String user_name;
	private String user_password;
	private String company_name;
	
	
	public Customer(long idcustomer, String userName, String userPassword, String companyName) {
		this.idcustomer = idcustomer;
		this.user_name = userName;
		this.user_password = userPassword;
		this.company_name = companyName;
	}

	public Customer(String userName, String userPassword, String companyName) {
		this.user_name = userName;
		this.user_password = userPassword;
		this.company_name = companyName;
	}

	public Customer() {

	}

	public long getIdcustomer() {
		return idcustomer;
	}

	public void setIdcustomer(long idcustomer) {
		this.idcustomer = idcustomer;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String userName) {
		this.user_name = userName;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String password) {
		this.user_password = password;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String companyName) {
		this.company_name = companyName;
	}

	public boolean isAdmin(){
		return this.user_name.equals("admin");
	}
}