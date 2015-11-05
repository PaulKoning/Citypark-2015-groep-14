package main;
import nl.hanze.promagreader.main.Runner;
import BetalingsAfhandeling.BetalingsAfhandeling;
import Database.Database;

public class Initialize {
	public Initialize() {
		String [] args = new String[1];
	    args[0] = "hello";
		try{
		//1315 is een tijdelijke testwaarde voor de constructor
		Double Test = new BetalingsAfhandeling(1315).getBetaling();
		System.out.println(Test);
		//new BetalingScheduler();
		new Database();
		Runner.main(args);
		}
		catch(Exception x){
			x.printStackTrace();
		}
	}
}
