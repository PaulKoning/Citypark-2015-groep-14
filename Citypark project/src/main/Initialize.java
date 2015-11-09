package main;
import gui.MainScreen;

import java.rmi.RemoteException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import org.apache.axis.utils.StringUtils;

import pasHerkenning.PasHerkenning;
import threading.Out;
import BetalingsAfhandeling.PinView;
import Database.Database;
import mainReader.Main;

public class Initialize {
	private static final String URL = "localhost:3306/citypark";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	private static final int REKENINGNR_CITYPARK = 100000;
	private static Database databaseCitypark;
	private Database databaseBank;
	private static Timestamp tijd;
	
	public Initialize() {
	    
		try{
		databaseCitypark = new Database(URL, USERNAME, PASSWORD);
		databaseBank = new Database("localhost:3306/bank", USERNAME, PASSWORD);
		new Main();	// start de main van PROMAGReader				
		}
		catch(Exception x){
			x.printStackTrace();
		}
	}
	
	public static void PoortOfPin(String pas){
		//de string pas formatteren zodat die bruikbaar is
		String Card_ID = pas.replace("\n", "").replace("\r", "");
		StringUtils.stripEnd(Card_ID, null); //Card_ID is de raw kaart id
		
		//Pas_ID selecteren
		databaseCitypark.query("Select Pas_ID FROM pas WHERE Cardid = '"+Card_ID+"'");
		ArrayList<Map<String, Object>> res = databaseCitypark.getResult();
		int pas_id = 0; // dit is de id van de pas
		for(Map<String, Object> row : res) {
			pas_id =  (int) row.get("Pas_ID");
			System.out.println(pas_id);
		}
		
		//PasType selecteren
		databaseCitypark.query("Select Pastype_Pastype_ID FROM pas WHERE Cardid = '"+Card_ID+"'");
		res = databaseCitypark.getResult();
		int pas_type = 0; // dit is de id van de pas
		for(Map<String, Object> row : res) {
			pas_type =  (int) row.get("Pastype_Pastype_ID");
			System.out.println(pas_type);
		}		
		
		//Alle begin en eindtijden selecteren van het geselecteerde Pas_ID
		databaseCitypark.query("Select Begintijd, Eindtijd FROM inrijden WHERE Pas_Pas_ID = '"+pas_id+"' ORDER BY Begintijd DESC");
		res = databaseCitypark.getResult();
		Timestamp eindtijd = null;
		Timestamp begintijd = null;
		for(Map<String, Object> row : res) {
			eindtijd =  (Timestamp) row.get("Eindtijd");
			begintijd =  (Timestamp) row.get("Begintijd");
		}
		//abbonomenten id zoeken
		databaseCitypark.query("Select Abbonoment_ID FROM abbonementen WHERE Pas_Pas_ID = '"+pas_id+"'");
		res = databaseCitypark.getResult();
		int abbonomenten_id = 0;
		for(Map<String, Object> row : res) {
			abbonomenten_id =  (int) row.get("Abbonoment_ID");
		}
		
		//timestamp aanmaken van huidige tijd
	     java.util.Date date= new java.util.Date();
	     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	     String formattedDate = sdf.format(date);
		//als er geen begin of eind tijden zijn voor de gebruiker, insert dan een nieuwe record met de huidige tijd
		if(begintijd == null){ 
			String insertQueryMet = ("INSERT INTO inrijden (Begintijd, Eindtijd, Betaald, Abbonementen_Abbonoment_ID, Pas_Pas_ID) VALUES ('"+formattedDate+"',NULL, 0,'"+abbonomenten_id+"','"+pas_id+"')");
			String insertQueryZonder = ("INSERT INTO inrijden (Begintijd, Eindtijd, Betaald, Pas_Pas_ID) VALUES ('"+formattedDate+"',NULL, 0, '"+pas_id+"')");
			if(abbonomenten_id==0){
				if(databaseCitypark.update(insertQueryZonder)){
					System.out.println("Poort gaat nu open. Prettige dag verder!");
					try{
					MainScreen.out.beep(); //beep als de poort open gaat
					}catch(Exception e){}				
				}else{
						System.out.println("error query zonder");
				}	
			}else{
				databaseCitypark.update(insertQueryMet);
				System.out.println("Poort gaat nu open. Prettige dag verder!");
				try{
				MainScreen.out.beep(); //beep als de poort open gaat
				}catch(Exception e){}
			}
		}	
		
		//Rekeningsnummer selecteren van de Card_ID
		if(databaseCitypark.query("SELECT Gebruiker_Gebruiker_ID FROM pas WHERE Cardid = '"+Card_ID+"'")){
			System.out.println(PasHerkenning.pasHerkenning(pas));
			
			res = databaseCitypark.getResult();
			int gebruiker_id = 0;
			for(Map<String, Object> row : res) {
				gebruiker_id =  (int) row.get("Gebruiker_Gebruiker_ID");
				System.out.println(gebruiker_id);
			}
			databaseCitypark.query("SELECT Rekeningsnummer FROM gebruiker WHERE Gebruiker_ID = '"+gebruiker_id+"'");
			res = databaseCitypark.getResult();
			int rekeningsnummer = 0;
			for(Map<String, Object> row : res) {
				rekeningsnummer =  (int) row.get("Rekeningsnummer");
				System.out.println(rekeningsnummer);
			}
		}
	}
}
