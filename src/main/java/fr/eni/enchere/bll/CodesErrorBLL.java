package fr.eni.enchere.bll;

public abstract class CodesErrorBLL {
	/**
	 * Echec quand un des input est vide
	 */
	public static final int INPUT_EMPTY_ERROR=20000;
	
	/**
	 * Echec quand la date de début d'enchères est inférieure à la date de fin
	 */
	public static final int DATE_ERROR=20001;
	
	/**
	 * Echec quand le prix n'est pas en chiffre
	 */
	public static final int PRICE_EMPTY_ERROR = 20002;
	
	/**
	 * Echec quand les 2 mots de passe saisis ne correspondent pas 
	 */
	public static final int SAME_PASSWORD_ERROR = 20004;
	
	/**
	 * Echec quand le pseudo est déjà créé
	 */
	public static final int CREATE_PSEUDO_ERROR = 20005;
	
	/**
	 * Echec quand l'email est déjà créé
	 */
	public static final int CREATE_EMAIL_ERROR = 20006;
	
	
}
