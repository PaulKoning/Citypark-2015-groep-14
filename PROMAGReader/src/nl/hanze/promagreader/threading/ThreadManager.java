package nl.hanze.promagreader.threading;

import nl.hanze.promagreader.gui.*;
import nl.hanze.promagreader.decoder.*;

public class ThreadManager extends Thread {
	private In in;
	private MainScreen main;
	private long pollinterval;
	private Decoder dec;
	
	public ThreadManager(In in, MainScreen main, long pollinterval) {
		this.in=in;
		this.main=main;
		this.pollinterval=pollinterval;
		dec=new Decoder();
		start();
	}
	
	@Override
	public void run() {
		while(true) {
			
			try {
				String s=in.getBuffer();
				
				if(s!=null) { 
					main.setText(s);
					main.setID(dec.decodeLastValue(main.getText()));
				}
				
				Thread.sleep(pollinterval);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
