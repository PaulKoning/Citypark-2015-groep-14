package main;
import BetalingsAfhandeling.BetalingsAfhandeling;
import Database.Database;
import mainReader.Main;

public class Initialize {
	private String pas;
	private Main main;
	public Initialize() {
	    
		try{
		main = new Main();	// start de main van PROMAGReader
		pas = main.getPas();
		
		System.out.println(pas);
		//new BetalingScheduler();	
				
		}
		catch(Exception x){
			x.printStackTrace();
		}
	}
	
	public static void PoortOfPin(){
		
	}
}
