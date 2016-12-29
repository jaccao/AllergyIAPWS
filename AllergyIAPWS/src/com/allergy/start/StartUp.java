package com.allergy.start;

import java.sql.Connection;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.allergy.comm.SystemSql;

@Startup

@Singleton

public class StartUp {
	@PostConstruct
	void atStartup() {
		System.out.println("Creating the Database....");
		String strStatus = "Ok";
		try {
			InitialContext cxt = new InitialContext();
			if (cxt != null) {
				DataSource ds = (DataSource) cxt.lookup("java:/PostgresXADS");
				if (ds == null)
					strStatus = "Error while obtaining the dataSource";
				else {
					Connection connection = ds.getConnection();
					Statement stm = connection.createStatement();

					String sql_customer = "" + "CREATE TABLE IF NOT EXISTS customer ( "
							+ "  idcustomer SERIAL  NOT NULL , " 
							+ "  user_mail TEXT UNIQUE NOT NULL , "
							+ "  user_password TEXT  DEFAULT '' NOT NULL , "
							+ "  company_name TEXT  DEFAULT '' NOT NULL   , " 
							+ "  url_logo TEXT  DEFAULT '' NOT NULL   , " 
							+ "  company_description TEXT  DEFAULT '' NOT NULL , " 
							+ "  PRIMARY KEY(idcustomer));";

					String sql_allergy = "" + "CREATE TABLE IF NOT EXISTS allergy ( "
							+ "  idallergy INTEGER NOT NULL , " 
							+ "  allergy_name TEXT , "
							+ "  allergy_description TEXT , " 
							+ "  allergy_code TEXT , "
							+ "  PRIMARY KEY(idallergy));";
					
					String sql_station_table = ""+ "CREATE TABLE IF NOT EXISTS station ("
							+ "  idstation SERIAL NOT NULL ,"
							+ "  name_station TEXT DEFAULT '' NOT NULL ,"
							+ "  latitude real NOT NULL ,"
							+ "  longitude real  NOT NULL ,"
							+ "  PRIMARY KEY(idstation));";

					String sql_allergy_level = "" + "CREATE TABLE IF NOT EXISTS allergy_level ( "
							+ "  idallergy_level SERIAL NOT NULL , " 
							+ "  allergy_idallergy INTEGER NOT NULL , "
							+ "  current_level FLOAT , " 
							+ "  station TEXT NOT NULL , " 
							+ "  date_start DATE , "
							+ "  date_end DATE , " 
							+ "  forecast_level TEXT ,"
							+ "  PRIMARY KEY(idallergy_level) ," 
							+ "  FOREIGN KEY(allergy_idallergy) REFERENCES allergy(idallergy) " 
							+ "  ON DELETE CASCADE ON UPDATE CASCADE);";

					String sql_product_catalog = "" + "CREATE TABLE IF NOT EXISTS product_catalog ( "
							+ "  idproduct_catalog SERIAL  NOT NULL , " 
							+ "  allergy_idallergy INTEGER   NOT NULL , "
							+ "  customer_idcustomer INTEGER   NOT NULL , " 
							+ "  product_name TEXT , "
							+ "  product_description TEXT , " 
							+ "  product_url_image TEXT ,"
							+ "  PRIMARY KEY(idproduct_catalog) , "
							+ "  FOREIGN KEY(customer_idcustomer) REFERENCES customer(idcustomer) "
							+ "  ON DELETE CASCADE ON UPDATE RESTRICT, "
							+ "  FOREIGN KEY(allergy_idallergy) REFERENCES allergy(idallergy) "
							+ "  ON DELETE RESTRICT ON UPDATE RESTRICT);";
					
					String sql_user_table = "" + "CREATE TABLE IF NOT EXISTS user_information ( "
							+ "  iduser SERIAL NOT NULL, "
							+ "  user_name TEXT  DEFAULT '' NOT NULL ,"
							+ "  user_second_name TEXT  DEFAULT '' NOT NULL ,"
							+ "  user_mail TEXT UNIQUE NOT NULL ,"
							+ "  user_password TEXT  DEFAULT '' NOT NULL ,"
							+ "  user_station_default INTEGER NOT NULL ,"
							+ "  alarm_weekdays TEXT DEFAULT '' NOT NULL , "
							+ "  alarm_time TIME ,"
							+ "  PRIMARY KEY(iduser) ,"
							+ "  FOREIGN KEY(user_station_default) REFERENCES station(idstation)"
							+ "  ON DELETE CASCADE ON UPDATE RESTRICT);";
					
					String sql_pharmacy_table = "" + "CREATE TABLE IF NOT EXISTS pharmacy ("
							+ "  id_pharmacy SERIAL NOT NULL ,"
							+ "  name_pharmacy TEXT DEFAULT '' NOT NULL ,"
							+ "  latitude real NOT NULL ,"
							+ "  longitude real NOT NULL,"
							+ "  PRIMARY KEY(id_pharmacy));";
										
					String sql_pharmacy_customer_table = "" + "CREATE TABLE IF NOT EXISTS relation_pharmacies_customers ("
							+ "  id_pharmacy INTEGER NOT NULL , "
							+ "  id_customer INTEGER NOT NULL , "
							+ "  PRIMARY KEY (id_pharmacy, id_customer), "
							+ "  FOREIGN KEY(id_customer) REFERENCES customer(idcustomer)"
							+ "  ON DELETE CASCADE ON UPDATE RESTRICT,"
							+ "  FOREIGN KEY(id_pharmacy) REFERENCES pharmacy(id_pharmacy)"
							+ "  ON DELETE CASCADE ON UPDATE RESTRICT);";

					String sql_users_allergies = "" + "CREATE TABLE IF NOT EXISTS user_allergies ("
							+ "  id_user INTEGER NOT NULL , "
							+ "  id_allergy INTEGER NOT NULL ,"
							+ "  PRIMARY KEY (id_user, id_allergy), "
							+ "  FOREIGN KEY(id_user) REFERENCES user_information(iduser)"
							+ "  ON DELETE CASCADE ON UPDATE RESTRICT,"
							+ "  FOREIGN KEY(id_allergy) REFERENCES allergy(idallergy)"
							+ "  ON DELETE CASCADE ON UPDATE RESTRICT);";
										
					stm.executeUpdate(sql_customer);
					stm.executeUpdate(sql_station_table);
					stm.executeUpdate(sql_user_table);
					stm.executeUpdate(sql_allergy);
					stm.executeUpdate(sql_allergy_level);
					stm.executeUpdate(sql_product_catalog);
					stm.executeUpdate(sql_pharmacy_table);
					stm.executeUpdate(sql_pharmacy_customer_table);
					stm.executeUpdate(sql_users_allergies);

					String sql_script;
					
					List<HashMap<String, Object>> AllergiesExists = SystemSql
							.executeQuery("SELECT * FROM allergy;");
					
					if(AllergiesExists.isEmpty() || AllergiesExists.size() != 12){
						sql_script = "" + "INSERT INTO allergy VALUES (1, 'Urticaceae', NULL, 'URTI');";
						stm.executeUpdate(sql_script);

						sql_script = "" + "INSERT INTO allergy VALUES (2, 'Gramineae (Poaceae)', NULL, 'GRAM');";
						stm.executeUpdate(sql_script);

						sql_script = "" + "INSERT INTO allergy VALUES (3, 'Olea', NULL, 'OLEA');";
						stm.executeUpdate(sql_script);

						sql_script = "" + "INSERT INTO allergy VALUES (4, 'Artemisia', NULL, 'ARTE');";
						stm.executeUpdate(sql_script);

						sql_script = "" + "INSERT INTO allergy VALUES (5, 'Chenopodiaceae/Amaranthaceae', NULL, 'QUAM');";
						stm.executeUpdate(sql_script);

						sql_script = "" + "INSERT INTO allergy VALUES (6, 'Erica', NULL, 'ERIC');";
						stm.executeUpdate(sql_script);

						sql_script = "" + "INSERT INTO allergy VALUES (7, 'Casuarina', NULL, 'CASU');";
						stm.executeUpdate(sql_script);

						sql_script = "" + "INSERT INTO allergy VALUES (8, 'Compositae (Asteraceae)', NULL, 'COMP');";
						stm.executeUpdate(sql_script);

						sql_script = "" + "INSERT INTO allergy VALUES (9, 'Cruciferae (Brassicaceae)', NULL, 'CRUC');";
						stm.executeUpdate(sql_script);

						sql_script = "" + "INSERT INTO allergy VALUES (10, 'Mercurialis', NULL, 'MERC');";
						stm.executeUpdate(sql_script);

						sql_script = "" + "INSERT INTO allergy VALUES (11, 'Palmae (Arecaceae)', NULL, 'PALM');";
						stm.executeUpdate(sql_script);

						sql_script = "" + "INSERT INTO allergy VALUES (12, 'Cupressaceae', NULL, 'CUPR');";
						stm.executeUpdate(sql_script);
					}
					stm.close();
					connection.close();
				}
			}
		} catch (Exception e) {
			strStatus = e.getMessage();
		}
		System.out.println(strStatus);
	}
}
