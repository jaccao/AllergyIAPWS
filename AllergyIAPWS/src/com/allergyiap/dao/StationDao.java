package com.allergyiap.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.allergyiap.beans.Station;

public class StationDao extends Dao<Station>{
	
	private static final String TABLE_NAME = "station";
	
	private static String name_station = "name_station" ;
	private static String latitude = "latitude";
	private static String longitude = "longitude";

	@Override
	public void insert(Station bean) {
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO ");
		query.append(TABLE_NAME);
		query.append(" (");
		query.append(name_station + ", ");
		query.append(latitude + ", ");
		query.append(longitude + " ");
		query.append(") ");
		query.append("VALUES");
		query.append(" (");
		query.append("'" + bean.getName_station() + "', ");
		query.append("'" + bean.getLatitude() + "', ");
		query.append("'" + bean.getLongitude() + "' ");
		query.append(") ");
		
		db.executeUpdate(query.toString());
	}

	@Override
	public void update(Station bean) {
		StringBuilder query = new StringBuilder();
		query.append("UPDATE ");
		query.append(TABLE_NAME);
		query.append(" set ");
		query.append(latitude + " = '" + bean.getLatitude() + "', ");
		query.append(longitude + " = '" + bean.getLongitude() + "' ");
		query.append(" WHERE ");
		query.append(name_station + " = " + bean.getName_station());

		db.executeUpdate(query.toString());
		
	}

	public void delete(String station) {
		StringBuilder query = new StringBuilder();
		query.append("DELETE FROM ");
		query.append(TABLE_NAME);
		query.append(" WHERE ");
		query.append(name_station + " = " + station);

		db.executeUpdate(query.toString());
		
	}

	@Override
	public List<Station> getAll() {
		String selectQuery = "SELECT * FROM " + TABLE_NAME + ";";
		return select(selectQuery);
	}
	
	private List<Station> select(String query) {
		List<Station> list = new ArrayList<>();
		try {

			ResultSet rs = db.execute(query);
			while (rs.next()) {
				String name = rs.getString(name_station);
				String lat = rs.getString(latitude);
				String lon = rs.getString(longitude);
				list.add(new Station(name, lat, lon));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
	}

}
