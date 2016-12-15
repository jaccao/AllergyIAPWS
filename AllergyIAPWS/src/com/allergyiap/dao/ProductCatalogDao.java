package com.allergyiap.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.allergyiap.beans.ProductCatalog;

public class ProductCatalogDao extends Dao<ProductCatalog> {

	private static final String TABLE_NAME = "product_catalog";

	private static String idproduct_catalog = "idproduct_catalog";
	private static String allergy_idallergy = "allergy_idallergy";
	private static String customer_idcustomer = "customer_idcustomer";
	private static String product_name = "product_name";
	private static String product_description = "product_description";

	/**
	 * 
	 */
	@Override
	public void insert(ProductCatalog bean) {
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO ");
		query.append(TABLE_NAME);
		query.append(" (");
		query.append(allergy_idallergy + ", ");
		query.append(customer_idcustomer + ", ");
		query.append(product_name + ", ");
		query.append(product_description + " ");
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
		query.append(allergy_idallergy + " = '" + bean.getAllergyId() + "', ");
		query.append(customer_idcustomer + " = '" + bean.getCustomerId() + "', ");
		query.append(product_name + " = '" + bean.getProductName() + "', ");
		query.append(product_description + " = '" + bean.getProductDescription() + "' ");
		query.append(" WHERE ");
		query.append(idproduct_catalog + " = " + bean.getId());

		db.executeUpdate(query.toString());
	}

	@Override
	public void delete(int id) {

		StringBuilder query = new StringBuilder();
		query.append("DELETE FROM ");
		query.append(TABLE_NAME);
		query.append(" WHERE ");
		query.append(idproduct_catalog + " = " + id);

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

				long idlevel = rs.getLong(idproduct_catalog);
				long idallergy = rs.getLong(allergy_idallergy);
				long idcustomer = rs.getLong(customer_idcustomer);
				String name = rs.getString(product_name);
				String description = rs.getString(product_description);

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
		String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + customer_idcustomer + " = " + id + ";";
		return select(selectQuery);
	}

	public ProductCatalog get(long id) {
		String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + idproduct_catalog + " = " + id + ";";
		List<ProductCatalog> list = select(selectQuery);
		return list.isEmpty() ? null : list.get(0);
	}
}
