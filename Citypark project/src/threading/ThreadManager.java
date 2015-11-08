package threading;

import gui.MainScreen;
import decoder.Decoder;
import scheduler.BetalingScheduler;
import main.Initialize;

public class ThreadManager extends Thread {
	private In in;//Instantie nodig voor input
	private MainScreen main;//GUI
	private long pollinterval;
	private Decoder dec;
	
	public ThreadManager(In in, MainScreen main, long pollinterval) {
		new BetalingScheduler();
		this.in=in;
		this.main=main;//Instantie van main
		this.pollinterval=pollinterval;//
		dec=new Decoder();//New Decoder to 
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
					Initialize.PoortOfPin(s);
				}				
				Thread.sleep(pollinterval);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
