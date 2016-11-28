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
	private static String ID = "idallergy_level";
	private static String ALLERGY_ID = "allergy_idallergy";
	private static String CURRENT_LEVEL = "current_level";
	private static String STATION = "STATION";
	private static String DATE_START = "date_start";
	private static String DATE_END = "date_end";
	private static String FORECAST_LEVEL = "forecast_level";

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
		query.append(ALLERGY_ID + ", ");
		query.append(CURRENT_LEVEL + ", ");
		query.append(STATION + ", ");
		query.append(DATE_START + ", ");
		query.append(DATE_END + ", ");
		query.append(FORECAST_LEVEL);
		query.append(") ");
		query.append("VALUES");
		query.append(" (");
		query.append(bean.getAlleryID() + ", ");
		query.append(bean.getCurrentLevel() + ", ");
		query.append(bean.getStation() + ", ");
		query.append(bean.getDateStart() + ", ");
		query.append(bean.getDateEnd() + ", ");
		query.append(bean.getForecastLevel());
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
		query.append(ID + " = " + id);

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
		query.append(ALLERGY_ID + " = " + bean.getAlleryID() + ", ");
		query.append(CURRENT_LEVEL + " = " + bean.getCurrentLevel() + ", ");
		query.append(STATION + " = " + bean.getStation() + ", ");
		query.append(DATE_START + " = " + bean.getDateStart() + ", ");
		query.append(DATE_END + " = " + bean.getDateEnd() + ", ");
		query.append(FORECAST_LEVEL + " = " + bean.getForecastLevel());
		query.append(" WHERE ");
		query.append(ID + " = " + bean.getId());

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

				long idlevel = rs.getLong(ID);
				long idallergy = rs.getLong(ALLERGY_ID);
				float curlevel = rs.getFloat(CURRENT_LEVEL);
				String station = rs.getString(STATION);
				String dateStart = df.format(rs.getDate(DATE_START));
				String dateEnd = df.format(rs.getDate(DATE_END));
				String forecastLevel = rs.getString(FORECAST_LEVEL);

				list.add(new AllergyLevel(idlevel, idallergy, curlevel, station, dateStart, dateEnd, forecastLevel));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
