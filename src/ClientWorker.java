import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;


public class ClientWorker implements  Runnable{
	private Socket connectionSocketConected;
	private GiantSwitch GS = new GiantSwitch();
	private encryption cryp = new encryption();
	private String incomingJson;
	
	ClientWorker(Socket connectionSocket){
		this.connectionSocketConected = connectionSocket;
	}
	
	@SuppressWarnings("unused")
	@Override
	public void run(){
		try{		
			byte[] b = new byte[700];
	
			//saa vi kan laese bytes
			int count = connectionSocketConected.getInputStream().read(b);
			ByteArrayInputStream bais = new ByteArrayInputStream(b);
	
			//input
			DataInputStream inFromClient = new DataInputStream(connectionSocketConected.getInputStream());		
			//output
			DataOutputStream outToClient = new DataOutputStream(connectionSocketConected.getOutputStream());
				
			//trim for at fjerne mellemrum
			String inputBesked = new String(b, "UTF-8").trim();
			
			//getter bytes for at kunne kryptere
			byte[] input = inputBesked.getBytes();
			
			//kryptere med key
			byte key = (byte) 3.1470;
			byte[] encrypted = input;
			for (int i = 0; i < encrypted.length; i++)
			{
				encrypted[i] = (byte) (encrypted[i] ^ key);
			}
			
			//dekryptere
			String decrypted = new String(encrypted).trim();		
			//meddelelsen til switchen
			String outputBesked = GS.GiantSwitchMethod(decrypted);

			byte[] input2 = outputBesked.getBytes();
			byte key2 = (byte) 3.1470;
			byte[] encrypted2 = input2;
			for (int i = 0; i < encrypted2.length; i++)
			{
				encrypted2[i] = (byte) (encrypted2[i] ^ key);
			}
			String stringToClient = new String(encrypted2).trim();
		
			//klienten faar et svar tilbage
			System.out.println(stringToClient);
			outToClient.writeBytes(stringToClient+"\n");
			
		}catch(Exception exception){
			System.err.print(exception);
		}
	}
}


