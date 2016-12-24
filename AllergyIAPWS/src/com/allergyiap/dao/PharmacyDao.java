package com.allergyiap.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.allergyiap.beans.Pharmacy;

public class PharmacyDao extends Dao<Pharmacy>{
	
	private static final String TABLE_NAME = "pharmacy";
	
	private String id_pharmacy = "id_pharmacy";
	private String id_customer = "id_customer";
	private String name_pharmacy = "name_pharmacy";
	private String latitude = "latitude";
	private String longitude = "longitude";

	@Override
	public void insert(Pharmacy bean) {
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO ");
		query.append(TABLE_NAME);
		query.append(" (");
		query.append(id_customer + ", ");
		query.append(name_pharmacy + ", ");
		query.append(latitude + ", ");
		query.append(longitude + " ");
		query.append(") ");
		query.append("VALUES");
		query.append(" (");
		query.append("'" + bean.getId_customer() + "', ");
		query.append("'" + bean.getName_pharmacy() + "', ");
		query.append("'" + bean.getLatitude() + "', ");
		query.append("'" + bean.getLongitude() + "' ");
		query.append(") ");
		
		db.executeUpdate(query.toString());
	}

	@Override
	public void update(Pharmacy bean) {
		StringBuilder query = new StringBuilder();
		query.append("UPDATE ");
		query.append(TABLE_NAME);
		query.append(" set ");
		query.append(id_customer + " = '" + bean.getId_customer() + "', ");
		query.append(name_pharmacy + " = '" + bean.getName_pharmacy() + "', ");
		query.append(latitude + " = '" + bean.getLatitude() + "', ");
		query.append(longitude + " = '" + bean.getLongitude() + "' ");
		query.append(" WHERE ");
		query.append(id_pharmacy + " = " + bean.getId_pharmacy());

		db.executeUpdate(query.toString());
		
	}

	@Override
	public void delete(int id) {
		StringBuilder query = new StringBuilder();
		query.append("DELETE FROM ");
		query.append(TABLE_NAME);
		query.append(" WHERE ");
		query.append(id_pharmacy + " = " + id);

		db.executeUpdate(query.toString());
		
	}

	@Override
	public List<Pharmacy> getAll() {
		String selectQuery = "SELECT * FROM " + TABLE_NAME + ";";
		return select(selectQuery);
	}
	
	private List<Pharmacy> select(String query) {
		List<Pharmacy> list = new ArrayList<>();
		try {

			ResultSet rs = db.execute(query);
			while (rs.next()) {
				long idp = rs.getLong(id_pharmacy);
				long idc = rs.getLong(id_customer);
				String name = rs.getString(name_pharmacy);
				String lat = rs.getString(latitude);
				String lon = rs.getString(longitude);
				list.add(new Pharmacy(idp, idc, name, lat, lon));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}


}