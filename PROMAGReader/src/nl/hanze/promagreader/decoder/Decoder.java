package nl.hanze.promagreader.decoder;
public class Decoder {
	public String decodeLastValue(String s) {
		int j=s.lastIndexOf("CR");
		if (j==-1) return null;
		int i=s.lastIndexOf("STX", j);
		if (i==-1) return null;
		s=s.substring(i+3,j).trim().toUpperCase();
		StringBuffer temp=new StringBuffer();
		for(int k=0;k<s.length();k++) {
			char c=s.charAt(k);
			if (c!='\r' && c!='\n') temp.append(c);
		}
		s=temp.toString();
		if (s.length()!=18) return null;
		// example s = C0 F9 3A 6F BC D1 32 6B A8
		s=s.substring(2);
		// skip C0
		// can't convert 32 bits if first bit=1, since first bit is used as sign-bit in int
		// so let's split it in 2-byte parts
		int a=Integer.parseInt(s.substring(0,4), 16); // F9 3A radix=16
		int b=Integer.parseInt(s.substring(8,12), 16); //  D1 32 radix=16
		int c=Integer.parseInt(s.substring(4,8), 16); // 6F BC radix=16
		int d=Integer.parseInt(s.substring(12), 16); //  6B A8 radix=16

		return Integer.toHexString(a^b).toUpperCase() + Integer.toHexString(c^d).toUpperCase();
	}
}
