package com.allergyiap.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.allergyiap.beans.Allergy;

public class AllergyDao extends Dao<Allergy> {

	private static final String TABLE_NAME = "allergy";

	private static String ID = "idallergy";
	private static String NAME = "allergy_name";
	private static String DESCRIPTION = "allergy_description";
	private static String CODE = "allergy_code";

	/**
	 * 
	 */
	@Override
	public void insert(Allergy bean) {
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO ");
		query.append(TABLE_NAME);
		query.append(" (");
		query.append(NAME + ", ");
		query.append(DESCRIPTION + ", ");
		query.append(CODE + " ");
		query.append(") ");
		query.append("VALUES");
		query.append(" (");
		query.append(bean.getAllergyName() + ", ");
		query.append(bean.getAllergyDescription() + ", ");
		query.append(bean.getAllergyCode() + " ");
		query.append(") ");

		db.executeUpdate(query.toString());
	}

	@Override
	public void update(Allergy bean) {

		StringBuilder query = new StringBuilder();
		query.append("UPDATE ");
		query.append(TABLE_NAME);
		query.append(" set ");
		query.append(CODE + " = " + bean.getAllergyCode() + ", ");
		query.append(NAME + " = " + bean.getAllergyName() + ", ");
		query.append(DESCRIPTION + " = " + bean.getAllergyDescription() + " ");
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
	public List<Allergy> getAll() {

		String selectQuery = "SELECT * FROM " + TABLE_NAME + ";";
		return select(selectQuery);
	}

	private List<Allergy> select(String query) {
		List<Allergy> list = new ArrayList<>();

		try {

			ResultSet rs = db.execute(query);
			while (rs.next()) {

				long id = rs.getLong(ID);
				String name = rs.getString(NAME);
				String description = rs.getString(DESCRIPTION);
				String code = rs.getString(CODE);

				list.add(new Allergy(id, name, description, code));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public Allergy get(long id) {

		String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " = " + id + ";";
		List<Allergy> customers = select(selectQuery);
		return customers.isEmpty() ? null : customers.get(0);
	}

}
