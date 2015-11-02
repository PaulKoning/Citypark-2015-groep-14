package runner;

import main.Initialize;

/**
 * Something something description
 * 
 */

public class Runner {
	public static void main(String[] args) {

		try {
			new Initialize();
		} catch (Exception e) {
			System.out.println("Het uitvoeren van het programma mislukte.");
			e.getStackTrace();
		}
	}
}
