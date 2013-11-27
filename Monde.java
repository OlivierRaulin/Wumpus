public class Monde implements Cloneable
{
	private Case[][] monde;
	private int taille;
	//private Coordonnee coorWumpus;
	//private Coordonnee coorTresor;
	
	public Object clone() {
		Monde m =null;
		try {
			m = (Monde) super.clone();
		}
		catch(CloneNotSupportedException e){
			e.printStackTrace(System.err);
		}
		m.monde = (Case[][]) monde.clone(); 
		return m;
	}
	
	public Monde(int t)
	{
		taille = t;
		monde = (Case[][]) new Case[taille][taille].clone();
		for(int i=0;i<taille;i++)
		{
			for(int j=0;j<taille;j++)
			{
				monde[i][j] = new Case(i,j);
			}
		}
		
	}
	
	public int getSize()
	{
		return taille;
	}
	
	public Case getX(int x, int y)
	{
		return(monde[x][y]);
	}
	
	public String getSensations(Coordonnee p, Agent joueur)
	{
		String spit;
		String sgold;
		String swumpus;

		if(monde[p.getXReelInf()][p.getYReel()].pit())
		{
			spit = "Breeze";
		}

		else if(monde[p.getXReelSup()][p.getYReel()].pit())
		{
			spit = "Breeze";
		}
		
		else if(monde[p.getXReel()][p.getYReelInf()].pit())
		{
			spit = "Breeze";
		}
		else if(monde[p.getXReel()][p.getYReelSup()].pit())
		{
			spit = "Breeze";
		}
	
		else
		{
			spit = "";
		}
		
		if(monde[p.getXReel()][p.getYReel()].gold())
		{
			sgold = "Glitter";
		}
		/*else if(monde[p.getXReel()][p.getYReelSup()].gold())
		{
			sgold = "Glitter";
		}
		else if(monde[p.getXReelInf()][p.getYReel()].gold())
		{
			sgold = "Glitter";
		}
		else if(monde[p.getXReelSup()][p.getYReel()].gold())
		{
			sgold = "Glitter";
		}*/
		else
		{
			sgold = "";
		}
		
		if(monde[p.getXReel()][p.getYReelInf()].wumpus()&&(joueur.getWumpusTue() == false))
		{
			swumpus = "Stench";
		}
		else if(monde[p.getXReel()][p.getYReelSup()].wumpus()&&(joueur.getWumpusTue() == false))
		{
			swumpus = "Stench";
		}
		else if(monde[p.getXReelInf()][p.getYReel()].wumpus()&&(joueur.getWumpusTue() == false))
		{
			swumpus = "Stench";
		}
		else if(monde[p.getXReelSup()][p.getYReel()].wumpus()&&(joueur.getWumpusTue() == false))
		{
			swumpus = "Stench";
		}
		else
		{
			swumpus = "";
		}
		return("Sensations : " + spit + " " + sgold + " " + swumpus);
	}
	public String getSensationsMap(Coordonnee p,Agent joueur)
	{
		String spit = "-";
		String sgold = "-";
		String swumpus = "-";

		/*if (p.getX()-1<0);
		else if(monde[p.getX()-1][p.getY()].pit())
		{
			spit = "V";
		}

		else if (p.getX()+1>0);
		else if(monde[p.getX()+1][p.getY()].pit())
		{
			spit = "V";
		}
		else if (p.getY()-1<0);
		else if(monde[p.getX()][p.getY()-1].pit())
		{
			spit = "V";
		}
		else if (p.getY()+1>0);
		else if(monde[p.getX()][p.getY()+1].pit())
		{
			spit = "V";
		}
		
		if(monde[p.getX()][p.getY()].gold())
		{
			sgold = "g";
		}

		if (p.getY()-1<0);
		else if(monde[p.getX()][p.getY()-1].wumpus())
		{
			swumpus = "S";
		}
		else if (p.getY()+1>0);
		else if(monde[p.getX()][p.getY()+1].wumpus())
		{
			swumpus = "S";
		}
		else if (p.getX()-1<0);
		else if(monde[p.getX()-1][p.getY()].wumpus())
		{
			swumpus = "S";
		}
		else if (p.getX()+1>0);
		else if(monde[p.getX()+1][p.getY()].wumpus())
		{
			swumpus = "S";
		}*/
		
		if(monde[p.getXReelInf()][p.getYReel()].pit())
		{
			spit = "V";
		}

		else if(monde[p.getXReelSup()][p.getYReel()].pit())
		{
			spit = "V";
		}
		
		else if(monde[p.getXReel()][p.getYReelInf()].pit())
		{
			spit = "V";
		}
		else if(monde[p.getXReel()][p.getYReelSup()].pit())
		{
			spit = "V";
		}
	

		if(monde[p.getXReel()][p.getYReel()].gold())
		{
			sgold = "g";
		}
		/*else if(monde[p.getXReel()][p.getYReelSup()].gold())
		{
			sgold = "Glitter";
		}
		else if(monde[p.getXReelInf()][p.getYReel()].gold())
		{
			sgold = "Glitter";
		}
		else if(monde[p.getXReelSup()][p.getYReel()].gold())
		{
			sgold = "Glitter";
		}*/

		if(monde[p.getXReel()][p.getYReelInf()].wumpus()&&(joueur.getWumpusTue() == false))
		{
			swumpus = "s";
		}
		else if(monde[p.getXReel()][p.getYReelSup()].wumpus()&&(joueur.getWumpusTue() == false))
		{
			swumpus = "s";
		}
		else if(monde[p.getXReelInf()][p.getYReel()].wumpus()&&(joueur.getWumpusTue() == false))
		{
			swumpus = "s";
		}
		else if(monde[p.getXReelSup()][p.getYReel()].wumpus()&&(joueur.getWumpusTue() == false))
		{
			swumpus = "s";
		}

		return(""+spit+""+sgold+""+swumpus);
	}
	public boolean gold(Coordonnee p)
	{
		return monde[p.getXReel()][p.getYReel()].gold();
	}
	
	public boolean wumpus(Coordonnee p, Direction d, Agent joueur)
	{		
		if(d == Direction.NORD)
		{System.out.println(joueur.getWumpusTue());
			for (int i=p.getYReel();i<=3;i++){
				if ((monde[p.getXReel()][i].wumpus()== true)&&(joueur.getWumpusTue()== false)){
					joueur.wumpusTue();
					//monde[p.getXReel()][i].wumpusTue();
					return true;}
			}
		}
		
		else if(d == Direction.SUD)
		{System.out.println(joueur.getWumpusTue());
			for (int i=p.getYReel();i>=0;i--){
				if ((monde[p.getXReel()][i].wumpus() == true)&&(joueur.getWumpusTue()== false)){
					joueur.wumpusTue();
					//monde[p.getXReel()][i].wumpusTue();
					return true;}
			}
		}
		
		else if(d == Direction.EST)
		{System.out.println(joueur.getWumpusTue());
			for (int i=p.getXReel();i<=3;i++){
				if ((monde[i][p.getYReel()].wumpus() == true)&&(joueur.getWumpusTue()== false)){
					joueur.wumpusTue();
					//monde[i][p.getYReel()].wumpusTue();
					return true;}
			}
		}
		
		else if(d == Direction.OUEST)
		{
			System.out.println(joueur.getWumpusTue());
			for (int i=p.getXReel();i>=0;i--){
				if ((monde[i][p.getYReel()].wumpus() == true)&&(joueur.getWumpusTue()== false)){
					joueur.wumpusTue();
					//monde[i][p.getYReel()].wumpusTue();
					return true;}
			}
		}
		return false;
	}
	public Case getMonde(int i, int j){

		return monde[i-1][j-1];
	}
}
