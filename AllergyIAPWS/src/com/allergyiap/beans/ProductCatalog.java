package com.allergyiap.beans;

public class ProductCatalog implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private long id;
	private long allergyId;
	private long customerId;
	private String productName;
	private String productDescription;

	private Allergy allergy;
	private Customer customer;

	public ProductCatalog(long id, long allergyIdAllergy, long customerIdCustomer, String productName,
			String productDesctiption) {
		this.id = id;
		this.allergyId = allergyIdAllergy;
		this.customerId = customerIdCustomer;
		this.productName = productName;
		this.productDescription = productDesctiption;
		this.allergy = new Allergy();
	}

	public ProductCatalog(long allergyIdAllergy, long customerIdCustomer, String productName,
			String productDesctiption) {

		this(0, allergyIdAllergy, customerIdCustomer, productName, productDesctiption);
	}

	public ProductCatalog() {
		this(0, 0, 0, "", "");
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
	
	public Allergy getAllergy() {
		return allergy;
	}

	public void setAllergy(Allergy allergy) {
		this.allergy = allergy;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setAllergyId(long allergyId) {
		this.allergyId = allergyId;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
}
