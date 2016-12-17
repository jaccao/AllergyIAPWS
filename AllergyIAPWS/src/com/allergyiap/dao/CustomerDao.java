package com.allergyiap.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.allergyiap.beans.Customer;

public class CustomerDao extends Dao<Customer> {

	private static final String TABLE_NAME = "customer";

	private static String idcustomer = "idcustomer";
	private static String user_name = "user_name";
	private static String user_password = "user_password";
	private static String company_name = "company_name";
	private static String pharmacy_location = "pharmacy_location";

	/**
	 * 
	 */
	@Override
	public void insert(Customer bean) {
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO ");
		query.append(TABLE_NAME);
		query.append(" (");
		query.append(user_name + ", ");
		query.append(user_password + ", ");
		query.append(company_name + ", ");
		query.append(pharmacy_location + " ");
		query.append(") ");
		query.append("VALUES");
		query.append(" (");
		query.append("'" + bean.getUser_name() + "', ");
		query.append("'" + bean.getUser_password() + "', ");
		query.append("'" + bean.getCompany_name() + "', ");
		query.append("'" + bean.getPharmacy_location() + "' ");
		query.append(") ");

		db.executeUpdate(query.toString());
	}

	@Override
	public void update(Customer bean) {

		StringBuilder query = new StringBuilder();
		query.append("UPDATE ");
		query.append(TABLE_NAME);
		query.append(" set ");
		query.append(company_name + " = '" + bean.getCompany_name() + "', ");
		query.append(user_name + " = '" + bean.getUser_name() + "', ");
		query.append(user_password + " = '" + bean.getUser_password() + "', ");
		query.append(pharmacy_location + " = '" + bean.getPharmacy_location() + "' ");
		query.append(" WHERE ");
		query.append(idcustomer + " = " + bean.getIdcustomer());

		db.executeUpdate(query.toString());
	}

	@Override
	public void delete(int id) {

		StringBuilder query = new StringBuilder();
		query.append("DELETE FROM ");
		query.append(TABLE_NAME);
		query.append(" WHERE ");
		query.append(idcustomer + " = " + id);

		db.executeUpdate(query.toString());
	}

	@Override
	public List<Customer> getAll() {

		String selectQuery = "SELECT * FROM " + TABLE_NAME + ";";
		return select(selectQuery);
	}

	private List<Customer> select(String query) {
		List<Customer> list = new ArrayList<>();
		try {

			ResultSet rs = db.execute(query);
			while (rs.next()) {
				long id = rs.getLong(idcustomer);
				String name = rs.getString(user_name);
				String pass = rs.getString(user_password);
				String description = rs.getString(company_name);
				String pharmacy = rs.getString(pharmacy_location);
				list.add(new Customer(id, name, pass, description, pharmacy));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}

	public Customer get(long id) {
		String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + idcustomer + " = " + id + ";";
		List<Customer> customers = select(selectQuery);
		Customer c = customers.isEmpty() ? null : customers.get(0);
		return c;
	}

	public Customer get(String name, String password) {
		String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + user_name + " = '" + name 
				+ "' AND " + user_password + " = '" + password + "';";
		List<Customer> customers = select(selectQuery);
		return customers.isEmpty() ? null : customers.get(0);
	}
}