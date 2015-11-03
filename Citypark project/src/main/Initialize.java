package main;
import Database.Database;
import scheduler.BetalingScheduler;
import BetalingsAfhandeling.BetalingsAfhandeling;

public class Initialize {
	public Initialize() {
		try{
		new BetalingsAfhandeling();
		//new BetalingScheduler();
		new Database();
		}
		catch(Exception x){
			x.printStackTrace();
		}
	}
}
