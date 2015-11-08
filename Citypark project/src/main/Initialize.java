package main;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

import org.apache.axis.utils.StringUtils;

import pasHerkenning.PasHerkenning;
import BetalingsAfhandeling.PinView;
import Database.Database;
import mainReader.Main;

public class Initialize {
	private static final String URL = "localhost:3306/citypark";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	private static final int REKENINGNR_CITYPARK = 100000;
	private static Database databaseCitypark;
	private static Database databaseBank;
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
		StringUtils.stripEnd(Card_ID, null);
		
		//Pas_ID selecteren
		databaseCitypark.query("Select Pas_ID FROM pas WHERE Cardid = '"+Card_ID+"'");
		ArrayList<Map<String, Object>> res = databaseCitypark.getResult();
		int pas_id = 0;
		for(Map<String, Object> row : res) {
			pas_id =  (int) row.get("Pas_ID");
			System.out.println(pas_id);
		}
		//Alle begin en eindtijden selecteren van het geselecteerde Pas_ID
		databaseCitypark.query("Select Begintijd, Eindtijd FROM inrijden WHERE Pas_Pas_ID = '"+pas_id+"' ORDER BY Begintijd DESC");
		res = databaseCitypark.getResult();
		Timestamp eindtijd;
		for(Map<String, Object> row : res) {
			eindtijd =  (Timestamp) row.get("Eindtijd");
			System.out.println("Eindtijd: "+eindtijd);
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
		
		String substr = "Bank";
		int tmp = pas.indexOf(substr);
		if(tmp == 0){
			//new PinView();
		}
	}
}
