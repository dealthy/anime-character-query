package animeCharacterQuery;

import java.sql.SQLException;
import javax.swing.DefaultListModel;
import javax.swing.JList;

public interface databaseinfo {
	
	public abstract DefaultListModel display(String searchword, JList table) throws SQLException;
	public abstract void create(String firstname, String lastname, String animefrom) throws SQLException;
	public abstract void delete() throws SQLException;
	public abstract void update(String firstname, String lastname, String animefrom) throws SQLException;
	public abstract int search(String searchword) throws SQLException; 
	//return characterid
	
}
