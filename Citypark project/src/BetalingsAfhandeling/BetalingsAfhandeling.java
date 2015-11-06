package BetalingsAfhandeling;
import Database.Database;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private double hCount= 0.00;
    public double bedrag = 0.00;
    private int pasID;
    private static final String URL = "localhost:3306/citypark";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
	
	public BetalingsAfhandeling(int ID){
		try{	
			pasID = ID;
			init();
			firstQuery();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void init(){
		connection = new Database(URL, USERNAME, PASSWORD);
		conn = connection.getConnection();
	}
	
	public void firstQuery(){
		connection.query("Select Begintijd, Eindtijd FROM Inrijden WHERE Pas_Pas_ID = '"+pasID+"' ORDER BY Begintijd DESC");
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
        start = Timestamp.valueOf(sdf.format(cal.getTime()));
        HashMap<String, Object> tarief;
        while(!(finish.before(start))){
        	tarief = getTarief();
        	//System.out.println("Verstreken tijd: "+hCount+" uur"+"Bedrag: "+bedrag);
	        	if(bedrag < (double)tarief.get("max")){
		        	bedrag += (double)tarief.get("bedragpuur")/2;
		        	cal.add(Calendar.SECOND, 1800);
		        	start = Timestamp.valueOf(sdf.format(cal.getTime()));
	        	}

        	hCount = hCount + 0.5;
        }
        getBetaling();
	}
	
	public HashMap<String, Object> getTarief(){
		int zone = tariefZone(start, cal);
		HashMap<String, Object> tarief = new HashMap<String, Object>();
		if(tarief.isEmpty()){
		connection.query("Select tarief_id, bedragpuur, max FROM tarief where tarief_id = '"+zone+"'");
		list = connection.getResult();
		for(int i = 0; i < list.size(); i++) { 
			tarief.put("bedragpuur", (double)list.get(i).get("bedragpuur"));
			tarief.put("max", (double)list.get(i).get("max"));
		}
		}
		return tarief;
	}
	
	public int tariefZone(Timestamp t, Calendar c){
		int tariefZone = 0;
		Timestamp timeTemp = t;
		Calendar calTemp = c;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		String dbDate = sdf.format(start);	
		String dtmidnight = null;
		String dtmorning = null;
		String dtevening = null;
		Date date;
		
		try {
			dtmidnight = dbDate+ " " + "23:59:59.0";
			date = (Date) dateFormat.parse(dtmidnight);
			dtmidnight = dateFormat.format(date);
			dtmorning = dbDate+ " " + "06:00:00.0";
			date = (Date) dateFormat.parse(dtmorning);
			dtmorning = dateFormat.format(date);
			dtevening = dbDate+ " " + "20:00:00.0";
			date = (Date) dateFormat.parse(dtevening);
			dtevening = dateFormat.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Timestamp midnight = Timestamp.valueOf(dtmidnight);
		Timestamp morning = Timestamp.valueOf(dtmorning);
		Timestamp evening = Timestamp.valueOf(dtevening);
		if((timeTemp.after(midnight) && timeTemp.before(morning))||
		(timeTemp.after(evening) && (timeTemp.before(midnight))) &&
		(("Mon".equals(checkDay(calTemp.get(Calendar.DAY_OF_WEEK))) || "Tue".equals(checkDay(calTemp.get(Calendar.DAY_OF_WEEK))) 
				|| "Wed".equals(checkDay(calTemp.get(Calendar.DAY_OF_WEEK))) 	|| "Thu".equals(checkDay(calTemp.get(Calendar.DAY_OF_WEEK)))
				|| "Fri".equals(checkDay(calTemp.get(Calendar.DAY_OF_WEEK))))) && (!"Sun".equals(checkDay(calTemp.get(Calendar.DAY_OF_WEEK))) && !"Sat".equals(checkDay(calTemp.get(Calendar.DAY_OF_WEEK))))){
			tariefZone = 1;	
		}else if(("Sun".equals(checkDay(calTemp.get(Calendar.DAY_OF_WEEK))) || "Sat".equals(checkDay(calTemp.get(Calendar.DAY_OF_WEEK))))){
			tariefZone = 3;
		}else if(("Mon".equals(checkDay(calTemp.get(Calendar.DAY_OF_WEEK))) || "Tue".equals(checkDay(calTemp.get(Calendar.DAY_OF_WEEK))) 
				|| "Wed".equals(checkDay(calTemp.get(Calendar.DAY_OF_WEEK))) 	|| "Thu".equals(checkDay(calTemp.get(Calendar.DAY_OF_WEEK)))
				|| "Fri".equals(checkDay(calTemp.get(Calendar.DAY_OF_WEEK))))
				&& (timeTemp.after(morning) && (timeTemp.before(evening))) && (!"Sun".equals(checkDay(calTemp.get(Calendar.DAY_OF_WEEK))) && !"Sat".equals(checkDay(calTemp.get(Calendar.DAY_OF_WEEK))))){
			tariefZone = 2;
		}
		return tariefZone;
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
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();
		    long factor = (long) Math.pow(10, places);
		    value = value * factor;
		    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
	public double getBetaling(){
		bedrag = round(bedrag, 2);
		return bedrag;
	}
}
