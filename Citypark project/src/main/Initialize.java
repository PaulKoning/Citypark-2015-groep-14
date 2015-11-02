package main;
import Database.Database;
import scheduler.BetalingScheduler;
import BetalingsAfhandeling.BetalingsAfhandeling;

public class Initialize {
	public Initialize() {
		new BetalingsAfhandeling();
		//new BetalingScheduler();
		new Database();
	}
}
