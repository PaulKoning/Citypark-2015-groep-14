package main;
import Database.Database;
import scheduler.BetalingScheduler;

public class Initialize {
	private BetalingScheduler scheduler;
	private Database db;
	public Initialize() {
		scheduler = new BetalingScheduler();
		db = new Database();
	}
}
