package animeCharacterQuery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.sql.*;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.table.DefaultTableModel;

public class databaseupdate {
	
	//selected characterinfo
	
	private int characterid;
	
	private String firstname, lastname, animefrom;
	
	//sorting archieved information
	
	private String lastsearch;
	
	private String sortinfo = "firstname";
	
	//database location
	
	private String db = "jdbc:sqlite:/Volumes/SHARE/ComputerScience/HarryYu-IBCSdatabaseMiniProject/animeCharacterQuery/character.db";
	
	public databaseupdate() {
		
		//open database connection
		try {
			Class.forName("org.sqlite.JDBC");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public DefaultTableModel display(String searchword) {

		try {
			
			//archieve the searchword for possible sorting mech change
			
			this.lastsearch = searchword;

			//receiving database information from search()

			ResultSet rs = search(searchword, this.sortinfo);
			
			//pouring the data in from the ResultSet to the tempoarily ArrayList
			
			ArrayList<Object[]> temp = new ArrayList<Object[]>();

			while(rs.next()){
				
				temp.add(new Object[]{rs.getString("firstname"), rs.getString("lastname"), rs.getString("animefrom")});

			}
			
			//fixing the size of the array
			
			Object[][] data = new Object[temp.size()][3];
			
			//pouring the data from ArrayList to Array
			
			Object[] container = {"", "", ""};
			
			for(int i = 0; i < temp.size(); i++) {
				container = temp.get(i);
				data[i][0] = container[0];
				System.out.print(container[0] + " ");
				data[i][1] = container[1];
				System.out.print(container[1] + " ");
				data[i][2] = container[2];
				System.out.print(container[2] + " ");
			}
	
			//establishing a DefaultTableModel type to import data into JList
			
			DefaultTableModel model = new DefaultTableModel(data, new Object[] {"firstname", "lastname", "originanime"});
			
			
			return model;


		} catch (Exception f) {

			//standard error detection
			
			f.printStackTrace();
			System.out.println("error thrown in loading sql");
			return new DefaultTableModel();

		}
	}
	
	public DefaultTableModel sort(String sortinfo) {
		this.sortinfo = sortinfo;
		return this.display(this.lastsearch);
	}

	public void create(String firstname, String lastname, String animefrom) {
		// TODO Auto-generated method stub
		
	}

	public void delete() {
		// TODO Auto-generated method stub
		
	}

	public void update(String firstname, String lastname, String animefrom) {
		// TODO Auto-generated method stub
		
	}

	public ResultSet search(String searchword, String sortinfo) {
		
		Connection conn;
		
		try {
			
			//prevent error input
			
			if (sortinfo != "firstname" && sortinfo != "lastname" && sortinfo != "animefrom") {
				System.out.println("error input");
				return null;
			}
			
			//establish connection
			
			conn = DriverManager.getConnection(this.db); 
			
			//sql statment
			
			String sqlstring = "SELECT * FROM character WHERE firstname LIKE '%" + searchword + "%' OR lastname LIKE '%" + searchword + "%' OR animefrom LIKE '%" + searchword + "%' ORDER BY '" + sortinfo + "'";
			
			System.out.println(sqlstring);
			
			//transfer into PreparedStatement form
			
			Statement sql = conn.createStatement();
			
			//filling in the prepared gaps with searchword
			/*
			sql.setString(1, "%" + searchword + "%");
			sql.setString(2, "%" + searchword + "%");
			sql.setString(3, "%" + searchword + "%");
			sql.setString(4, sortinfo);
			*/
			
			//execute sql query
			
			ResultSet rs = sql.executeQuery(sqlstring);
			
			return rs;
			
		} catch(Exception e) {
			
			//standard error commands 
			
			System.out.println("error thrown in searching");
			e.printStackTrace();
		}
		return null;
	}
	
	
}
