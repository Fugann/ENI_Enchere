package fr.eni.enchere.dal;

public abstract class CodesErrorDAL {
	
	/**
	 * Echec quand l'article est vide
	 */
	public static final int INSERT_OBJET_NULL = 30000;
	
	/**
	 * Echec SQL
	 */
	public static final int INSERT_OBJET_ECHEC = 30001;

}
