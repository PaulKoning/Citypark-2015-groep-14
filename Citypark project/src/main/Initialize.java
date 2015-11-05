package main;
import Database.Database;
import scheduler.BetalingScheduler;
import BetalingsAfhandeling.BetalingsAfhandeling;
<<<<<<< HEAD
import BetalingsAfhandeling.PinView;

=======
import nl.hanze.promagreader.main.Runner;
>>>>>>> origin/master

public class Initialize {
	public Initialize() {
		String [] args = new String[2];
	    args[0] = "hello";
	    args[1] = "every";
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
