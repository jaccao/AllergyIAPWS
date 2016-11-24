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
	private long allergyIdAllergy;
	private long customerIdCustomer;
	private String productName;
	private String productDescription;
	
	
	public ProductCatalogTable(long id, long allergyIdAllergy, long customerIdCustomer,
								String productName, String productDesctiption){
		this.id = id;
		this.allergyIdAllergy = allergyIdAllergy;
		this.customerIdCustomer = customerIdCustomer;
		this.productName = productName;
		this.productDescription = productDesctiption;
	}
	
	public long getId(){
		return this.id;
	}
	
	public long getAllergyId(){
		return this.allergyIdAllergy;
	}
	
	public long getCustomerId(){
		return this.customerIdCustomer;
	}
	
	public String getProductName(){
		return this.productName;
	}
	
	public String getProductDescription(){
		return this.productDescription;
	}
	
	public static long getSerialversionUId() {
		return serialVersionUID;
	}
	
	public void getId(long id){
		this.id = id;
	}
	
	public void getAllergyId(long allergyIdAllergy){
		this.allergyIdAllergy = allergyIdAllergy;
	}
	
	public void getCustomerId(long customerIdCustomer){
		this.customerIdCustomer = customerIdCustomer;
	}
	
	public void getProductName(String productName){
		this.productName = productName;
	}
	
	public void getProductDescription(String productDescription){
		this.productDescription = productDescription;
	}
	
	/*Print all the products in the catalog for all customers in the moment*/
	public static List<ProductCatalogTable> getAllProductCatalogs() {
		List<ProductCatalogTable> prodcatalogs = new ArrayList<>();
		try{
			DB db = DB.getInstance();
			String selectQuery = "SELECT * FROM " + tableName + ";";
			ResultSet rs = db.execute(selectQuery);
			while(rs.next()){
				prodcatalogs.add(new ProductCatalogTable(rs.getLong("idproduct_catalog"),
								rs.getLong("allergy_idallergy"), rs.getLong("customer_idcustomer"),
								rs.getString("product_name"), rs.getString("product_description")));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return prodcatalogs;
	}
	
	/*From a customerID, select all his products in the catalog*/
	public static List<ProductCatalogTable> getProductCatalogById(long id){
		List<ProductCatalogTable> prodcatalogs = new ArrayList<>();
		try{
			DB db = DB.getInstance();
			String selectQuery = "SELECT * FROM " + tableName + " WHERE customer_idcustomer = " + id + ";";
			ResultSet rs = db.execute(selectQuery);
			while(rs.next()){
				prodcatalogs.add(new ProductCatalogTable(rs.getLong("idproduct_catalog"),
								rs.getLong("allergy_idallergy"), rs.getLong("customer_idcustomer"),
								rs.getString("product_name"), rs.getString("product_description")));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return prodcatalogs;
	}
}
