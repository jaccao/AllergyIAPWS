package com.allergyiap.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.allergyiap.beans.AllergyLevel;

public class AllergyLevelDao extends Dao<AllergyLevel> {

	private static final String TABLE_NAME = "allergy_level";
	private static String idallergy_level = "idallergy_level";
	private static String allergy_idallergy = "allergy_idallergy";
	private static String current_level = "current_level";
	private static String station = "station";
	private static String date_start = "date_start";
	private static String date_end = "date_end";
	private static String forecast_level = "forecast_level";

	/**
	 * 
	 * @param bean
	 */
	@Override
	public void insert(AllergyLevel bean) {

		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO ");
		query.append(TABLE_NAME);
		query.append(" (");
		query.append(allergy_idallergy + ", ");
		query.append(current_level + ", ");
		query.append(station + ", ");
		query.append(date_start + ", ");
		query.append(date_end + ", ");
		query.append(forecast_level);
		query.append(") ");
		query.append("VALUES");
		query.append(" (");
		query.append(bean.getAllergy_idallergy() + ", ");
		query.append(bean.getCurrent_level() + ", ");
		query.append(bean.getStation() + ", ");
		query.append(bean.getDate_start() + ", ");
		query.append(bean.getDate_end() + ", ");
		query.append(bean.getForecast_level());
		query.append(") ");

		db.executeUpdate(query.toString());
	}

	/**
	 * 
	 */
	@Override
	public void delete(int id) {

		StringBuilder query = new StringBuilder();
		query.append("DELETE FROM ");
		query.append(TABLE_NAME);
		query.append(" WHERE ");
		query.append(idallergy_level + " = " + id);

		db.executeUpdate(query.toString());
	}

	/**
	 * 
	 * @param bean
	 */
	@Override
	public void update(AllergyLevel bean) {

		StringBuilder query = new StringBuilder();
		query.append("UPDATE ");
		query.append(TABLE_NAME);
		query.append(" set ");
		query.append(allergy_idallergy + " = " + bean.getAllergy_idallergy() + ", ");
		query.append(current_level + " = " + bean.getCurrent_level() + ", ");
		query.append(station + " = " + bean.getStation() + ", ");
		query.append(date_start + " = " + bean.getDate_start() + ", ");
		query.append(date_end + " = " + bean.getDate_end() + ", ");
		query.append(forecast_level + " = " + bean.getForecast_level());
		query.append(" WHERE ");
		query.append(idallergy_level + " = " + bean.getIdallergy_level());

		db.executeUpdate(query.toString());
	}

	/**
	 * 
	 * @return
	 */
	@Override
	public List<AllergyLevel> getAll() {

		String selectQuery = "SELECT * FROM " + TABLE_NAME + ";";

		return select(selectQuery);
	}

	private List<AllergyLevel> select(String query) {
		List<AllergyLevel> list = new ArrayList<>();

		try {
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			ResultSet rs = db.execute(query);
			while (rs.next()) {

				long idlevel = rs.getLong(idallergy_level);
				long idallergy = rs.getLong(allergy_idallergy);
				float curlevel = rs.getFloat(current_level);
				String stationLevel = rs.getString(station);
				String dateStart = df.format(rs.getDate(date_start));
				String dateEnd = df.format(rs.getDate(date_end));
				String forecastLevel = rs.getString(forecast_level);

				list.add(new AllergyLevel(idlevel, idallergy, curlevel, stationLevel, dateStart, dateEnd, forecastLevel));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
