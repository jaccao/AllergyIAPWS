package com.allergyiap.beans;

public class ProductCatalog implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private long id;
	private long allergyId;
	private long customerId;
	private String productName;
	private String productDescription;

	public ProductCatalog(long id, long allergyIdAllergy, long customerIdCustomer, String productName,
			String productDesctiption) {
		this.id = id;
		this.allergyId = allergyIdAllergy;
		this.customerId = customerIdCustomer;
		this.productName = productName;
		this.productDescription = productDesctiption;
	}

	public ProductCatalog(long allergyIdAllergy, long customerIdCustomer, String productName,
			String productDesctiption) {

		this.allergyId = allergyIdAllergy;
		this.customerId = customerIdCustomer;
		this.productName = productName;
		this.productDescription = productDesctiption;
	}

	public ProductCatalog() {

	}

	public long getId() {
		return this.id;
	}

	public long getAllergyId() {
		return this.allergyId;
	}

	public long getCustomerId() {
		return this.customerId;
	}

	public String getProductName() {
		return this.productName;
	}

	public String getProductDescription() {
		return this.productDescription;
	}

	public static long getSerialversionUId() {
		return serialVersionUID;
	}

	public void getId(long id) {
		this.id = id;
	}

	public void getAllergyId(long allergyIdAllergy) {
		this.allergyId = allergyIdAllergy;
	}

	public void getCustomerId(long customerIdCustomer) {
		this.customerId = customerIdCustomer;
	}

	public void getProductName(String productName) {
		this.productName = productName;
	}

	public void getProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
}
