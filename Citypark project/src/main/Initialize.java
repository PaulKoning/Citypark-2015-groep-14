package main;
import Database.Database;
import scheduler.BetalingScheduler;
import BetalingsAfhandeling.BetalingsAfhandeling;

public class Initialize {
	public Initialize() {
		try{
		//1315 is een tijdelijke testwaarde voor de constructor
		new BetalingsAfhandeling(1315);
		//new BetalingScheduler();
		new Database();
		}
		catch(Exception x){
			x.printStackTrace();
		}
	}
}
