package com.allergyiap.beans;

public class CustomerWS implements java.io.Serializable{
	private static final long serialVersionUID = 2L;

	private long idcustomer;
	private String company_name;
	
	public CustomerWS(Customer customer){
		this.idcustomer = customer.getIdcustomer();
		this.company_name = customer.getCompany_name();
	}

	public CustomerWS() {

	}

	public long getIdcustomer() {
		return idcustomer;
	}

	public void setIdcustomer(long idcustomer) {
		this.idcustomer = idcustomer;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String companyName) {
		this.company_name = companyName;
	}
	

}
