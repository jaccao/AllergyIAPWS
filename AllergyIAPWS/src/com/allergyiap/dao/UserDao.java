package com.allergyiap.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.allergyiap.beans.Allergy;
import com.allergyiap.beans.User;

public class UserDao extends Dao<User>{
	
	private static final String TABLE_NAME = "user_information";

	private static String iduser = "iduser";
	private static String user_name = "user_name";
	private static String user_second_name = "user_second_name";
	private static String user_mail = "user_mail";
	private static String user_password = "user_password";

	@Override
	public void insert(User bean) {
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO ");
		query.append(TABLE_NAME);
		query.append(" (");
		query.append(user_name + ", ");
		query.append(user_second_name + ", ");
		query.append(user_mail + ", ");
		query.append(user_password + " ");
		query.append(") ");
		query.append("VALUES");
		query.append(" ('");
		query.append(bean.getUser_name() + "', '");
		query.append(bean.getUser_second_name() + "', '");
		query.append(bean.getUser_mail() + "', '");
		query.append(bean.getUser_password() + "' ");
		query.append(") ");
		db.executeUpdate(query.toString());
		
	}

	@Override
	public void update(User bean) {
		StringBuilder query = new StringBuilder();
		query.append("UPDATE ");
		query.append(TABLE_NAME);
		query.append(" SET ");
		query.append(user_name + " = '" + bean.getUser_name() + "', ");
		query.append(user_second_name + " = '" + bean.getUser_second_name() + "', ");
		query.append(user_mail + " = '" + bean.getUser_mail() + "', ");
		query.append(user_password + " = '" + bean.getUser_password() + "' ");
		query.append(" WHERE ");
		query.append(iduser + " = " + bean.getIduser());

		db.executeUpdate(query.toString());
		
	}

	@Override
	public void delete(int id) {
		StringBuilder query = new StringBuilder();
		query.append("DELETE FROM ");
		query.append(TABLE_NAME);
		query.append(" WHERE ");
		query.append(iduser + " = " + id);

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

				long id = rs.getLong(iduser);
				String name = rs.getString(user_name);
				String secondName = rs.getString(user_second_name);
				String mail = rs.getString(user_mail);
				String password = rs.getString(user_password);

				list.add(new User(id, name, secondName, mail, password));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public User get(long id) {

		String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + iduser + " = " + id + ";";
		List<User> users = select(selectQuery);
		return users.isEmpty() ? null : users.get(0);
	}
	
	public User isValidLogin(String mail, String password){
		String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + user_mail + " = '" + mail + "' AND "+ user_password + " = '" + password+"' ;";
		List<User> users = select(selectQuery);
		return users.isEmpty() ? null : users.get(0);
	}

}
