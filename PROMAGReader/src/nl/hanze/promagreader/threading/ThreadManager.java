package nl.hanze.promagreader.threading;

import javax.swing.JTextArea;

import nl.hanze.promagreader.gui.*;
import nl.hanze.promagreader.decoder.*;

public class ThreadManager extends Thread {
	private In in;//Instantie nodig voor input
	private MainScreen main;//GUI
	private long pollinterval;
	private Decoder dec;
	
	
	public ThreadManager(In in, MainScreen main, long pollinterval) {
		this.in=in;
		this.main=main;//Instantie van main
		this.pollinterval=pollinterval;//
		dec=new Decoder();//New Decoder to 
		start();
	}
	
public void pasHerkenning(String s){
	try {	
		if(s!=null) { 
			if (s.equals("STX EED6326ACR LF \n\r")) {
				System.out.println("Bank pas 1");
			}
			if (s.equals("STX D4F9374CCR LF \n\r")) {
				System.out.println("Bank pas 2");
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
		}	
	
	@Override
	public void run() {
		while(true) {
			
			try {
				String s=in.getBuffer();
				if(s!=null) { 
					main.setText(s);					
					main.setID(dec.decodeLastValue(main.getText()));
					pasHerkenning(s);	
				}				
				Thread.sleep(pollinterval);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
