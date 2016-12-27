
package com.allergy.service;

import java.util.List;

import com.allergyiap.beans.Allergy;
import com.allergyiap.beans.User;
import com.allergyiap.beans.UserAllergy;
import com.allergyiap.dao.UserAllergyDao;

public class UserAllergyService {
	private static UserAllergyDao dao = new UserAllergyDao();
	
	public static void insert(UserAllergy bean){
		dao.insert(bean);
	}
	
	public static void update(UserAllergy bean){
		dao.update(bean);
	}
	
	public static void delete(int iduser, int idallergy){
		dao.delete(iduser, idallergy);
	}
	
	public static List<User> getUsersByAllergy(long idallergy) {
		return dao.getUsersByAllergy(idallergy);
	}
	
	public static List<Allergy> getAllergyesByUser(long iduser) {
		return dao.getAllergyesByUser(iduser);
	}
	
	public static List<UserAllergy> getAll() {
		return dao.getAll();
	}
}
