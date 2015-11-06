package pasHerkenning;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.axis.utils.StringUtils;

import main.Initialize;

public class PasHerkenning {
	private static String pas;
	public static String pasHerkenning(String s){
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
		if(Initialize.database.query(inrijdQuery)){
			String pasQuery = "SELECT Pastype_Pastype_ID FROM pas WHERE Cardid = '"+Card_ID+"'";
			Initialize.database.query(pasQuery);
			ArrayList<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
			resultList =  Initialize.database.getResult();
			

		      List<Object> list=new ArrayList<Object>();
		      for(Map<String,Object> i:resultList){
		          list.addAll(i.values());
		      }
		      String ID = list.toString();
		      String ID2 = ID.replace("[", "");
		      String Pas_ID = ID2.replace("]", "");
		      
		      String pasTypeQuery = "SELECT Beschrijving_Type FROM pastype WHERE Pastype_ID = '"+Pas_ID+"'";
		      Initialize.database.query(pasTypeQuery);
		      resultList = Initialize.database.getResult();
		      list.clear();   // gooit List leeg
		      for(Map<String,Object> i:resultList){
		          list.addAll(i.values());
		      }
		      String pas = list.toString();
		      String pas2 = pas.replace("[", "");
		      String PasType = pas2.replace("]", "");		 
		      return PasType;
		}else{
			System.out.println("failed query");
		}
		pas = "Geen pas gevonden";
		return pas;
	}
}
