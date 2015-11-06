package comm;

import java.util.*;
import java.io.*;

import threading.In;
import threading.Out;
import gnu.io.*;

public class Comm {
	private String strPortName;
    private int baudrate;  
    private int databits;
    private int stopbits;   
    private int parity;
    private int flowcontrol;   
    private OutputStream outModem;
    private InputStream inpModem;
    private CommPortIdentifier portId;
    private Enumeration<CommPortIdentifier> portList;
    private SerialPort serialPort;
    private In in;
    private Out out;
        
    public Comm(CommSetting settings) throws Exception {
        System.out.println("Comm:<init> S");
        this.strPortName=settings.getStrPortName();
        this.baudrate=settings.getBaudrate();
        this.databits=settings.getDatabits();
        this.stopbits=settings.getStopbits();
        this.parity=settings.getParity();
        this.flowcontrol=settings.getFlowcontrol();
        initModem();
        System.out.println("comm:<init> E");
    }
    
    @SuppressWarnings("unchecked")
	private void initModem() throws Exception {
        System.out.println("comm:initModem S");
        boolean bFound=false;
        portList = CommPortIdentifier.getPortIdentifiers();
        while (portList.hasMoreElements() && !bFound) {
            portId = portList.nextElement();
            // check if it is a serial port
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) 
                if (portId.getName().equals(strPortName)) bFound=true; 
        }

        if (!bFound) throw new Exception("Comm not found: Reader niet aangesloten!");
        serialPort = (SerialPort) portId.open("comm", 2000);
        serialPort.setSerialPortParams(baudrate, databits, stopbits, parity);
        serialPort.setFlowControlMode(flowcontrol);
        inpModem = serialPort.getInputStream();
        outModem = serialPort.getOutputStream();
        in=new In(inpModem);
        out=new Out(outModem);
        
        System.out.println("comm:initModem E");
    }
    
    public In getIn() {
    	return in;
    }
    
    public Out getOut() {
    	return out;
    }
}
