package com.allergyiap.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.allergyiap.beans.ProductCatalog;

public class ProductCatalogDao extends Dao<ProductCatalog> {

	private static final String TABLE_NAME = "product_catalog";

	private static String ID = "idproduct_catalog";
	private static String ALLERGY_ID = "allergy_idallergy";
	private static String CUSTOMER_ID = "customer_idcustomer";
	private static String NAME = "product_name";
	private static String DESCRIPTION = "product_description";

	/**
	 * 
	 */
	@Override
	public void insert(ProductCatalog bean) {
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO ");
		query.append(TABLE_NAME);
		query.append(" (");
		query.append(ALLERGY_ID + ", ");
		query.append(CUSTOMER_ID + ", ");
		query.append(NAME + ", ");
		query.append(DESCRIPTION + " ");
		query.append(") ");
		query.append("VALUES");
		query.append(" (");
		query.append("'" + bean.getAllergyId() + "', ");
		query.append("'" + bean.getCustomerId() + "', ");
		query.append("'" + bean.getProductName() + "', ");
		query.append("'" + bean.getProductDescription() + "' ");
		query.append(") ");

		db.executeUpdate(query.toString());
	}

	@Override
	public void update(ProductCatalog bean) {

		StringBuilder query = new StringBuilder();
		query.append("UPDATE ");
		query.append(TABLE_NAME);
		query.append(" set ");
		query.append(ALLERGY_ID + " = '" + bean.getAllergyId() + "', ");
		query.append(CUSTOMER_ID + " = '" + bean.getCustomerId() + "', ");
		query.append(NAME + " = '" + bean.getProductName() + "', ");
		query.append(DESCRIPTION + " = '" + bean.getProductDescription() + "' ");
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
	public List<ProductCatalog> getAll() {

		String selectQuery = "SELECT * FROM " + TABLE_NAME + ";";
		return select(selectQuery);
	}

	private List<ProductCatalog> select(String query) {
		List<ProductCatalog> list = new ArrayList<>();

		try {

			ResultSet rs = db.execute(query);
			while (rs.next()) {

				long idlevel = rs.getLong(ID);
				long idallergy = rs.getLong(ALLERGY_ID);
				long idcustomer = rs.getLong(CUSTOMER_ID);
				String name = rs.getString(NAME);
				String description = rs.getString(DESCRIPTION);

				list.add(new ProductCatalog(idlevel, idallergy, idcustomer, name, description));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}

	/* From a customerID, select all his products in the catalog */
	public List<ProductCatalog> getByCustomer(long id) {
		String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + CUSTOMER_ID + " = " + id + ";";
		return select(selectQuery);
	}

	public ProductCatalog get(long id) {
		String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " = " + id + ";";
		List<ProductCatalog> list = select(selectQuery);
		return list.isEmpty() ? null : list.get(0);
	}
}
