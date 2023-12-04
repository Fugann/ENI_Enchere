package fr.eni.ecole.enchere.dal;

public abstract class DAOFactory {
	
	public static UtilisateurDAO getUtilisateurDAO()
	{
		return new utilisateurJDBC();
	}
	
}
