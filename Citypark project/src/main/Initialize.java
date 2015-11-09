package main;
import gui.MainScreen;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

import org.apache.axis.utils.StringUtils;

import pasHerkenning.PasHerkenning;
import BetalingsAfhandeling.BetalingsAfhandeling;
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
	private static ArrayList<Map<String, Object>> res;
	private static double bedrag;
	private static boolean betaald;
	
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
		String Card_ID = pas.replace("\n", "").replace("\r", "");		
		StringUtils.stripEnd(Card_ID, null); //Card_ID is de raw kaart id
		int pas_id = getPasId(Card_ID);	
		int pas_type = getPasType(pas_id);	
		if (Card_ID.equals("STX EED6326ACR LF ") || Card_ID.equals("STX D4F9374CCR LF ")) {
			System.out.println("bank");
			afrekenen(pas_id,Card_ID, bedrag);
		}
		//Alle begin en eindtijden selecteren van het geselecteerde Pas_ID
		databaseCitypark.query("Select Begintijd, Eindtijd FROM inrijden WHERE Pas_Pas_ID = '"+pas_id+"' ORDER BY Begintijd ASC");
		res = databaseCitypark.getResult();
		Timestamp eindtijd = null;
		Timestamp begintijd = null;
		//for(Map<String, Object> row : res) {
		for(int i = 0; i < res.size(); i++) { 
			eindtijd =  (Timestamp) res.get(i).get("Eindtijd");
			begintijd =  (Timestamp) res.get(i).get("Begintijd");
		}		
		//timestamp aanmaken van huidige tijd
		java.util.Date date= new java.util.Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String formattedDate = sdf.format(date);
	    Timestamp test = Timestamp.valueOf(formattedDate);
		//als er geen begin of eind tijden zijn voor de gebruiker, insert dan een nieuwe record met de huidige tijd
	     if(eindtijd != null || res.isEmpty()){
	    	 try{
				if(begintijd == null || (test.after(eindtijd))){
					databaseCitypark.update("SELECT Uren_Dezeweek FROM abbonementen WHERE Pas_Pas_ID = '"+pas_id+"'");
					int uren_dezeweek = 0;
					res = databaseCitypark.getResult();
				     for(Map<String, Object> row : res) {
				    	 uren_dezeweek =  (int) row.get("Uren_Dezeweek");	     
				     }	
				     //abbonomenten id zoeken
				     databaseCitypark.query("Select Abbonoment_ID FROM abbonementen WHERE Pas_Pas_ID = '"+pas_id+"' AND Betaald = 1");
				     res = databaseCitypark.getResult();
				     int abbonomenten_id = 0;
				     for(Map<String, Object> row : res) {
				    	 abbonomenten_id =  (int) row.get("Abbonoment_ID");	     
				     }
				     String insertQueryMet = ("INSERT INTO inrijden (Begintijd, Eindtijd, Betaald, Abbonementen_Abbonoment_ID, Pas_Pas_ID) VALUES ('"+formattedDate+"',NULL, 0,'"+abbonomenten_id+"','"+pas_id+"')");
					String insertQueryZonder = ("INSERT INTO inrijden (Begintijd, Eindtijd, Betaald, Pas_Pas_ID) VALUES ('"+formattedDate+"',NULL, 0, '"+pas_id+"')");		
					if(pas_type==4 && uren_dezeweek <32){
						if(databaseCitypark.update(insertQueryMet)){
							System.out.println("Welkom bezoeker! Poort gaat nu open. Parkeer uw auto.");
							try{
							MainScreen.out.beeps(); //beep als de poort open gaat
							}catch(Exception e){}				
						}else{
							System.out.println("Deze bezoekerpas is niet gekoppeld aan een abbonement!");
						}
					}
								
					if(abbonomenten_id==0 && pas_type == 1){
						if(databaseCitypark.update(insertQueryZonder)){
							System.out.println("Welkom! Poort gaat nu open. Parkeer uw auto.");
							try{
							MainScreen.out.beeps(); //beep als de poort open gaat
							}catch(Exception e){}				
						}else{
								System.out.println("error query zonder");
						}	
					}else if(abbonomenten_id != 0 && pas_type != 4){
						databaseCitypark.update(insertQueryMet);
						System.out.println("Welkom abonnee! Poort gaat nu open. Parkeer uw auto.");
						try{
						MainScreen.out.beeps(); //beep als de poort open gaat
						}catch(Exception e){}
					}else if(abbonomenten_id==0 && pas_type == 2){
						System.out.println("Uw abbonement is niet betaald.");
					}
					
				}
	    	 }catch(Exception e){
	    		 uitrijden(pas_id,pas_type,Card_ID);
	    	 }
			
		}else if(eindtijd == null){
			uitrijden(pas_id,pas_type, Card_ID);
		}
		
		//Rekeningsnummer selecteren van de Card_ID
		if(databaseCitypark.query("SELECT Gebruiker_Gebruiker_ID FROM pas WHERE Cardid = '"+Card_ID+"'")){			
			res = databaseCitypark.getResult();
			int gebruiker_id = 0;
			for(Map<String, Object> row : res) {
				gebruiker_id =  (int) row.get("Gebruiker_Gebruiker_ID");
			}
			databaseCitypark.query("SELECT Rekeningsnummer FROM gebruiker WHERE Gebruiker_ID = '"+gebruiker_id+"'");
			res = databaseCitypark.getResult();
			int rekeningsnummer = 0;
			for(Map<String, Object> row : res) {
				rekeningsnummer =  (int) row.get("Rekeningsnummer");
			}
		}
	}
	private static int getPasType(int pas){				
		//PasType selecteren
		int pas_type = 0;
		try{
			databaseCitypark.query("Select Pastype_Pastype_ID FROM pas WHERE Pas_ID = '"+pas+"'");
			res = databaseCitypark.getResult();
			 // dit is de id van de pas
			for(Map<String, Object> row : res) {
				pas_type =  (int) row.get("Pastype_Pastype_ID");
			}		
		}catch(Exception e){
			e.printStackTrace();
		}
		return pas_type;
		}
	private static int getPasId(String pas){
		int pas_id = 0;
		try{
			String Card_ID = pas.replace("\n", "").replace("\r", "");		
			StringUtils.stripEnd(Card_ID, null); //Card_ID is de raw kaart id		
			//Pas_ID selecteren
			databaseCitypark.query("Select Pas_ID FROM pas WHERE Cardid = '"+Card_ID+"'");
			res = databaseCitypark.getResult();
			 // dit is de id van de pas
			for(Map<String, Object> row : res) {
				pas_id =  (int) row.get("Pas_ID");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return pas_id;
	}
	
	private static void uitrijden(int pas_id, int pas_type, String Card_ID){	
		java.util.Date date= new java.util.Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String formattedDate = sdf.format(date);
		databaseCitypark.update("UPDATE inrijden SET eindtijd='"+formattedDate+"', betaald = 1 WHERE Pas_Pas_ID='"+pas_id+"';");
		if(pas_type == 1){
			
			BetalingsAfhandeling betaling = new BetalingsAfhandeling(pas_id);
			bedrag = betaling.getBetaling();
			databaseCitypark.update("UPDATE inrijden SET eindtijd=null, betaald = 0 WHERE Pas_Pas_ID='"+pas_id+"';");
			System.out.println("Te betalen bedrag: "+bedrag+"\nVoer uw bank pas in.");			
			afrekenen(pas_id,Card_ID,bedrag);
		}else{
			poortOpenen();
		}
	}
	
	private static void poortOpenen(){
		System.out.println("Poort gaat open. Fijne dag verder en tot ziens!");
		try{
			MainScreen.out.beeps(); //beep als de poort open gaat
		}catch(Exception e){}
	}
	
	private static void afrekenen(int pas_id, String Card_ID, double bedrag){
		java.util.Date date= new java.util.Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String formattedDate = sdf.format(date);
		if((Card_ID.equals("STX EED6326ACR LF ") || Card_ID.equals("STX D4F9374CCR LF ")) && bedrag > 0.00){
			try{
				int rekeningnmr =0;
				if(Card_ID.equals("STX EED6326ACR LF ")){rekeningnmr = 459;}
				else if(Card_ID.equals("STX D4F9374CCR LF ")){rekeningnmr = 111;}
				new PinView(rekeningnmr, bedrag);
				databaseCitypark.update("UPDATE inrijden SET eindtijd='"+formattedDate+"', betaald = 1 WHERE Pas_Pas_ID='"+pas_id+"';");
			}catch(Exception e){
				e.getStackTrace();
				System.out.println("Connectie met de bank kon niet gelegd worden.");
			}
		}
	}
}

