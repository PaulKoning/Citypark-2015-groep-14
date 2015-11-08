package scheduler;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;

/**
 * 
 * @author Koen Berghuis
 * Scheduled de maandelijkse betaling voor abbonees
 *
 */

public class BetalingScheduler {
	private Timer timer;
	private Betaling betaling;
	
	public BetalingScheduler() {
		timer = new Timer();
		betaling = new Betaling(this);
		schedule();
	}
	
	/**
	 * Scheduled een betaling voor de eerste dag van de volgende maand
	 */
	public void schedule() {
		timer.scheduleAtFixedRate(betaling, getFirstDay(), getDays());
	}
	
	/**
	 * 
	 * @return Date object van de eerste dag van de volgende maand
	 */
	private Date getFirstDay() {
		Calendar firstDay = new GregorianCalendar();
		//één maand vooruit
		firstDay.add(Calendar.MONTH, 1);
		//eerste dag van de maand
		firstDay.set(Calendar.DAY_OF_MONTH, 1);
		return firstDay.getTime();
	}
	
	/**
	 * Geeft het aantal dagen dat in de huidige maand zit
	 * @return aantal dagen in huidige maand
	 */
	private int getDays() {
		Calendar current = new GregorianCalendar();
		return current.getActualMaximum(Calendar.MONTH);
	}
}
