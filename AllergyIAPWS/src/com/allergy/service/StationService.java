package com.allergy.service;

import java.util.List;

import com.allergyiap.beans.Station;
import com.allergyiap.dao.StationDao;

public class StationService {
	private static StationDao dao = new StationDao();
	
	public static void insert(Station bean){
		dao.insert(bean);
	}
	
	public static void update(Station bean){
		dao.update(bean);
	}
	
	public static void delete(String name_station){
		dao.delete(name_station);
	}
	
	public static Station get(String name) {
		return dao.get(name);
	}
	
	public static List<Station> getAll() {
		return dao.getAll();
	}
}
