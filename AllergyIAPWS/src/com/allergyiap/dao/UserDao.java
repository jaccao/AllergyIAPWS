package com.allergyiap.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.allergyiap.beans.Allergy;
import com.allergyiap.beans.User;

public class UserDao extends Dao<User>{
	
	private static final String TABLE_NAME = "user_information";

	private static String ID = "iduser";
	private static String NAME = "user_name";
	private static String SECONDNAME = "user_second_name";
	private static String MAIL = "user_mail";
	private static String PASSWORD = "user_password";

	@Override
	public void insert(User bean) {
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO ");
		query.append(TABLE_NAME);
		query.append(" (");
		query.append(NAME + ", ");
		query.append(SECONDNAME + ", ");
		query.append(MAIL + ", ");
		query.append(PASSWORD + " ");
		query.append(") ");
		query.append("VALUES");
		query.append(" (");
		query.append(bean.getUserName() + ", ");
		query.append(bean.getUserSecondName() + ", ");
		query.append(bean.getUserMail() + ", ");
		query.append(bean.getUserPassword() + " ");
		query.append(") ");
		db.executeUpdate(query.toString());
		
	}

	@Override
	public void update(User bean) {
		StringBuilder query = new StringBuilder();
		query.append("UPDATE ");
		query.append(TABLE_NAME);
		query.append(" set ");
		query.append(NAME + " = " + bean.getUserName() + ", ");
		query.append(SECONDNAME + " = " + bean.getUserSecondName() + ", ");
		query.append(MAIL + " = " + bean.getUserMail() + " ");
		query.append(PASSWORD + " = " + bean.getUserPassword() + " ");
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
	public List<User> getAll() {
		String selectQuery = "SELECT * FROM " + TABLE_NAME + ";";
		return select(selectQuery);
	}
	
	private List<User> select(String query) {
		List<User> list = new ArrayList<>();

		try {

			ResultSet rs = db.execute(query);
			while (rs.next()) {

				long id = rs.getLong(ID);
				String name = rs.getString(NAME);
				String secondName = rs.getString(SECONDNAME);
				String mail = rs.getString(MAIL);
				String password = rs.getString(PASSWORD);

				list.add(new User(id, name, secondName, mail, password));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public User get(long id) {

		String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " = " + id + ";";
		List<User> users = select(selectQuery);
		return users.isEmpty() ? null : users.get(0);
	}

}
