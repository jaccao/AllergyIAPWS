package com.allergyiap.test;

import java.sql.Connection;
import java.sql.Statement;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.naming.InitialContext;
import javax.sql.DataSource;

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
					stm.executeUpdate("insert into example (code, name) values('123','456')");

					String sql_customer = "" + "CREATE TABLE IF NOT EXISTS customer ( "
							+ "  idcustomer SERIAL  NOT NULL , " + "  user_name TEXT  DEFAULT '' NOT NULL , "
							+ "  user_password TEXT  DEFAULT '' NOT NULL , "
							+ "  company_name TEXT  DEFAULT '' NOT NULL   , " + "PRIMARY KEY(idcustomer));";

					String sql_allergy = "" + "CREATE TABLE IF NOT EXISTS allergy ( "
							+ "  idallergy SERIAL  NOT NULL , " + "  allergy_name TEXT    , "
							+ "  allergy_description TEXT    , " + "  allergy_code TEXT      , "
							+ "PRIMARY KEY(idallergy));";

					String sql_allergy_level = "" + "CREATE TABLE IF NOT EXISTS allergy_level ( "
							+ "  idallergy_level SERIAL  NOT NULL , " + "  allergy_idallergy INTEGER   NOT NULL , "
							+ "  current_level FLOAT    , " + "  station TEXT    , " + "  date_start DATE    , "
							+ "  date_end DATE    , " + "  forecast_level TEXT      , "
							+ "PRIMARY KEY(idallergy_level)  , " + "  FOREIGN KEY(allergy_idallergy) "
							+ "    REFERENCES allergy(idallergy) " + "      ON DELETE CASCADE "
							+ "      ON UPDATE CASCADE);";

					String sql_product_catalog = "" + "CREATE TABLE IF NOT EXISTS product_catalog ( "
							+ "  idproduct_catalog SERIAL  NOT NULL , " + "  allergy_idallergy INTEGER   NOT NULL , "
							+ "  customer_idcustomer INTEGER   NOT NULL , " + "  product_name TEXT    , "
							+ "  product_description TEXT      , " + "PRIMARY KEY(idproduct_catalog)    , "
							+ "  FOREIGN KEY(customer_idcustomer) " + "    REFERENCES customer(idcustomer) "
							+ "      ON DELETE CASCADE " + "      ON UPDATE RESTRICT, "
							+ "  FOREIGN KEY(allergy_idallergy) " + "    REFERENCES allergy(idallergy) "
							+ "      ON DELETE RESTRICT " + "      ON UPDATE RESTRICT);";

					stm.executeUpdate(sql_customer);
					stm.executeUpdate(sql_allergy);
					stm.executeUpdate(sql_allergy_level);
					stm.executeUpdate(sql_product_catalog);

					String sql_script;

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
