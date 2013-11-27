import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;


public class Protocole implements Runnable,Cloneable{

	private Socket socket;
	private int nbrclient;
	private static int numClient;
	private static Monde mondePrin = null;
	//private static Agent joueurPrin = null;
	
		public Protocole(int numClient,int nbrclient,Socket s)
		{
			this.nbrclient=nbrclient;
			socket=s;
			this.numClient = numClient;
			
		}
		
		public Object clone() {
			Protocole proto =null;
			try {
				proto = (Protocole) super.clone();
			}
			catch(CloneNotSupportedException e){
				e.printStackTrace(System.err);
			}
			
			return proto;
		}
		
		public void creeMonde(){
			mondePrin = new Monde(4);
		}
		
		/*public void creeJoueur(){
			joueurPrin = new Agent("");
		}*/
		
		

		public void run(){
			String name;
			Monde monde;
			Agent joueur;
			
	        try
	        {
	       			 
	        	// Envoi d'un message au client
	        	PrintWriter socketOut = new PrintWriter(socket.getOutputStream(), true);
	        	if(true)
	        	{
	  				socketOut.println("Qui va là ?");
	        	}
	        	else
	        	{
	        		socketOut.println("Msg indéfini");
	        	}
	            		
				//code du traitement du client
				try {
				BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));

					socket.setSoTimeout(30000);
					String rep1 = "";
				
				try {
					rep1 = socketIn.readLine();
				}
				catch (java.net.SocketTimeoutException e){
					socket.close();
					return;
				}

				Thread.sleep(0);
				name = rep1.substring(11);
				
				
				while(!(rep1.contains("Mon nom est")))
				{
					socketOut.println("Message incompréhensible : Syntaxe attendue : Mon nom est <Nom> (Sensible à la casse)");
					try{
						rep1 = socketIn.readLine();
					}
					catch (java.net.SocketTimeoutException e){
						socket.close();
						return;
					}
				}
				name = rep1.substring(11);
				socketOut.println("Bienvenue"+name);

				// Creation du joueur et du monde ici
				if (numClient==1){
					this.creeMonde();
					//this.creeJoueur();
				}
					monde = (Monde) mondePrin.clone();
					joueur = new Agent(name);
					//joueur = (Agent) joueurPrin.clone();
					//joueur.setName(name);
				
				//joueur = (Agent) new Agent(name).clone();
				//monde = (Monde) new Monde(4).clone();
				// Message de présentation
				socketOut.println("But du jeu : Le monde est un tableau de 4*4 cases");
				socketOut.println("Il y a un Wumpus, une case avec de l'or, et des trous");
				socketOut.println("Votre mission, si vous l'acceptez, sera de trouver et de ramasser l'or, ");
				socketOut.println("puis éventuellement de tuer le wumpus en lui faisant face et en tirant une flèche (vous n'en avez qu'une)");
				socketOut.println("Pour finir, vous reviendrez à l'origine et taperez 'grimpe'");
				socketOut.println("Les commandes sont insensibles à la casse, sauf pour man, qui doit s'écrire en minuscules");
				socketOut.println("Une aide à propos des commandes sera disponible en tapant 'man <commande>'");
				socketOut.println("");
				while(true)
				{
					socketOut.println("Coordonées :"+ joueur.getPosition() +", "+joueur.getDirection()+", "+monde.getSensations(joueur.getPosition(),joueur));
					// En attente d'action
					socketOut.println("Liste des actions :");
					socketOut.println("Droite, Gauche, Avance, Tire, Ramasse, Quit, Grimpe, Plouf, man <commande>");
					String action;
					try{
						action = socketIn.readLine();
					}
					catch (java.net.SocketTimeoutException e){
						socket.close();
						return;
					}
					boolean valid = false;
					
					if(action.contains("man"))
					{	
						valid=true;
						Manuel man = new Manuel();
						if(action.substring(2).equals("n")) socketOut.println("Syntaxe correcte : man <commande>");
						else socketOut.println(man.getMan(action.substring(4)));
					}
					
					if(action.equalsIgnoreCase("Map"))
					{
						valid = true;
						String s = "";
						Coordonnee coord = new Coordonnee(1,1);
						for(int i=4; i>=1 ;i--){
							coord.setY(i);
							for(int j=1; j<=4;j++){
								coord.setX(j);
								if(monde.getMonde(j,i).getContenu(joueur)==Contenu.VIDE){
									s += "-"+monde.getSensationsMap(coord,joueur)+" ";
								}
								else if(monde.getMonde(j,i).getContenu(joueur)==Contenu.OR){
									s += "g"+monde.getSensationsMap(coord,joueur)+" ";
								}
								else if(monde.getMonde(j,i).getContenu(joueur)==Contenu.TROU){
									s += "t"+monde.getSensationsMap(coord,joueur)+" ";
								}
								else if(monde.getMonde(j,i).getContenu(joueur)==Contenu.AGENT){
									s += "A"+monde.getSensationsMap(coord,joueur)+" ";
								}
								else s += "w"+monde.getSensationsMap(coord,joueur)+" ";
							}
							s += "\r\n";
						}
						socketOut.println(s);
					}
					if(action.equalsIgnoreCase("Avance"))
					{
						valid = true;
						//socketOut.println("Avancement d'une case :-)");
						if(joueur.getPosition().getX() == 4 && joueur.getDirection() == Direction.EST)
						{
							socketOut.println("Bump");
							joueur.setScore(-1);
						}
						else if(joueur.getPosition().getX() == 1 && joueur.getDirection() == Direction.OUEST)
						{
							socketOut.println("Bump");
							joueur.setScore(-1);
						}
						else if(joueur.getPosition().getY() == 4 && joueur.getDirection() == Direction.NORD)
						{
							socketOut.println("Bump");
							joueur.setScore(-1);
						}
						else if(joueur.getPosition().getY() == 1 && joueur.getDirection() == Direction.SUD)
						{
							socketOut.println("Bump");
							joueur.setScore(-1);
						}
						else 
						{
							joueur.setScore(-1);
							//monde.getMonde(joueur.getPosition().getX(),joueur.getPosition().getY()).setAgent();
							joueur.avance(joueur.getDirection());
							//monde.getMonde(joueur.getPosition().getX(),joueur.getPosition().getY()).setAgent();
							if(monde.getX(joueur.getPosition().getXReel(), joueur.getPosition().getYReel()).pit())
							{
								joueur.setScore(-400);
								socketOut.println(joueur.getNom()+ ", fin de partie, tombé dans un trou, "+joueur.getScore());
								socketOut.println("Rejouer ? (O/N)");
								String rep;
								try{
									rep = socketIn.readLine();
								}
								catch (java.net.SocketTimeoutException e){
									socket.close();
									return;
								}
								Thread.sleep(0);
								while(!((rep.equalsIgnoreCase("O") || rep.equalsIgnoreCase("N"))))
								{
									socketOut.println("Message incompréhensible");
								}
								if(rep.equalsIgnoreCase("O"))
								{
									joueur.rejouer();
									if (numClient==1){
										this.creeMonde();
										//this.creeJoueur();
									}
									monde = (Monde) mondePrin.clone();
									//monde = (Monde) new Monde(4).clone();
									socketOut.println("Nouvelle partie !");
									socketOut.println("Position : "+joueur.getPosition());
									socketOut.println(monde.getSensations(joueur.getPosition(),joueur));
								}
								else if(rep.equalsIgnoreCase("N"))
								{
									socketOut.println("Au revoir :-)");
									socket.close();
								}
								
							}
							else if((monde.getX(joueur.getPosition().getXReel(), joueur.getPosition().getYReel()).wumpus())&&(joueur.getWumpusTue() == false))
							{
								joueur.setScore(-800);
								socketOut.println(joueur.getNom()+", fin de partie, mangé par le Wumpus, "+joueur.getScore());
								socketOut.println("Rejouer ? (O/N)");
								String rep;
								try{
									rep = socketIn.readLine();
								}
								catch (java.net.SocketTimeoutException e){
									socket.close();
									return;
								}
								Thread.sleep(0);
								while(!((rep.equalsIgnoreCase("O") || rep.equalsIgnoreCase("N"))))
								{
										socketOut.println("Message incompréhensible, rejouer ? (O/N)");
										try{
											rep = socketIn.readLine();
										}
										catch (java.net.SocketTimeoutException e){
											socket.close();
											return;
										}
										Thread.sleep(0);
								}
								if(rep.equalsIgnoreCase("O"))
								{
									joueur.rejouer();
									if (numClient==1){
										this.creeMonde();
										//this.creeJoueur();
									}
									monde = (Monde) mondePrin.clone();
									//monde = (Monde) new Monde(4).clone();
									socketOut.println("Nouvelle partie !");
									socketOut.println("Position : "+joueur.getPosition());
									socketOut.println(monde.getSensations(joueur.getPosition(),joueur));
								}
								else if(rep.equalsIgnoreCase("N"))
								{
									socketOut.println("Au revoir :-)");
									socket.close();
								}
								
							}
							else
							{
								socketOut.println("Nouvelle position : "+joueur.getPosition().toString());
								socketOut.println(monde.getSensations(joueur.getPosition(),joueur));
							}
						}
						
					}
					
					if(action.equalsIgnoreCase("Grimpe"))
					{
						joueur.setScore(-1);
						valid = true;
						if(joueur.getPosition().equals(joueur.getPositionOrigine()))
						{
							joueur.setScore(2000);
							socketOut.println(joueur.getName()+", sortie du labyrinthe"+joueur.getScore());
							socketOut.println("Rejouer ? (O/N)");
							String rep;
							try{
								rep = socketIn.readLine();
							}
							catch (java.net.SocketTimeoutException e){
								socket.close();
								return;
							}
							Thread.sleep(0);
							
							while(!((rep.equalsIgnoreCase("O") || rep.equalsIgnoreCase("N"))))
							{
									socketOut.println("Message incompréhensible, rejouer ? (O/N)");
									try{
										rep = socketIn.readLine();
									}
									catch (java.net.SocketTimeoutException e){
										socket.close();
										return;
									}
									Thread.sleep(0);
							}
							if(rep.equalsIgnoreCase("O"))
							{
								joueur.rejouer();
								if (numClient==1){
									this.creeMonde();
									//this.creeJoueur();
								}
								monde = (Monde) mondePrin.clone();
								//monde = (Monde) new Monde(4).clone();
								socketOut.println("Nouvelle partie !");
								socketOut.println("Position : "+joueur.getPosition());
								socketOut.println(monde.getSensations(joueur.getPosition(),joueur));
							}
							else if(rep.equalsIgnoreCase("N"))
							{
								socketOut.println("Au revoir :-)");
								socket.close();
							}
							//A ajuster
						}
						else
						{
							socketOut.println("Commande grimpe disponible uniquement  l'origine");
						}
					}
					
					if(action.equalsIgnoreCase("Plouf"))
					{
						valid = true;
						socketOut.println("Un coup dans l'eau !");
						joueur.setScore(-1);
					}
					
					if(action.equalsIgnoreCase("Tire"))
					{
						joueur.setScore(-1);
						valid = true;
						if((monde.wumpus(joueur.getPosition(), joueur.getDirection(), joueur))&&(joueur.getFleche()))
						{
							socketOut.println("Succès :-)");
							joueur.setScore(1000);
							joueur.setFleche(false);
						}
						else if(joueur.getFleche() == false)
						{
							socketOut.println("Plus de flèche dispo !");
						}
						else
						{
							socketOut.println("Echec :-(");
						}
						joueur.setFleche(false);
					}
					
					if(action.equalsIgnoreCase("Ramasse"))
					{
						joueur.setScore(-1);
						valid = true;
						if(monde.gold(joueur.getPosition()))
						{
							socketOut.println("Succès :-)");
							joueur.setScore(1000);
							monde.getMonde(joueur.getPosition().getX(), joueur.getPosition().getY()).orRamasse();
						}
						else
						{
							socketOut.println("Echec :-(");
						}
					}
					
					if(action.equalsIgnoreCase("Droite"))
					{
						
						valid = true;
						Direction temp = joueur.getDirection();
						if(temp.equals(Direction.NORD))
						{
							temp = Direction.EST;
						}
						
						else if(temp.equals(Direction.SUD))
						{
							temp = Direction.OUEST;
						}
						
						else if(temp.equals(Direction.EST))
						{
							temp = Direction.SUD;
						}
						
						else if(temp.equals(Direction.OUEST))
						{
							temp = Direction.NORD;
						}
						
						else
						{
							socketOut.println("Problème");
						}
						
						socketOut.println("Nouvelle direction : "+temp);
						joueur.setDirection(temp);
					}
					
					if(action.equalsIgnoreCase("Gauche"))
					{
						valid = true;

						Direction temp = joueur.getDirection();
						if(temp.equals(Direction.NORD))
						{
							temp = Direction.OUEST;
						}
						
						else if(temp.equals(Direction.SUD))
						{
							temp = Direction.EST;
						}
						
						else if(temp.equals(Direction.EST))
						{
							temp = Direction.NORD;
						}
						
						else if(temp.equals(Direction.OUEST))
						{
							temp = Direction.SUD;
						}
						
						socketOut.println("Nouvelle direction : "+temp);
						joueur.setDirection(temp);
						joueur.setScore(-1);
					}
					
					if(action.equalsIgnoreCase("Quit"))
					{
						valid = true;
						joueur.setScore(-1000*monde.getSize());
						socketOut.println("Fin de partie, "+joueur.getName()+", "/* + cause*/ + joueur.getScore()+", rejouer ? (O/N)" );
						String repQuit;
						try {
							repQuit = socketIn.readLine();
						}
						catch (java.net.SocketTimeoutException e){
							socket.close();
							return;
						}
						if(repQuit.equalsIgnoreCase("O"))
						{
							// Rejouer
							socketOut.println("Nouvelle partie");
							joueur.rejouer();joueur.setScore(-1);
						}
						else if(repQuit.equalsIgnoreCase("N"))
						{
							socketOut.println("Au revoir ! :-)");
							try
							{
								socket.close();
							}
							catch(java.net.SocketException e)
							{
								new SocketException("Client déconnecté");
								
							}
							
						}
						else
						{
							socketOut.println("Message incompréhensible !");
						}	
					}
					else if(valid == false)
					{
						socketOut.println("Commande invalide : Réessayer");
					}			
				}
				
				
				
			} 
			catch (InterruptedException e) {
			e.printStackTrace();
			}
				//socket.close();

	        }
	        catch (IOException e) {
				e.printStackTrace();
			}
		}
}

