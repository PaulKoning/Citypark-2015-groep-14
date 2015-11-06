package main;
import nl.hanze.promagreader.main.Runner;
import BetalingsAfhandeling.BetalingsAfhandeling;
import Database.Database;

public class Initialize {
    private static final String URL = "localhost:3306/citypark";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
	public static Database database = new Database(URL, USERNAME, PASSWORD);
	public Initialize() {
		String [] args = new String[1];
	    args[0] = "hello";
	    
		try{
		Runner.main(args);	// start de runner van PROMAGReader
		
		Double Test = new BetalingsAfhandeling(1315).getBetaling();	//1315 is een tijdelijke testwaarde voor de constructor
		System.out.println(Test);
		//new BetalingScheduler();
		
				
		}
		catch(Exception x){
			x.printStackTrace();
		}
	}
}
