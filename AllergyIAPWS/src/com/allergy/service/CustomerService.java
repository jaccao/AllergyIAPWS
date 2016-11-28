package com.allergy.service;

import java.util.List;

import com.allergyiap.beans.Customer;
import com.allergyiap.dao.CustomerDao;

public class CustomerService {

	private static CustomerDao dao = new CustomerDao();
	
	public static void insert(Customer bean){
		dao.insert(bean);
	}
	
	public static void update(Customer bean){
		dao.update(bean);
	}
	
	public static void delete(int id){
		dao.delete(id);
	}
	
	public static Customer get(long id) {
		return dao.get(id);
	}
	
	public static Customer get(String name, String password) {
		return dao.get(name, password);
	}
	
	public static List<Customer> getAll() {
		return dao.getAll();
	}
	
}
