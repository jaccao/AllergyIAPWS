package database;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Statement;


public class DB {

	private Connection conn;
	private static DB instance = new DB();
	private final String dataSource = "java:/PostgresXADS";
	
	public static DB getInstance(){
		return instance;
	}
	
	private DB(){
		try{
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup(dataSource);
			conn = ds.getConnection();
			
		}catch(Exception e){
			System.out.println("Datasource " + dataSource + "can't be obtained");
			e.printStackTrace();
		}
	}
	
	/*For select statements*/
	public ResultSet execute(String query){
		Statement statement;
		ResultSet rs = null;
		try {
			statement = conn.createStatement();
			rs = statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	/*For insert, update and delete statements*/
	public void executeUpdate(String query){
		Statement statement;
		try {
			statement = conn.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
