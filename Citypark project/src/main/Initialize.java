package main;
import java.rmi.RemoteException;
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
	private static Database database;
	public Initialize() {
	    
		try{
		database = new Database(URL, USERNAME, PASSWORD);
		new Main();	// start de main van PROMAGReader				
		}
		catch(Exception x){
			x.printStackTrace();
		}
	}
	
	public static void PoortOfPin(String pas){
		String Card_ID = pas.replace("\n", "").replace("\r", "");
		StringUtils.stripEnd(Card_ID, null);
		database.query("SELECT Gebruiker_Gebruiker_ID FROM pas WHERE Cardid = '"+Card_ID+"'");
		System.out.println(PasHerkenning.pasHerkenning(pas));
		ArrayList<Map<String, Object>> res = database.getResult();
		for(Map<String, Object> row : res) {
			int tmp =  (int) row.get("Gebruiker_Gebruiker_ID");
			System.out.println(tmp);
		}
		
		String substr = "Bank";
		int tmp = pas.indexOf(substr);
		if(tmp == 0){
			//new PinView();
		}
	}
}
