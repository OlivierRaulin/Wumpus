
public class Agent implements Cloneable{
	private String nom;
	private boolean fleche;
	private Coordonnee position;
	private int score;
	private Direction direction;
	private boolean wumpusTue;
	private boolean orRamasse;
	
	public Object clone() {
		Agent agent =null;
		try {
			agent = (Agent)super.clone();
		}
		catch(CloneNotSupportedException e){
			e.printStackTrace(System.err);
		}
		agent.position = (Coordonnee) position.clone();
		return agent;
	}
	
	
	public Agent(String name){
		nom = name;
		fleche = true;
		position = new Coordonnee(1,1);
		score = 0;
		direction = Direction.NORD;
		wumpusTue=false;
		orRamasse=false;
	}
	
	public void wumpusTue(){
		wumpusTue = true;
	}
	
	public boolean getWumpusTue(){
		return wumpusTue;
	}
	
	public void setName(String nom){
		this.nom = nom;
	}
	
	
	public void setPosition(Coordonnee emplacement){
		position.setX(emplacement.getX());
		position.setY(emplacement.getY());
	}
	
	public Coordonnee getPosition()
	{
		return position;
	}
	

	
	public Coordonnee getPositionOrigine()
	{
		return new Coordonnee(1,1);
	}
	
	public void setFleche (boolean bool){
		fleche = bool;
	}
	
	public boolean getFleche()
	{
		return fleche;
	}
	
	public int modifScore(int modif){
		score += modif;
		return score;
	}
	
	public void setDirection(Direction newDirection){
		direction = newDirection;
	}
	
	public Direction getDirection()
	{
		return direction;
	}
	
	public void rejouer()
	{
		fleche=true;
		position = new Coordonnee(1,1);
		direction = Direction.NORD;
	}
	
	public String getName()
	{
		return nom;
	}
	
	public String getNom()
	{
		return nom;
	}
		
	
	public String getScore()
	{
		String point = "point";
		String points = "points";
		if(score == 0)
		{
		return(score+ " "+point);
		}
		else return(score + " "+points);
	}
	
	public void setScore(int newScore)
	{
		score += newScore;
	}
	
	public void avance(Direction d)
	{
		
		if(d.equals(Direction.NORD))
		{
			position.setY(position.getY() + 1);
		}
		
		if(d.equals(Direction.EST))
		{
			position.setX(position.getX() + 1);
		}
		
		if(d.equals(Direction.SUD))
		{
			position.setY(position.getY() - 1);
		}
		
		if(d.equals(Direction.OUEST))
		{
			position.setX(position.getX() - 1);
		}
	}
}
