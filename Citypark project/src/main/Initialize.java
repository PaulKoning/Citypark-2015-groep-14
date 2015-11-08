package main;
import BetalingsAfhandeling.BetalingsAfhandeling;
import Database.Database;
import mainReader.Main;

public class Initialize {
	public Initialize() {
	    
		try{
		new Main();	// start de main van PROMAGReader
		//new BetalingScheduler();	
				
		}
		catch(Exception x){
			x.printStackTrace();
		}
	}
}
