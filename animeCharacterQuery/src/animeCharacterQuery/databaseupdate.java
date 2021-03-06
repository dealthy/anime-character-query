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
	
	public int characterid;
	
	public String firstname, lastname, animefrom;
	
	//sorting archieved information
	
	private String lastsearch;
	
	private String sortinfo = "firstname";
	
	private databasecon conn = new databasecon();
	
	public String[] searchadv() throws SQLException {
		
		
		String[] searchsuggestions = {"", ""};
		String sqlstring = "SELECT searchword FROM history GROUP BY searchword HAVING COUNT(*) = (SELECT MAX(Cnt) FROM(SELECT COUNT(*) as Cnt FROM history GROUP BY searchword ) tmp)";
		ResultSet rs = conn.getsql(sqlstring);
		searchsuggestions[0] = rs.getString("searchword");
		sqlstring = "select searchword from history order by recordid desc limit 1";
		rs = conn.getsql(sqlstring);
		searchsuggestions[1] = rs.getString("searchword");
		
		return searchsuggestions;
		
	}
	
	public DefaultTableModel display(String searchword) {

		try {
			
			//archieve the searchword for possible sorting mech change
			
			this.lastsearch = searchword;

			//receiving database information from search()

			ArrayList<ResultSet> resultsets = search(searchword, this.sortinfo);
			
			//pouring the data in from the ResultSet to the tempoarily ArrayList
			
			TreeSet<String> tempfirst = new TreeSet<String>();
			
			TreeSet<String> templast = new TreeSet<String>();
			
			TreeSet<String> tempanime = new TreeSet<String>();
			
			ArrayList<String> first = new ArrayList<String>();
			
			ArrayList<String> last = new ArrayList<String>();
			
			ArrayList<String> anime = new ArrayList<String>();
			
			int size = 0;

			for(ResultSet rs : resultsets){
				while (rs.next()) {
					size = tempfirst.size();
					tempfirst.add(rs.getString("firstname"));
					if(tempfirst.size() == size + 1) {
						first.add(rs.getString("firstname"));
					}
					System.out.println(rs.getString("firstname"));
					size = templast.size();
					templast.add(rs.getString("lastname"));
					if(templast.size() == size + 1) {
						last.add(rs.getString("lastname"));
					}
					System.out.println(rs.getString("lastname"));
					size = tempanime.size();
					tempanime.add(rs.getString("animefrom"));
					if(tempanime.size() == size + 1) {
						anime.add(rs.getString("animefrom"));
					}
					System.out.println(rs.getString("animefrom"));
				}

			}
			
			//fixing the size of the array
			
			Object[][] data = new Object[first.size()][3];
			
			//pouring the data from ArrayList to Array
			
			Iterator<String> a = first.iterator();
			Iterator<String> b = last.iterator();
			Iterator<String> c = anime.iterator();
			
			int i = 0;
			
			while(true) {
				data[i][0] = a.next();
				System.out.println(data[i][0]);
				data[i][1] = b.next();
				System.out.println(data[i][1]);
				data[i][2] = c.next();
				System.out.println(data[i][2]);
				if(a.hasNext() == false) {
					break;
				}
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

	public void create() throws SQLException {
		// TODO Auto-generated method stub
		try {
			String sqlstring = "insert into character (firstname, lastname, animefrom) values ('" + this.firstname + "', '" + this.lastname + "', '" + this.animefrom + "')";
			ResultSet rs = conn.getsql(sqlstring);
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public void delete() {
		// TODO Auto-generated method stub
		
	}

	public void update(String firstname, String lastname, String animefrom) {
		// TODO Auto-generated method stub
		
	}
	
	public void addhistory(String searchcontent) throws SQLException {
		
		System.out.println("in addhistory()");
		
		try {
			
			//sql statment
			
			String sqlstring = "insert into history (searchword) values ('" + searchcontent + "')";
			
			//execute sql query
			
			ResultSet rs = conn.getsql(sqlstring);
			
			System.out.println("added " + searchcontent);
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
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
				
				System.out.println(sqlstring);
				
				ResultSet rs = conn.getsql(sqlstring);
				
				System.out.println("back to mainpage");
				
				tablecontents.add(rs);
				
				//rs.close();
				
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
