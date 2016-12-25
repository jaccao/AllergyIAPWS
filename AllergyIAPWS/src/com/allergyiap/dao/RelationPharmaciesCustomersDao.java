package com.allergyiap.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.allergyiap.beans.RelationPharmaciesCustomers;

public class RelationPharmaciesCustomersDao extends Dao<RelationPharmaciesCustomers> {
	private static final String TABLE_NAME = "relation_pharmacies_customers";

	private static String id_customer = "id_customer";
	private static String id_pharmacy = "id_pharmacy";

	@Override
	public void insert(RelationPharmaciesCustomers bean) {
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO ");
		query.append(TABLE_NAME);
		query.append(" (");
		query.append(id_customer + ", ");
		query.append(id_pharmacy);
		query.append(") ");
		query.append("VALUES");
		query.append(" (");
		query.append("'" + bean.getId_customer() + "', ");
		query.append("'" + bean.getId_pharmacy() + "'");
		query.append(") ");

		db.executeUpdate(query.toString());

	}

	@Override
	public void update(RelationPharmaciesCustomers bean) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
	}

	public void delete(int id_customer, int id_pharmacy) {
		StringBuilder query = new StringBuilder();
		query.append("DELETE FROM ");
		query.append(TABLE_NAME);
		query.append(" WHERE ");
		query.append(id_customer + " = " + id_customer + " AND " + id_pharmacy + " = " + id_pharmacy);

		db.executeUpdate(query.toString());

	}

	@Override
	public List<RelationPharmaciesCustomers> getAll() {
		String selectQuery = "SELECT * FROM " + TABLE_NAME + ";";
		return select(selectQuery);
	}

	private List<RelationPharmaciesCustomers> select(String query) {
		List<RelationPharmaciesCustomers> list = new ArrayList<>();
		try {

			ResultSet rs = db.execute(query);
			while (rs.next()) {
				long idcustomer = rs.getLong(id_customer);
				long idpharmacy = rs.getLong(id_pharmacy);
				list.add(new RelationPharmaciesCustomers(idcustomer, idpharmacy));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}

	public List<Integer> getCustomersByPharmacy(long idpharmacy) {
		String selectQuery = "SELECT id_customer FROM " + TABLE_NAME + " WHERE "+ id_pharmacy + " = "+idpharmacy + ";";
		List<Integer> list = new ArrayList<>();
		try {

			ResultSet rs = db.execute(selectQuery);
			while (rs.next()) {
				int idcustomer = rs.getInt(id_customer);
				list.add(idcustomer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return list.isEmpty() ? null : list;
	}

	public List<RelationPharmaciesCustomers> getPharmaciesByCustomer(long idcustomer) {
		String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE "+ id_customer + " = "+idcustomer + ";";
		List<RelationPharmaciesCustomers> list = select(selectQuery);
		return list.isEmpty() ? null : list;
	}

}