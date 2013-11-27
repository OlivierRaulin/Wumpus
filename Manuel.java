public class Manuel
{
	public Manuel()
	{}
	
	public String getMan(String s)
	{
	if(s.equalsIgnoreCase("droite"))
	{
		return("Tourne votre personnage de 90° vers la droite, affiche votre nouvelle direction(N, S, E, O)");
	}
	if(s.equalsIgnoreCase("gauche"))
	{
		return("Tourne votre personnage de 90° vers la gauche, affiche votre nouvelle direction(N, S, E, O)");
	}
	if(s.equalsIgnoreCase("avance"))
	{
		return("Fait avancer votre personnage d'une case, en fonction de votre direction");
	}
	if(s.equalsIgnoreCase("tire"))
	{
		return("Tire une flèche, en fonction de votre direction");
	}
	if(s.equalsIgnoreCase("ramasse"))
	{
		return("Ramasse l'or, si présent");
	}
	if(s.equalsIgnoreCase("man"))
	{
		return("Affiche le manuel d'une commande");
	}
	if(s.equalsIgnoreCase("grimpe"))
	{
		return("Vous fait sortir du jeu, si vous êtes à la case d'origine uniquement");
	}
	if(s.equalsIgnoreCase("plouf"))
	{
		return("Vous permet de faire un coup dans l'eau, afin de ne pas être déconnecté automatiquement");
	}
	if(s.equalsIgnoreCase("quit"))
	{
		return("Affiche votre score, et quitte le jeu");
	}
	else
	{
		return("Pas de manuel pour "+s);
	}
	
	
	
	}
}
