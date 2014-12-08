import javax.xml.bind.ParseConversionEvent;

import config.Configurations;


public class encryption {

	public String decrypt(byte[] b)
	{
		
		Configurations CF = new Configurations();
		
		//her kunne vi laese fra fil og dekryptere den.
		String crypKey = CF.getFfcryptkey();
		
		double tilByte = Double.parseDouble(crypKey);
		byte ff = (byte) tilByte;

		//dekryptere
		for(int i = 0 ; i<b.length ; i++)
		{
			b[i] = (byte)(b[i]^ff);
		}
		//Generates new String without any white spaces following or leading
		String encrypted = new String(b).trim();
		//Returns decrypted String
		return encrypted;
	}
}
