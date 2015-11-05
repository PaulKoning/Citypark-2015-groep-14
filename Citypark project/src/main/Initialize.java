package main;
import Database.Database;
import scheduler.BetalingScheduler;
import BetalingsAfhandeling.BetalingsAfhandeling;
import nl.hanze.promagreader.main.Runner;

public class Initialize {
	public Initialize() {
		String [] args = new String[2];
	    args[0] = "hello";
	    args[1] = "every";
		try{
		new BetalingsAfhandeling();
		//new BetalingScheduler();
		new Database();
		Runner.main(args);
		}
		catch(Exception x){
			x.printStackTrace();
		}
	}
}
