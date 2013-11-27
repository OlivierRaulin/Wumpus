import java.io.*;
import java.awt.*;
import java.net.*;


public class Client {

	public static void main(String[] args){
		Socket socket;
		try {
		socket = new Socket("localhost",10000);
		//code du client
		 try 
				{
				Thread.sleep(0);
				PrintWriter socketOut = new PrintWriter(socket.getOutputStream(), true);
				//BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
				//System.out.print("Message : ");
				//String message = clavier.readLine(); 
				//socketOut.println(message);
				Frame frame = new Frame("Client Wumpus");
 				} catch (InterruptedException e) {
				e.printStackTrace();
			}
		//socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}