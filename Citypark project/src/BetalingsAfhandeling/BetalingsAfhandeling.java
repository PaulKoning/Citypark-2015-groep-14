package BetalingsAfhandeling;
import Database.Database;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class BetalingsAfhandeling {
	private Database connection;
	public Connection conn;
	private ArrayList<Map<String, Object>> list;
	
	public BetalingsAfhandeling(){
		init();
		rekentest();
	}
	
	public void init(){
		connection = new Database();
		conn = connection.getConnection();
	}
	public void rekentest(){
		/*try {
			Statement stat = conn.createStatement();
			ResultSet res = stat.executeQuery("Select * FROM gebruiker");
			while(res.next()){
				System.out.println(res.getString("voornaam"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		connection.query("Select Begintijd, Eindtijd FROM Inrijden WHERE Pas_Pas_ID = 1315");
		list = connection.getResult();		
		for(int i = 0; i < list.size(); i++) {   
			nextInc((Timestamp)list.get(i).get("Begintijd"), (Timestamp)list.get(i).get("Eindtijd"));
			 
		}
	}
	public void nextInc(Timestamp begin, Timestamp eind){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat day = new SimpleDateFormat("EEEEE");	
		Timestamp start = begin;
		Timestamp finish = eind;
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(start.getTime());

        System.out.println("Startijd begin "+start);        
        start = Timestamp.valueOf(sdf.format(cal.getTime()));
        
        while(!(eind.before(start))){
        	cal.add(Calendar.SECOND, 1800);
            System.out.println(checkDay(cal.get(Calendar.DAY_OF_WEEK)));
        	start = Timestamp.valueOf(sdf.format(cal.getTime()));
        }
        System.out.println("Starttijd eind "+start);
        System.out.println("Datbase eindtijd "+eind);
        System.out.println(day.format(cal.get(Calendar.DAY_OF_WEEK)));
	
	}
	public String checkDay(int a){
		int dcount = a;
		String day = null;
		switch (dcount) {
        case 1:  day = "Sun";
                 break;
        case 2:  day = "Mon";
                 break;
        case 3:  day = "Tue";
                 break;
        case 4:  day = "Wed";
                 break;
        case 5:  day = "Thu";
                 break;
        case 6:  day = "Fri";
                 break;
        case 7:  day = "Sat";
                 break;
        default: day = "Unknown";
                 break;
    }
		return day;
	}
	public int checkTarief(int tijd){
		
		return 0;
	}
	public int getBetaling(){
		return 0;
	}
}
