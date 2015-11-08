package threading;

import gui.MainScreen;
import decoder.Decoder;
import pasHerkenning.PasHerkenning;
import scheduler.BetalingScheduler;
import main.Initialize;

public class ThreadManager extends Thread {
	private In in;//Instantie nodig voor input
	private MainScreen main;//GUI
	private long pollinterval;
	private Decoder dec;
	private String pas;	
	
	public ThreadManager(In in, MainScreen main, long pollinterval) {
		new BetalingScheduler();
		this.in=in;
		this.main=main;//Instantie van main
		this.pollinterval=pollinterval;//
		dec=new Decoder();//New Decoder to 
		start();
	}
	
	public String getPas(){
		return pas;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				String s=in.getBuffer();
				if(s!=null) { 
					main.setText(s);					
					main.setID(dec.decodeLastValue(main.getText()));
					pas = PasHerkenning.pasHerkenning(s);
					Initialize.PoortOfPin();
				}				
				Thread.sleep(pollinterval);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
