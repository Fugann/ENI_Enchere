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
}
