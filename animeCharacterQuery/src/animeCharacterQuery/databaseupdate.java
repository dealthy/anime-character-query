package animeCharacterQuery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class databaseupdate implements databaseinfo {
	
	private int characterid;
	
	private String firstname, lastname, animefrom;
	
	private String db = "jdbc:sqlite:/Volumes/SHARE/ComputerScience/HarryYu-IBCSdatabaseMiniProject/animeCharacterQuery/character.db";
	
	@Override
	public DefaultListModel display(String searchword, JList table) throws SQLException {
		
		Connection conn; 

		try {

			// open a database connection

			conn = DriverManager.getConnection(this.db);


			// let the database do its work

			

			//order by Name has to be at the end, so it is added 
			//after the where statement
			
			String sql = "SELECT * FROM character WHERE firstname LIKE '%?%' OR lastname LIKE '%?%' OR animefrom LIKE '%?%'";

			PreparedStatement sqlupdate = conn.prepareStatement(sql);
			
			ResultSet rs = sqlupdate.executeQuery(sql);

			/* 

			stmt.excuteUpdate(sql);
			update database command after changing it

			prepared statement

			*/


			// put the items in the JList
			DefaultListModel model = new DefaultListModel();

			table.setModel(model);

			while(rs.next()){

				model.addElement(rs.getString("firstname"));

			}
			


			// close the connection

			sqlupdate.close();

			conn.close();
			
			return model;


		} catch (Exception f) {

			f.printStackTrace();
			System.out.println("error thrown in loading sql");
			return new DefaultListModel();

		}
	}

	@Override
	public void create(String firstname, String lastname, String animefrom) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(String firstname, String lastname, String animefrom) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int search(String searchword) throws SQLException {
		Connection conn;
		// TODO Auto-generated method stub
		try {
			conn = DriverManager.getConnection(this.db);
			
			String sql = "SELECT ? FROM character WHERE firstname LIKE '%?%' OR lastname LIKE '%?%' OR animefrom LIKE '%?%'";

			PreparedStatement sqlupdate = conn.prepareStatement(sql);
			
			ResultSet rs = sqlupdate.executeQuery(sql);
		} catch(Exception e) {
			System.out.println("error thrown in searching");
			e.printStackTrace();
		}
		return 0;
	}
	
	
}
