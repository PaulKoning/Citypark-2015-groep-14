package bank;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Koen Berghuis, Paul Koning
 * Klasse om verbinding te maken met een SQL database en hier queries op uit te voeren
 */
public class Database { 
	private Connection conn;
	private ResultSet res;
	private String url;
	private String user;
	private String password;
	
	/**
	 * Maakt verbinding met de gegeven database
	 * @param url De url van de database
	 * @param user
	 * @param password
	 */
	public Database(String url, String user, String password) {	
		this.url = url;
		this.user = user;
		this.password = password;
		conn = getConnection();
	};
	
	/**
	 * Maakt verbinding met de database
	 * @returns De gemaakte verbinding
	 */
	public Connection getConnection() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://" + url, user, password);
			if(conn == null) {
				System.out.println("Conn in Class Db = null");
			}
			return conn;
		}
		catch(Exception e) {
			e.printStackTrace();	
		}
		return null;
	}	
	
	/**
	 * Voer een query uit op de database. Dit kunnen alleen queries zijn de data lezen, niet aanpassen.
	 * 
	 * @param String met de uit te voeren query
	 * @returns Boolean of de query geslaagd is of niet
	 */
	public boolean query(String q) {
		try {
			Statement stat = conn.createStatement();
			res = stat.executeQuery(q);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	/**
	 * Voer een query uit op de database. Dit kunnen alleen queries zijn de data aanpassen, niet lezen.
	 * 
	 * @param String met de uit te voeren query
	 * @returns Boolean of de query geslaagd is of niet
	 */
	public boolean update(String q) {
		try {
			Statement stat = conn.createStatement();
			stat.executeUpdate(q);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	/**
	 * Geeft de resultatenset van de laatst uitgevoerde query terug
	 * @return ArrayList met de rijen van de resultatenset
	 */
	public ArrayList<Map<String, Object>> getResult() {
		ArrayList<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		HashMap<String, Object> row = null;
		try {
			int columns = res.getMetaData().getColumnCount();
			while(res.next()) {
				row = new HashMap<String, Object>();
				for(int i = 1; i <= columns; i++) {
					row.put(res.getMetaData().getColumnName(i), res.getObject(i));
				}
				resultList.add(row);
			}
			return resultList;
		} catch (SQLException e) {
			return null;
		}
	}
	
	/**
	 * Sluit de verbinding met de database
	 * @returns Boolean of het sluiten is geslaagd
	 */
	public boolean close() {
		try {
			if(conn != null) conn.close();
			if(res != null) res.close();
			return true;
		} catch(SQLException e) {
			return false;
		}
	}
}
