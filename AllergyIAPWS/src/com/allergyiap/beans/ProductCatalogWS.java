package com.allergyiap.beans;

public class ProductCatalogWS implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private long idproduct_catalog;
	private long allergy_idallergy;
	private long customer_idcustomer;
	private String product_name;
	private String product_description;

	public ProductCatalogWS(ProductCatalog pc) {
		this.idproduct_catalog = pc.getIdproduct_catalog();
		this.allergy_idallergy = pc.getAllergy_idallergy();
		this.customer_idcustomer = pc.getCustomer_idcustomer();
		this.product_name = pc.getProduct_name();
		this.product_description = pc.getProduct_description();
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
