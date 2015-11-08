package mainReader;

import threading.ThreadManager;
import comm.Comm;
import comm.CommSetting;
import gnu.io.*;
import gui.MainScreen;

public class Main {
	private Comm comm;
	private CommSetting setting;
	private MainScreen mainscreen;
	private ThreadManager threadManager;
	private String pas;
	
	public Main() throws Exception {
		setting=new CommSetting("COM4", 9600, 
				SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
				SerialPort.PARITY_NONE, SerialPort.FLOWCONTROL_NONE);
		comm=new Comm(setting);
      	mainscreen=new MainScreen(comm.getOut());
      	threadManager = new ThreadManager(comm.getIn(), mainscreen, 250L);
	}
	
	public String getPas(){
		pas = threadManager.getPas();
		return pas;
	}
	
	
	
	
}
