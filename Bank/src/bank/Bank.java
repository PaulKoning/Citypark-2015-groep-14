package bank;

import javax.jws.WebService;

import java.util.Map;

import javax.jws.WebMethod;

/**
 * 
 * @author Koen Berghuis
 * Webservice die de functionaliteit van een bank simuleert
 * Kan gebruikt worden voor een credit check en overschrijvingen
 *
 */
@WebService
public class Bank {
	
	public Bank(){
		
	}
	
	/**
	 * Controleert of er meer dan een gegeven bedrag op een rekening staat
	 * 
	 * @param accountnr De te controleren rekening
	 * @param amount Het bedrag
	 * @return True als er genoeg op staat, false als dat niet zo is
	 */
	@WebMethod
	public boolean doCreditCheck(int accountnr, double amount) {
		Database database = new Database("localhost:3306/bank", "root", "");
		if(database.query("SELECT Saldo FROM rekening WHERE Rekening_ID = " + accountnr)) {
			Map<String, Object> row = database.getResult().get(0);
			if((double)row.get("Saldo") >= amount) {
				database.close();
				return true;
			}
		}
		database.close();
		return false;
	}
	
	/**
	 * Doet een overschrijving tussen twee gegeven rekeningen voor een gegeven bedrag
	 * @param accountfrom De rekening waarvan het bedrag afgeschreven wordt
	 * @param accountto De rekening waar het bedrag heen gaat
	 * @param amount Het bedrag dat overgeschreven wordt
	 * @return True als de overschrijving is geslaagd
	 */
	@WebMethod
	public boolean transfer(int accountfrom, int accountto, double amount) {
		//controleren of er genoeg op de rekening staat voor de transfer
		if(doCreditCheck(accountfrom, amount)) {
			//overschrijving uitvoeren
			Database database = new Database("localhost:3306/bank", "root", "");
			if(database.update("UPDATE rekening SET Saldo = Saldo - " + amount + " WHERE Rekening_ID = " + accountfrom)) {
				if(database.update("UPDATE rekening SET Saldo = Saldo + " + amount + " WHERE Rekening_ID = " + accountto)) {
					database.close();
					return true;
				}
			}
			database.close();
		}
		return false;
	}
	
	/**
	 * Doet een overschrijving tussen twee gegeven rekeningen voor een gegeven bedrag waarbij een pincode nodig is
	 * @param accountfrom De rekening waarvan het bedrag afgeschreven wordt
	 * @param accountto De rekening waar het bedrag heen gaat
	 * @param amount Het bedrag dat overgeschreven wordt
	 * @param pin De pincode
	 * @return True als de overschrijving is geslaagd
	 */
	@WebMethod
	public boolean transferMetPin(int accountfrom, int accountto, double amount, int pin) {
		Database database = new Database("localhost:3306/bank", "root", "");
		if(database.query("SELECT Pincode FROM rekening WHERE Rekening_ID = " + accountfrom)) {
			//Er is maar één rij, aangezien Rekening_ID uniek is
			Map<String, Object> row = database.getResult().get(0);
			if((int)row.get("Pincode") == pin) {
				database.close();
				//Als de pin klopt kan de overschrijving via transfer worden uitgevoerd
				return transfer(accountfrom, accountto, amount);
			}
		}
		database.close();
		return false;
	}
	
}
