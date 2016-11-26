package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DB;


public class CustomerTable implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final String tableName = "customer";

	private long id;
	private String userName;
	private String userPassword;
	private String companyName;
	
	
	public CustomerTable(long id, String userName, String userPassword, String companyName){
		this.id = id;
		this.userName = userName;
		this.userPassword = userPassword;
		this.companyName = companyName;
	}
	
	public CustomerTable( String userName, String userPassword, String companyName){
		this.userName = userName;
		this.userPassword = userPassword;
		this.companyName = companyName;
	}
	
	public CustomerTable() {

	}
	
	public long getId(){
		return this.id;
	}
	
	public String getUserName(){
		return this.userName;
	}
	
	public String getUserPassword(){
		return this.userPassword;
	}
	
	public String getCompanyName(){
		return this.companyName;
	}
	
	public static long getSerialversionUId() {
		return serialVersionUID;
	}
	
	public void setgetId(long id){
		this.id = id;
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	public void setUserPassword(String userPassword){
		this.userPassword = userPassword;
	}
	
	public void setCompanyName(String companyName){
		this.companyName = companyName;
	}
	
	public void insertNewCustomer() {
		DB db = DB.getInstance();
		String insertQuery = "INSERT INTO " + tableName + "(user_name,"
				+ "user_password,company_name ) VALUES ('" + userName + "','" + userPassword
				+ "','" + companyName + "');";

		System.out.println(insertQuery);

		db.executeUpdate(insertQuery);
	}

	public void deleteCustomer() {
		DB db = DB.getInstance();
		String deleteQuery = "DELETE FROM " + tableName + " WHERE idcustomer = " + id;
		db.executeUpdate(deleteQuery);
	}

	public void updateCustomer() {
		DB db = DB.getInstance();
		String updateQuery = "UPDATE " + tableName + " set user_name = '" + userName
				+ "', user_password = '" + userPassword + "'," + "company_name = '" + companyName
				+ "' WHERE idcustomer = " + id;
		db.executeUpdate(updateQuery);
	}
	
	
	public static List<CustomerTable> getAllCustomers() {
		List<CustomerTable> customerList = new ArrayList<>();
		try{
			DB db = DB.getInstance();
			String selectQuery = "SELECT * FROM " + tableName + ";";
			ResultSet rs = db.execute(selectQuery);
			while(rs.next()){
				customerList.add(new CustomerTable(rs.getLong("idcustomer"),					
								rs.getString("user_name"), rs.getString("user_password"), rs.getString("company_name")));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return customerList;
	}
	
	public static CustomerTable getCustomerById(long id){
		CustomerTable customer = null;
		try{
			DB db = DB.getInstance();
			String selectQuery = "SELECT * FROM " + tableName + " WHERE idcustomer = " + id + ";";
			ResultSet rs = db.execute(selectQuery);
			while(rs.next()){
				customer = new CustomerTable(rs.getLong("idcustomer"),					
						rs.getString("user_name"), rs.getString("user_password"), rs.getString("company_name"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return customer;
	}
	
}