package animeCharacterQuery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import java.sql.*;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.table.DefaultTableModel;
import java.util.*;

public class databaseupdate {
	
	//selected characterinfo
	
	private int characterid;
	
	private String firstname, lastname, animefrom;
	
	//sorting archieved information
	
	private String lastsearch;
	
	private String sortinfo = "firstname";
	
	//database location
	
	private String db = "jdbc:sqlite:/Volumes/SHARE/ComputerScience/HarryYu-IBCSdatabaseMiniProject/animeCharacterQuery/character.db";
	
	public Connection conn = getcon();
	
	
	public Connection getcon() throws Exception {
		Connection con = DriverManager.getConnection(this.db);
		return con;
	}
	
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

			ArrayList<ResultSet> resultsets = search(searchword, this.sortinfo);
			
			//pouring the data in from the ResultSet to the tempoarily ArrayList
			
			HashSet<String> tempfirst = new HashSet<String>();
			
			HashSet<String> templast = new HashSet<String>();
			
			HashSet<String> tempanime = new HashSet<String>();

			for(ResultSet rs : resultsets){
				
				//temp.add(new Object[]{rs.getString("firstname"), rs.getString("lastname"), rs.getString("animefrom")});
				tempfirst.add(rs.getString("firstname"));
				templast.add(rs.getString("lastname"));
				tempanime.add(rs.getString("animefrom"));

			}
			
			//fixing the size of the array
			
			Object[][] data = new Object[tempfirst.size()][3];
			
			//pouring the data from ArrayList to Array
			
			Object[] container = {"", "", ""};
			
			Iterator<String> a = tempfirst.iterator();
			Iterator<String> b = templast.iterator();
			Iterator<String> c = tempanime.iterator();
			
			int i = 0;
			
			while(a.hasNext()) {
				data[i][0] = a.next();
				data[i][1] = b.next();
				data[i][2] = c.next();
				i++;
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
	
	public void addhistory(String searchcontent) throws SQLException {
		
		try {
			
			//sql statment
			
			String sqlstring = "insert into history (searchword) values ('" + searchcontent + "')";
			
			//execute sql query
			
			ResultSet rs = getconn(sqlstring);
			
			System.out.println("added" + searchcontent);
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	public ResultSet getconn(String sql) throws SQLException {
		
		
		ResultSet rs = null;
		
		try {
			
			//creating statement
			
			Statement sqlstring = getcon().createStatement();
			
			//execute sql query
			
			rs = sqlstring.executeQuery(sql);
			
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return rs;
		
	}

	public ArrayList<ResultSet> search(String searchword, String sortinfo) throws SQLException {
		
		//collecting all the search words in the search query
		
		searchword = searchword + " ";
		
		ArrayList<String> searchwordlist = new ArrayList<String>();
		ArrayList<Integer> spaceloc = new ArrayList<Integer>();
		
		int prev = 0;
		
		System.out.println(searchword);
		
		for (int i = 0; i < searchword.length(); i++) {
			if(searchword.substring(i, i + 1).equals(" ")) {
				spaceloc.add(Integer.valueOf(i));
			}
		}
		
		for(int i = 0; i < spaceloc.size(); ++i) {
			searchwordlist.add(searchword.substring(prev, spaceloc.get(i)));
			prev = spaceloc.get(i) + 1;
			System.out.println(searchwordlist.get(i));
		}
		
		if(searchwordlist.size() == 0) {
			System.out.println("nothing");
			searchwordlist.add("");
		}
		
		//using the sql library and return an arraylist of resultsets
		
		ArrayList<ResultSet> tablecontents = new ArrayList<ResultSet>();
		
		
		for (int i = 0; i < searchwordlist.size(); ++i) {
			
			try {
				
				if (sortinfo != "firstname" && sortinfo != "lastname" && sortinfo != "animefrom") {
					System.out.println("error input");
					return null;
				}
				
				
				String sqlstring = "SELECT * FROM character WHERE firstname LIKE '%" + searchwordlist.get(i) + "%' OR lastname LIKE '%" + searchwordlist.get(i) + "%' OR animefrom LIKE '%" + searchwordlist.get(i) + "%' ORDER BY " + sortinfo + " ASC";
				
				
				ResultSet rs = getconn(sqlstring);
				
				tablecontents.add(rs);
				
				System.out.println("searching");
				
				
				
			} catch(Exception e) {
				
				//standard error commands 
				
				System.out.println("error thrown in searching");
				e.printStackTrace();
			}
			
			
		}
			
		return tablecontents;
		
		
		
		
	}
	
	
}
