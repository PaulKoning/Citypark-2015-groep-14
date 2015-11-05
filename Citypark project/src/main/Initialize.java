package main;
import Database.Database;
import scheduler.BetalingScheduler;
import BetalingsAfhandeling.BetalingsAfhandeling;
import BetalingsAfhandeling.PinView;


public class Initialize {
	public Initialize() {
		try{
		//1315 is een tijdelijke testwaarde voor de constructor
		Double Test = new BetalingsAfhandeling(1315).getBetaling();
		System.out.println(Test);
		//new BetalingScheduler();
		new Database();
		}
		catch(Exception x){
			x.printStackTrace();
		}
	}
}
