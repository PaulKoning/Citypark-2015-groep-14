package scheduler;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TimerTask;

import bankclient.BankProxy;
import Database.Database;

/**
 * 
 * @author Koen Berghuis
 * Regelt afschrijving voor abbonees. Wordt periodiek uitgevoerd.
 *
 */
public class Betaling extends TimerTask {
	private static final String URL = "localhost:3306/citypark";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	private static final int REKENINGNR_CITYPARK = 100000;
	BetalingScheduler scheduler;
	
	/**
	 * 
	 * @param scheduler De scheduler om de betaling voor de volgende maand te kunnen schedulen, aangezien een maand geen constante lengte heeft.
	 */
	public Betaling(BetalingScheduler scheduler) {
		this.scheduler = scheduler;
	}
	
	@Override
	public void run() {
		Database database = new Database(URL, USERNAME, PASSWORD);
		BankProxy bank = new BankProxy();
		database.query("SELECT Bedrag_p_maand FROM abbonementtype WHERE Abbonementtype = 2");
		int bedrag = (int)database.getResult().get(0).get("Bedrag_p_maand");
		database.query("SELECT gebruiker.Rekeningsnummer, gebruiker.Gebruiker_ID from gebruiker JOIN abbonementen on gebruiker.Pas_Pas_ID = abbonementen.Pas_Pas_ID WHERE abbonomenten.Abbonementtype_Abbonementtype = 2");
		ArrayList<Map<String, Object>> res = database.getResult();
		//gaat alle abbonementen bij langs en schrijft het geld af
		for(Map<String, Object> row : res) {
			try {
				if(!bank.transfer((int)row.get("Rekeningsnummer"), REKENINGNR_CITYPARK, bedrag)) {
					//blokkeer passen als de afschrijving niet lukt
					database.update("UPDATE pas SET Actief = 0 WHERE pas.Gebruiker_Gebruiker_ID = " + (String)row.get("Gebruiker_ID"));
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		database.close();
		//schedule volgende betaling
		scheduler.schedule();
	}

}
