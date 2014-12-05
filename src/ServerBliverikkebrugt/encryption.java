package ServerBliverikkebrugt;


//import config.Configurations;


public class encryption {
	keyKeeper.KeyChest KC = new keyKeeper.KeyChest();
	private String encryptionKey = "";
	
//	Imports encryption key from external file
	public void keyImporter()
	{
		KC.keyImporter();
		setEncryption(KC.getEncryption());
	}
	
//	Decryption path
	
	public String decrypt(byte[] b)
	{
		//Configurations CF = new Configurations();
//		Defines the decryption value of the byte
		//The 4 lines below needs to work later on, but for now, it will be hardcode
		//System.out.println(CF.getFfcryptkey());
		//System.out.println(CF.getFfcryptkey());
		////String crypKey = CF.getFfcryptkey();
		//System.out.println(crypKey);
		//double gladKo = Double.parseDouble(crypKey);
		
//		This is the old method for declaring encrpytkey
//		byte ff = (byte) 3.1470;
		
//		Imports encryption key from external file
		Byte ff = new Byte(encryptionKey);
		
//		Generates for loop containing decryption value
		for(int i = 0 ; i<b.length ; i++)
		{
			b[i] = (byte)(b[i]^ff);
		}
//		Generates new String without any white spaces following or leading
		String encrypted = new String(b).trim();
//		Returns decrypted String
		return encrypted;
	}
	public static String xor_decrypt(String message, String key){
	    try {
	      if (message==null || key==null ) return null;
	      char[] keys=key.toCharArray();
	      message = new String(message);
	      char[] mesg=message.toCharArray();

	      int ml=mesg.length;
	      int kl=keys.length;
	      char[] newmsg=new char[ml];

	      for (int i=0; i<ml; i++){
	        newmsg[i]=(char)(mesg[i]^keys[i%kl]);
	      }
	      mesg=null; keys=null;
	      return new String(newmsg);
	    }
	    catch ( Exception e ) {
	      return null;
	    }  
	  }
	
	public void setEncryption(String encryption) {
		this.encryptionKey = encryption;
	}
}
