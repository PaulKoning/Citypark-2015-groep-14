package Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Database { 
	private Connection conn;
	private ResultSet res;
	
	public Database() {	
		conn = getConnection();
	};
	
	public Connection getConnection() {
		try {
			//Get Connection
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/citypark", "root", "");
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
	
	public boolean query(String q) {
		try {
			Statement stat = conn.createStatement();
			res = stat.executeQuery(q);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	public boolean update(String q) {
		try {
			Statement stat = conn.createStatement();
			stat.executeUpdate(q);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
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
