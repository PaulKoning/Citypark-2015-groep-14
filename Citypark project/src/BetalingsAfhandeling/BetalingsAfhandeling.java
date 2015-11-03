package BetalingsAfhandeling;
import Database.Database;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class BetalingsAfhandeling {
	private Database connection;
	public Connection conn;
	private ArrayList<Map<String, Object>> list;
	private Timestamp start;
	private Timestamp finish;
    private Calendar cal;
	
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
		start = begin;
		finish = eind;
        cal = Calendar.getInstance();
        cal.setTimeInMillis(start.getTime());

        System.out.println("Startijd begin "+start);        
        start = Timestamp.valueOf(sdf.format(cal.getTime()));
        
        while(!(finish.before(start))){
        	
        	cal.add(Calendar.SECOND, 1800);
        	start = Timestamp.valueOf(sdf.format(cal.getTime()));
        }
        System.out.println("Starttijd eind "+start);
        System.out.println("Datbase eindtijd "+finish);
	}
	
	public HashMap<String, Double> getTarief(){
		int zone = tariefZone(start, cal);
		connection.query("Select tarief_id, bedragpuur, max FROM '"+zone+"' ");
		list = connection.getResult();
		HashMap<String, Double> tarief = new HashMap<String, Double>();
		for(int i = 0; i < list.size(); i++) { 
			tarief.put("tarief_id", (Double)list.get(i).get("tarief_id"));
			tarief.put("bedragpuur", (Double)list.get(i).get("bedragpuur"));
			tarief.put("max", (Double)list.get(i).get("max"));
		}
		return tarief;
	}
	public int tariefZone(Timestamp t, Calendar c){
		Timestamp timeTemp = t;
		Calendar calTemp = c;
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date;
		try {
			date = (Date) dateFormat.parse("00:00:00");
			long time = date.getTime();
			new Timestamp(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(true){
			
		}
		return 0;
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
