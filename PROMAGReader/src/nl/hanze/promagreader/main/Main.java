package nl.hanze.promagreader.main;

import nl.hanze.promagreader.comm.*;
import nl.hanze.promagreader.gui.*;
import nl.hanze.promagreader.threading.*;
import gnu.io.*;

public class Main {
	private Comm comm;
	private CommSetting setting;
	private MainScreen mainscreen;
	
	public Main() throws Exception {
		setting=new CommSetting("COM4", 9600, 
				SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
				SerialPort.PARITY_NONE, SerialPort.FLOWCONTROL_NONE);
		comm=new Comm(setting);
      	mainscreen=new MainScreen(comm.getOut());
		new ThreadManager(comm.getIn(), mainscreen, 250L);
	}
	
	
}
