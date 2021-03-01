package animeCharacterQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.table.DefaultTableModel;

public interface databaseinfo {
	
	public abstract DefaultTableModel display(String searchword) throws SQLException;
	public abstract void create(String firstname, String lastname, String animefrom) throws SQLException;
	public abstract void delete() throws SQLException;
	public abstract void update(String firstname, String lastname, String animefrom) throws SQLException;
	public abstract ResultSet search(String searchword, String sortinfo) throws SQLException;
	//return characterid
	
}
