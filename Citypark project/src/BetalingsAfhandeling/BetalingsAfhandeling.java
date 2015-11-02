package BetalingsAfhandeling;
import Database.Database;
import java.sql.*;

public class BetalingsAfhandeling {
	private Database connection;
	public Connection conn;
	
	public BetalingsAfhandeling(){
		System.out.println("hello world");
		init();
		rekentest();
	}
	
	public void init(){
		connection = new Database();
		conn = connection.getConnection();
		System.out.println("Wtf? werkt het?");
	}
	public int rekentest(){
		if(conn == null){
			System.out.println("Geen db connectie");
		}
		try {
			Statement stat = conn.createStatement();
			ResultSet res = stat.executeQuery("Select * FROM gebruiker");
			while(res.next()){
				System.out.println(res.getString("voornaam"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Rekentest nope?");
		return 0;
	}
}
