package nl.hanze.promagreader.threading;

import java.io.*;

public class In extends Thread {
	private InputStream in;
	private byte[] buffer;
	private int max;
	private String out;
	private boolean blocked;
	
	public In(InputStream in) {
		this.in=in;
		buffer=new byte[50];
		max=0;
		blocked=false;
		start();
	}
	
	protected String getBuffer() {
		blocked=true;
		convertBufferToString();
		blocked=false;
		return out;
	}
	
	@Override
	public void run() {
		boolean skip=false;
		while(true) {
			try {
				while(blocked) {}
				int i=in.read();
				skip=(i==-1);
				if (!skip) {
					byte b=(byte) i;
					while(blocked) {}
					buffer[max++]=b;
				}
				skip=false;
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void convertBufferToString() {
		if (max==0) {out=null; return;}
		
		StringBuffer stbTemp=new StringBuffer();
		
		for(int i=0;i<max;i++) {
			switch(buffer[i]) {
				case 0x02 : stbTemp.append("STX "); break;
				case 0x07 : stbTemp.append("BEL "); break;
				case 0x0A : stbTemp.append("LF "); break;
				case 0x0D : stbTemp.append("CR "); break;
				case 0x1B : stbTemp.append("ESC "); break;
				default : stbTemp.append((char) buffer[i]);
			} 
		}
		
		stbTemp.append("\n\r");
		max=0;
		out=stbTemp.toString();
	}
	
}
