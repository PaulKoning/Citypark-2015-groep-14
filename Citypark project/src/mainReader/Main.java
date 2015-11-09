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
	
	public Main() throws Exception {
		setting=new CommSetting("ttyUSB0", 9600, 
				SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
				SerialPort.PARITY_NONE, SerialPort.FLOWCONTROL_NONE);
		comm=new Comm(setting);
      	mainscreen=new MainScreen(comm.getOut());
      	new ThreadManager(comm.getIn(), mainscreen, 250L);
	}
}
