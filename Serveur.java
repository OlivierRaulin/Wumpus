import java.io.*;
import java.net.*;
import java.sql.Time;

public class Serveur {


	public static void main(String[] args){

		// Todo : Timeout O_o
		
		ServerSocket socket_ecoute;
		Socket socket_service;
  		int nbrclient = 1;
  		int numClient = 1;
		try 
		{
			socket_ecoute = new ServerSocket(1457);
			System.out.println("Serveur actif");
			while(true)
			{
			socket_service = socket_ecoute.accept();
			Thread proto = new Thread(new Protocole(numClient++,nbrclient++,socket_service));
			proto.start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

	   