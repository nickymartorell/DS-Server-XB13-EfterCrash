package ServerBliverikkebrugt;
import java.net.*;

import GUI.GUILogic;

class TCPServer{    
	
	//keyKeeper.KeyChest KC = new keyKeeper.KeyChest();
	private String port;
	private static int cPort;
	
//	Imports portNr key from external file
	public void keyImporter()
	{
//		KC.keyImporter();
//		setportNr("8888");
//		Converting string to int, which is apparently necessary
//		int cPort = Integer.parseInt(port);
	}	
	public static void main(String argv[]) throws Exception       {
		
		//AdminWorker admin = new AdminWorker();		
//		Gets portNr from configuration file
		//Creates a socket to send and recieve messages in port 8888
//		Old port declaration
//		ServerSocket welcomeSocket = new ServerSocket(8888);
//		New read from file port declaration
		ServerSocket welcomeSocket = new ServerSocket(8888);
		
		//While something is true
		while(true){
			//Creates a socket and a buffered reader which recieves some sort of input from somewhere around the internet!
			Socket connectionSocket = welcomeSocket.accept();
			ClientWorker client= new ClientWorker(connectionSocket);
			Thread thread = new Thread(client, "client");
			thread.start();
			/*HUSK AT �NDRE DATABASE SCRIPTET, S� DET PASSER MED DEN NUV�RENDE DATABASE STRUKTUR!*/
		}		
	}		
		public void setportNr(String port) {
			this.port = port;	
	}
}