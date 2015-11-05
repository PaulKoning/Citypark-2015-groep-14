package nl.hanze.promagreader.threading;

import java.io.*;

/*
 * Thread om input te lezen
 */
public class In extends Thread {
	private InputStream in; //input
	private byte[] buffer; //buffer voor het inlezen van de inputstream
	private int max; //tot welke index de buffer is gevuld
	private String out; //output
	private boolean blocked; //mutex voor buffer
	
	public In(InputStream in) {
		this.in=in;
		buffer=new byte[50]; 
		max=0;
		blocked=false;
		start();
	}
	
	/*
	 * Geeft string representatie van de buffer
	 * @returns {String} buffer
	 */
	protected String getBuffer() {
		blocked=true;	//blokkeer de buffer terwijl deze wordt gelezen
		convertBufferToString();
		blocked=false; //geef de buffer vrij
		return out;
	}

	@Override
	public void run() {
		boolean skip=false;
		while(true) {
			try {
				while(blocked) {} //wacht tot de buffer is vrijgegeven
				int i=in.read(); //lees de volgende byte van de inputstream
				skip=(i==-1); //als de inputstream leeg is i niet aan de buffer toevoegen
				if (!skip) {
					byte b=(byte) i; //int naar byte casten
					while(blocked) {} //wacht tot de buffer is vrijgegeven
					buffer[max++]=b; //nieuwe byte aan de eerste vrije plaats in de buffer toevoegen
					
				}
				
				skip=false;
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	

	/*
	 * Converteert buffer naar String waarbij speciale bytes in juiste formaat worden gezet
	 * @returns {String} buffer
	 */
	private void convertBufferToString() {
		if (max==0) {out=null; return;} //als de buffer leeg is null returnen
		
		StringBuffer stbTemp=new StringBuffer(); //tijdelijke buffer om huidige buffer naar juiste formaat te converteren
		
		for(int i=0;i<max;i++) { //hele buffer lezen
			switch(buffer[i]) { //speciale bytes naar ander formaat converteren
				case 0x02 : stbTemp.append("STX "); break;
				case 0x07 : stbTemp.append("BEL "); break;
				case 0x0A : stbTemp.append("LF "); break; 
				case 0x0D : stbTemp.append("CR "); break;
				case 0x1B : stbTemp.append("ESC "); break; 
				default : stbTemp.append((char) buffer[i]);
			} 
		}
		
		stbTemp.append("\n\r"); //
		max=0; //buffer is gelezen en kan dus worden beschreven vanaf het begin
		out=stbTemp.toString();
	}
	
}
