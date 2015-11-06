package pasHerkenning;

import main.Initialize;

public class PasHerkenning {
	public static void pasHerkenning(String s){
		String inrijdQuery = "SELECT inrijd_id FROM inrijden WHERE Pas_Pas_ID = 'STX EEC9193ACR LF'";
		if(Initialize.database.query(inrijdQuery)){
			System.out.println("query works");
		}else{
			System.out.println("failed query");
		}
		
		try {	
			if(s!=null) { 
				if (s.equals("STX EED6326ACR LF \n\r")) {
					System.out.println("Bank pas 1");
				}
				if (s.equals("STX D4F9374CCR LF \n\r")) {
					System.out.println("Bank pas 2");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
