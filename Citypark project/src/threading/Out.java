package threading;

import java.io.*;

public class Out {
	private OutputStream out;
	
	public Out(OutputStream out) {
		this.out=out;
	}
	
	public void beep() throws Exception {
		write('B');
	}
	
	public void beeps() throws Exception {
		write('A');
	}
	
	public void reset() throws Exception {
		write('R');
	}
	
	public void name() throws Exception {
		write('N');
	}
	
	public void version() throws Exception {
		write('V');
	}
	
	private void write(char c) throws Exception {
		out.write(new byte[] {(byte) c});
	}
	
}
