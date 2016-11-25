package database;

import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.ResultSet;

public class ProductCatalogTable implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private static final String tableName = "product_catalog";

	private long id;
	private long allergyId;
	private long customerId;
	private String productName;
	private String productDescription;

	public ProductCatalogTable(long id, long allergyIdAllergy, long customerIdCustomer, String productName,
			String productDesctiption) {
		this.id = id;
		this.allergyId = allergyIdAllergy;
		this.customerId = customerIdCustomer;
		this.productName = productName;
		this.productDescription = productDesctiption;
	}

	public ProductCatalogTable(long allergyIdAllergy, long customerIdCustomer, String productName,
			String productDesctiption) {

		this.allergyId = allergyIdAllergy;
		this.customerId = customerIdCustomer;
		this.productName = productName;
		this.productDescription = productDesctiption;
	}

	public ProductCatalogTable() {

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

	public void insertNewProduct() {
		DB db = DB.getInstance();
		String insertQuery = "INSERT INTO " + tableName + "(allergy_idallergy, customer_idcustomer,"
				+ "product_name, product_description ) VALUES (" + allergyId + "," + customerId + ",'" + productName
				+ "','" + productDescription + "');";

		System.out.println(insertQuery);

		db.executeUpdate(insertQuery);
	}

	public void deleteProduct() {
		DB db = DB.getInstance();
		String deleteQuery = "DELETE FROM " + tableName + " WHERE idproduct_catalog = " + id;
		db.executeUpdate(deleteQuery);
	}

	public void updateProduct() {
		DB db = DB.getInstance();
		String updateQuery = "UPDATE " + tableName + " set allergy_idallergy = " + allergyId
				+ ", customer_idcustomer = " + customerId + "," + "product_name = '" + productName
				+ "', product_description = '" + productDescription + "' WHERE idproduct_catalog = " + id;
		db.executeUpdate(updateQuery);
	}

	/* Print all the products in the catalog for all customers in the moment */
	public static List<ProductCatalogTable> getAllProductCatalogs() {
		List<ProductCatalogTable> prodcatalogs = new ArrayList<>();
		try {
			DB db = DB.getInstance();
			String selectQuery = "SELECT * FROM " + tableName + ";";
			ResultSet rs = db.execute(selectQuery);
			while (rs.next()) {
				prodcatalogs.add(new ProductCatalogTable(rs.getLong("idproduct_catalog"),
						rs.getLong("allergy_idallergy"), rs.getLong("customer_idcustomer"),
						rs.getString("product_name"), rs.getString("product_description")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prodcatalogs;
	}

	/* From a customerID, select all his products in the catalog */
	public static List<ProductCatalogTable> getProductCatalogByCustomer(long id) {
		List<ProductCatalogTable> prodcatalogs = new ArrayList<>();
		try {
			DB db = DB.getInstance();
			String selectQuery = "SELECT * FROM " + tableName + " WHERE customer_idcustomer = " + id + ";";
			ResultSet rs = db.execute(selectQuery);
			while (rs.next()) {
				prodcatalogs.add(new ProductCatalogTable(rs.getLong("idproduct_catalog"),
						rs.getLong("allergy_idallergy"), rs.getLong("customer_idcustomer"),
						rs.getString("product_name"), rs.getString("product_description")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prodcatalogs;
	}

	public static ProductCatalogTable getProductCatalog(long id) {
		ProductCatalogTable prodcatalogs = new ProductCatalogTable();
		try {
			DB db = DB.getInstance();
			String selectQuery = "SELECT * FROM " + tableName + " WHERE idproduct_catalog = " + id + ";";
			ResultSet rs = db.execute(selectQuery);
			while (rs.next()) {
				prodcatalogs = new ProductCatalogTable(rs.getLong("idproduct_catalog"), rs.getLong("allergy_idallergy"),
						rs.getLong("customer_idcustomer"), rs.getString("product_name"),
						rs.getString("product_description"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prodcatalogs;
	}
}
