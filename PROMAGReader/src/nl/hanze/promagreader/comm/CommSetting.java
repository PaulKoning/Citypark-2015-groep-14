package nl.hanze.promagreader.comm;

public class CommSetting {
	private String strPortName;
    private int baudrate;  
    private int databits;
    private int stopbits;   
    private int parity;
    private int flowcontrol;
	
    public CommSetting() {}
    
    public CommSetting(String strPortName, int baudrate, int databits, int stopbits, int parity, int flowcontrol) {
    	this.strPortName=strPortName;
    	this.baudrate=baudrate;
    	this.databits=databits;
    	this.stopbits=stopbits;
    	this.parity=parity;
    	this.flowcontrol=flowcontrol;
    }
    
    public String getStrPortName() {
		return strPortName;
	}
	
    public void setStrPortName(String strPortName) {
		this.strPortName = strPortName;
	}
	
    public int getBaudrate() {
		return baudrate;
	}
	
    public void setBaudrate(int baudrate) {
		this.baudrate = baudrate;
	}
	
    public int getDatabits() {
		return databits;
	}
	
    public void setDatabits(int databits) {
		this.databits = databits;
	}
	
    public int getStopbits() {
		return stopbits;
	}
	
    public void setStopbits(int stopbits) {
		this.stopbits = stopbits;
	}
	
    public int getParity() {
		return parity;
	}
	
    public void setParity(int parity) {
		this.parity = parity;
	}
	
    public int getFlowcontrol() {
		return flowcontrol;
	}
	
    public void setFlowcontrol(int flowcontrol) {
		this.flowcontrol = flowcontrol;
	}   
}
