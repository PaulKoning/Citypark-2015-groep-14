package pasHerkenning;

import java.util.ArrayList;
import java.util.Map;

import org.apache.axis.utils.StringUtils;

import Database.Database;

public class PasHerkenning {
	private static String pas;
    private static final String URL = "localhost:3306/citypark";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
	
	public static String pasHerkenning(String s) {
		Database database = new Database(URL, USERNAME, PASSWORD);
		
		String Card_ID = s.replace("\n", "").replace("\r", "");
		StringUtils.stripEnd(Card_ID, null);		
				
		try {	
			if(s!=null) { 
				if (Card_ID.equals("STX EED6326ACR LF ")) {
					pas = "Bank pas 1";
					return pas;
				}
				if (Card_ID.equals("STX D4F9374CCR LF ")) {
					pas = "Bank pas 2";
					return pas;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String inrijdQuery = "SELECT Pas_ID FROM pas WHERE Cardid = '"+Card_ID+"'";
		if(database.query(inrijdQuery)){
			String pasQuery = "SELECT Pastype_Pastype_ID FROM pas WHERE Cardid = '"+Card_ID+"'";
			database.query(pasQuery);
			int pasType_ID = 0;
			ArrayList<Map<String, Object>> res = database.getResult();
			for(Map<String, Object> row : res) {
				pasType_ID =  (int) row.get("Pastype_Pastype_ID");
			}
			
		      //List<Object> list=new ArrayList<Object>();
		      //for(Map<String,Object> i:resultList){
		      //    list.addAll(i.values());
		     // }
		      //String ID = list.toString();
		      //String ID2 = ID.replace("[", "");
		      //String Pas_ID = ID2.replace("]", "");
		      
		      database.query("SELECT Beschrijving_Type FROM pastype WHERE Pastype_ID = '"+pasType_ID+"'");
		      
		      ArrayList<Map<String, Object>> res2 = database.getResult();
				for(Map<String, Object> row : res2) {
					pas =  (String) row.get("Beschrijving_Type");
				}
		      
		      //list.clear();   // gooit List leeg
		      //for(Map<String,Object> i:resultList){
		      //    list.addAll(i.values());
		      //}
		      //String pas = list.toString();
		      //String pas2 = pas.replace("[", "");
		      //String PasType = pas2.replace("]", "");
		      return pas;
		      
		}else{
			System.out.println("failed query");
		}
		pas = "Geen pas gevonden";
		return pas;
	}
}
