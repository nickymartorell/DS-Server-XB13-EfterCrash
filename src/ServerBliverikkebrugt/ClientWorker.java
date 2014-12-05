package ServerBliverikkebrugt;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;


import Encryption.encryption;
import GUILogic.Logic;
import SwitchLogic.GiantSwitch;

public class ClientWorker implements  Runnable{
	private Socket connectionSocketConected;
	private GiantSwitch GS = new GiantSwitch();
	private encryption cryp = new encryption();
	
	ClientWorker(Socket connectionSocket){
		this.connectionSocketConected = connectionSocket;
	}
	
	@Override
	public void run(){
		try{
			byte[] b = new byte[500];
			int count = connectionSocketConected.getInputStream().read(b);
			ByteArrayInputStream bais = new ByteArrayInputStream(b);
			DataInputStream inFromClient = new DataInputStream(connectionSocketConected.getInputStream());		
			//Creates an object of the data which is to be send back to the client, via the connectionSocket
			DataOutputStream outToClient = new DataOutputStream(connectionSocketConected.getOutputStream());
			System.out.println("Outtoclient oprettet!");
			//Sets client sentence equals input from client
			
			String ny1 = new String(b, "UTF-8").trim();
			System.out.println(ny1);
			
			byte[] input = ny1.getBytes();
			byte key = (byte) 3.1470;
			byte[] encrypted = input;
			for (int i = 0; i < encrypted.length; i++)
			{
				encrypted[i] = (byte) (encrypted[i] ^ key);
			}
			String decrypted = new String(encrypted).trim();
			//Sysout recieved message
			System.out.println("Received: " + decrypted);
			String returnSvar = GS.GiantSwitchMethod(decrypted);
			System.out.println(returnSvar);

			byte[] input2 = returnSvar.getBytes();
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
