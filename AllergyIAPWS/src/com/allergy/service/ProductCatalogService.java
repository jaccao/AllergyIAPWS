package com.allergy.service;

import java.util.List;

import com.allergyiap.beans.ProductCatalog;
import com.allergyiap.dao.ProductCatalogDao;

public class ProductCatalogService {

	private static ProductCatalogDao dao = new ProductCatalogDao();

	public static void insert(ProductCatalog bean) {
		dao.insert(bean);
	}

	public static void update(ProductCatalog bean) {
		dao.update(bean);
	}

	public static void delete(int id) {
		dao.delete(id);
	}

	public static ProductCatalog get(long id) {
		return dao.get(id);
	}

	public static List<ProductCatalog> getAllByCustomer(long id) {
		return dao.getByCustomer(id);
	}
	
	public static List<ProductCatalog> getAll() {
		return dao.getAll();
	}

}
