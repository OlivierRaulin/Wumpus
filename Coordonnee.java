
public class Coordonnee implements Cloneable
{
	private int x;
	private int y;
	
	public Object clone() {
		Object o =null;
		try {
			o = super.clone();
		}
		catch(CloneNotSupportedException e){
			e.printStackTrace(System.err);
		}
		return o;
	}
	
	
	public Coordonnee(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getXReelSup()
	{
		if(x<=3)
		{
			return x;
		}
		else return x-1;
	}
	
	public int getXReel()
	{
		return x - 1;
	}
	
	public int getXReelInf()
	{
		if(x>=2)
		{
		return x-2;
		}
		else return x-1;
	}
	
	
	public int getY()
	{
		return y;
	}
	
	public int getYReel()
	{
		return y-1;
	}
	
	public int getYReelSup()
	{
		if(y<=3)
		{
			return y;
		}
		else return y-1;
	}
	
	public int getYReelInf()
	{
		if(y>=2)
		{
			return y - 2;
		}
		else return y-1;
	}
	public void setX(int x)
	{
		this.x = x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	
	public String toString()
	{
		return("X : "+x+", Y : "+y);
	}
	
	public boolean equals(Coordonnee c2)
	{
		return((x == c2.getX()) && (y == c2.getY()));
	}
}
