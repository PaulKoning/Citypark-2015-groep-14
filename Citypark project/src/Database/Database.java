package Database;
import java.sql.*;

public class Database{ 
	private Connection conn;
	public Database(){	
	};
	public Connection giveConnection(){
		try{
			//Get Connection
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/citypark", "root", "");
		}
		catch(Exception x)
		{
		x.printStackTrace();	
		}
		return conn;
}	
}
//Create a statement
/* Database test
/Statement Stat = conn.createStatement();
//Try statement
ResultSet res = Stat.executeQuery("Select * FROM gebruiker");
//Print result
while(res.next()){
	System.out.println(res.getString("voornaam"));
}
*/