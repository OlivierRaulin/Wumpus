import java.io.*;
import java.awt.*;
import java.net.*;


public class Client {

	public static void main(String[] args){
		Socket socket;
		try {
		socket = new Socket("localhost",1457);
		//code du client
		 try 
				{
				Thread.sleep(0);
				PrintWriter socketOut = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
				BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				//System.out.print("Message : ");
				while(true)
				{
					while(!socketIn.ready())
					{
						Thread.sleep(0);					
					}
					while(socketIn.ready())
					{
						System.out.println(socketIn.readLine());
						Thread.sleep(100);
					}
					String message = clavier.readLine(); 
					socketOut.println(message);
				}
				//Frame frame = new Frame("Client Wumpus");
 				} catch (InterruptedException e) {
				e.printStackTrace();
			}
		//socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
