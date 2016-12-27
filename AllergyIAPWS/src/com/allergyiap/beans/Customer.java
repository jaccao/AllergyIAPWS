package com.allergyiap.beans;

public class Customer implements java.io.Serializable {

	private static final long serialVersionUID = 2L;

	private long idcustomer;
	private String user_mail;
	private String user_password;
	private String company_name;
	
	
	public Customer(long idcustomer, String userMail, String userPassword, String companyName) {
		this.idcustomer = idcustomer;
		this.user_mail = userMail;
		this.user_password = userPassword;
		this.company_name = companyName;
	}

	public Customer(String userMail, String userPassword, String companyName) {
		this.user_mail = userMail;
		this.user_password = userPassword;
		this.company_name = companyName;
	}

	public Customer() {}

	public long getIdcustomer() {
		return idcustomer;
	}

	public void setIdcustomer(long idcustomer) {
		this.idcustomer = idcustomer;
	}

	public String getUser_mail() {
		return user_mail;
	}

	public void setUser_mail(String userMail) {
		this.user_mail = userMail;
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
		return this.user_mail.equals("admin");
	}
}