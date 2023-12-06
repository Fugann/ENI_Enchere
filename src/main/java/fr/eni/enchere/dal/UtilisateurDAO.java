package fr.eni.enchere.dal;

import fr.eni.enchere.bo.Utilisateur;

public interface UtilisateurDAO {
	
	public void insert(Utilisateur utilisateur);
	
	public String selectPseudoByPseudo(String pseudo);
	
	public String selectEmailByEmail(String email);

	//Utilisateur getUserByUsername(String username);
	
	public Utilisateur getUserByEmail(String userMail);
	
	//List<Utilisateur> getAllUsers();
}
