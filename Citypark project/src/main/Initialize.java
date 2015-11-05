package main;
import nl.hanze.promagreader.main.Runner;
import BetalingsAfhandeling.BetalingsAfhandeling;
import Database.Database;

public class Initialize {
	public Initialize() {
		String [] args = new String[1];
	    args[0] = "hello";
	    
		try{
		Runner.main(args);	// start de runner van PROMAGReader
		
		Double Test = new BetalingsAfhandeling(1315).getBetaling();	//1315 is een tijdelijke testwaarde voor de constructor
		System.out.println(Test);
		//new BetalingScheduler();
		Database database = new Database();
		String inrijdQuery = "SELECT inrijd_id FROM inrijden WHERE Pas_ID = Pas_ID";
		database.query(inrijdQuery);		
		}
		catch(Exception x){
			x.printStackTrace();
		}
	}
	
	public static void pasHerkenning(String s){
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
