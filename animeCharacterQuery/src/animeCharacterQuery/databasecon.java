package animeCharacterQuery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class databasecon {
	
	private String db = "jdbc:sqlite:/Volumes/SHARE/ComputerScience/HarryYu-IBCSdatabaseMiniProject/animeCharacterQuery/character.db";
	private Connection con;
	
	public databasecon() {
		//open database connection
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection(this.db);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet getsql(String sql) throws SQLException {
		
		
		
		ResultSet rs = null;
		
		try {
			
			//creating statement
			
			Statement sqlstring = con.createStatement();
			
			//execute sql query
			
			try {
				rs = sqlstring.executeQuery(sql);
			} catch(Exception f) {
				rs = null;
				sqlstring.executeUpdate(sql);
			}
			
			System.out.println("sql executed");
 			
			
			return rs;
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} /*finally {
			
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}*/
		
		return null;
		
		
		
	}
}
