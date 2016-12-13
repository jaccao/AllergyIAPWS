package com.allergyiap.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.allergyiap.beans.Customer;

public class CustomerDao extends Dao<Customer> {

	private static final String TABLE_NAME = "customer";

	private static String ID = "idcustomer";
	private static String NAME = "user_name";
	private static String PASSWORD = "user_password";
	private static String COMPANY_NAME = "company_name";
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
		query.append(NAME + ", ");
		query.append(PASSWORD + ", ");
		query.append(COMPANY_NAME + ", ");
		query.append(pharmacy_location + " ");
		query.append(") ");
		query.append("VALUES");
		query.append(" (");
		query.append("'" + bean.getUserName() + "', ");
		query.append("'" + bean.getPassword() + "', ");
		query.append("'" + bean.getCompanyName() + "', ");
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
		query.append(COMPANY_NAME + " = '" + bean.getCompanyName() + "', ");
		query.append(NAME + " = '" + bean.getUserName() + "', ");
		query.append(PASSWORD + " = '" + bean.getPassword() + "', ");
		query.append(pharmacy_location + " = '" + bean.getPharmacy_location() + "' ");
		query.append(" WHERE ");
		query.append(ID + " = " + bean.getId());

		db.executeUpdate(query.toString());
	}

	@Override
	public void delete(int id) {

		StringBuilder query = new StringBuilder();
		query.append("DELETE FROM ");
		query.append(TABLE_NAME);
		query.append(" WHERE ");
		query.append(ID + " = " + id);

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

				long id = rs.getLong(ID);
				String name = rs.getString(NAME);
				String pass = rs.getString(PASSWORD);
				String description = rs.getString(COMPANY_NAME);
				String pharmacy = rs.getString(pharmacy_location);

				list.add(new Customer(id, name, pass, description, pharmacy));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public Customer get(long id) {

		String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " = " + id + ";";
		List<Customer> customers = select(selectQuery);
		return customers.isEmpty() ? null : customers.get(0);
	}

	public Customer get(String name, String password) {

		String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + NAME + " = '" + name 
				+ "' AND " + PASSWORD + " = '" + password + "';";
		List<Customer> customers = select(selectQuery);
		return customers.isEmpty() ? null : customers.get(0);
	}
}
