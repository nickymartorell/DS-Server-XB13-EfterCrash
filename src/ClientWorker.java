import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;


public class ClientWorker implements  Runnable{
	private Socket connectionSocketConected;
	//private CalendarInfo CI = new CalendarInfo();
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
	
			//SERVEREN MODTAGER FRA CLIENTEN
			DataInputStream inFromClient = new DataInputStream(connectionSocketConected.getInputStream());		

			//SERVEREN SENDER TILBAGE TIL CLIENTEN VIA SOCKET
			DataOutputStream outToClient = new DataOutputStream(connectionSocketConected.getOutputStream());
			
			
			//Sets client sentence equals input from client		
			String inputBesked = new String(b, "UTF-8").trim();
			
			//getter bytes
			byte[] input = inputBesked.getBytes();
			
			//kryptere med
			byte key = (byte) 3.1470;
			byte[] encrypted = input;
			for (int i = 0; i < encrypted.length; i++)
			{
				encrypted[i] = (byte) (encrypted[i] ^ key);
			}
			
			//dekryptere
			String decrypted = new String(encrypted).trim();
	
			//System.out.println("Received: " + decrypted);
			
			//out to client
			String outputBesked = GS.GiantSwitchMethod(decrypted);

			byte[] input2 = outputBesked.getBytes();
			byte key2 = (byte) 3.1470;
			byte[] encrypted2 = input2;
			for (int i = 0; i < encrypted2.length; i++)
			{
				encrypted2[i] = (byte) (encrypted2[i] ^ key);
			}
			String stringToClient = new String(encrypted2).trim();
		
			//Sends the capitalized message back to client!!
			System.out.println(stringToClient);
			outToClient.writeBytes(stringToClient+"\n");
			
		}catch(Exception exception){
			System.err.print(exception);
		}
	}
}


