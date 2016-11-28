package com.allergy.comm;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class SystemSql {
	static private List<HashMap<String, Object>> convertResultSetToList(ResultSet rs) throws SQLException {
		ResultSetMetaData md = rs.getMetaData();
		int columns = md.getColumnCount();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		while (rs.next()) {
			HashMap<String, Object> row = new HashMap<String, Object>(columns);
			for (int i = 1; i <= columns; ++i) {
				row.put(md.getColumnName(i), rs.getObject(i));
			}
			list.add(row);
		}

		return list;
	}

	static public List<HashMap<String, Object>> executeQuery(String query) throws SQLException, NamingException {
		InitialContext cxt = new InitialContext();
		if (cxt != null) {
			DataSource ds = (DataSource) cxt.lookup("java:/PostgresXADS");
			if (ds != null) {
				Connection connection = ds.getConnection();
				Statement stm = connection.createStatement();
				ResultSet rs = stm.executeQuery(query);
				List<HashMap<String, Object>> lm = SystemSql.convertResultSetToList(rs);
				connection.close();
				stm.close();
				return lm;
			}
		}
		return null;
	}

	static public void execute(String query) throws SQLException, NamingException {
		InitialContext cxt = new InitialContext();
		if (cxt != null) {
			DataSource ds = (DataSource) cxt.lookup("java:/PostgresXADS");
			if (ds != null) {
				Connection connection = ds.getConnection();
				Statement stm = connection.createStatement();
				stm.executeUpdate(query);
				connection.close();
				stm.close();
			}
		}
	}

}
