import java.lang.Math;
public class Case implements Cloneable
{
	private Coordonnee coord;
	private boolean wumpus;
	private static int wumpusCrees;
	private boolean pit;
	private boolean gold;
	private static int goldCrees;
	private static int casesCreees;
	
	public Object clone() {
		Case c =null;
		try {
			c = (Case)super.clone();
		}
		catch(CloneNotSupportedException e){
			e.printStackTrace(System.err);
		}
		c.coord = (Coordonnee) coord.clone();
		return c;
	}
	
	public Case(int x, int y)
	{
		coord = (Coordonnee) new Coordonnee(x, y).clone();
		//Timestamp temp = new Timestamp(System.currentTimeMillis());
		double rand = Math.random();
		//agent=false;
		if (x==0 && y==0){/*agent=true;*/}
		else{
			wumpus = false;
			if(20* Math.random() < 2)
			{
				if(wumpusCrees !=1)
				{
					wumpusCrees = 1;
					wumpus = true;
				}
			}
			else {
				if(rand<0.2)
				{
					pit=true;
				}
				else pit = false;
			}
		}
		
		if((32* Math.random() < 3)&&(pit == false))
		{
			if(goldCrees !=1)
			{
				goldCrees = 1;
				gold = true;
			}
			
		}
		if(casesCreees==16)
		{
			if(wumpusCrees == 0)
			{
				wumpus = true;
				wumpusCrees = 1;
			}
			if(goldCrees == 0)
			{
			gold = true;
			goldCrees = 1;
			}
			pit = false;
		}
		casesCreees++;
				
	}
	
	public boolean wumpus()
	{
		return wumpus;
	}
	
	public boolean pit()
	{
		return pit;
	}
	
	public boolean gold()
	{
		return gold;
	}
	
	public Coordonnee getCoord()
	{
		return coord;
	}
	public Contenu getContenu(Agent joueur){
		if (wumpus==true && !joueur.getWumpusTue()){return Contenu.WUMPUS;}
		else if (pit==true){return Contenu.TROU;}
		else if (gold==true){return Contenu.OR;}
		else if (joueur.getPosition().getXReel()==this.coord.getX()&&joueur.getPosition().getYReel()==this.coord.getY())/*agent==true*/{return Contenu.AGENT;}
		return Contenu.VIDE;
	}
	/*public void setAgent(){
		if (agent==false){agent=true;}
		else {agent=false;}		
	}*/
	/*public void wumpusTue(){
		wumpus=false;
	}*/
	public void orRamasse(){
		gold=false;
	}
}
