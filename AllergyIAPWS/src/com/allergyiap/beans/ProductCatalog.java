package com.allergyiap.beans;

public class ProductCatalog implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private long idproduct_catalog;
	private long allergy_idallergy;
	private long customer_idcustomer;
	private String product_name;
	private String product_description;
	
	private Allergy allergy;
	private Customer customer;

	public ProductCatalog(long id, long allergyIdAllergy, long customerIdCustomer, String productName,
			String productDesctiption) {
		this.idproduct_catalog = id;
		this.allergy_idallergy = allergyIdAllergy;
		this.customer_idcustomer = customerIdCustomer;
		this.product_name = productName;
		this.product_description = productDesctiption;
		this.allergy = new Allergy();
	}

	public ProductCatalog(long allergyIdAllergy, long customerIdCustomer, String productName,
			String productDesctiption) {

		this(0, allergyIdAllergy, customerIdCustomer, productName, productDesctiption);
	}

	public ProductCatalog() {
		this(0, 0, 0, "", "");
	}

	public long getIdproduct_catalog() {
		return this.idproduct_catalog;
	}

	public long getAllergy_idallergy() {
		return this.allergy_idallergy;
	}

	public long getCustomer_idcustomer() {
		return this.customer_idcustomer;
	}

	public String getProduct_name() {
		return this.product_name;
	}

	public String getProduct_description() {
		return this.product_description;
	}

	public static long getSerialversionUId() {
		return serialVersionUID;
	}

	public void getIdproduct_catalog(long id) {
		this.idproduct_catalog = id;
	}

	public void getAllergy_idallergy(long allergyIdAllergy) {
		this.allergy_idallergy = allergyIdAllergy;
	}

	public void getCustomer_idcustomer(long customerIdCustomer) {
		this.customer_idcustomer = customerIdCustomer;
	}

	public void getProductName(String productName) {
		this.product_name = productName;
	}

	public void getProduct_description(String productDescription) {
		this.product_description = productDescription;
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

	public void setAllergy_idallergy(long allergyId) {
		this.allergy_idallergy = allergyId;
	}

	public void setProduct_name(String productName) {
		this.product_name = productName;
	}

	public void setProduct_description(String productDescription) {
		this.product_description = productDescription;
	}
}
