package fr.eni.enchere.dal.utilisateur;

import java.util.ArrayList;

import fr.eni.enchere.bo.Utilisateur;

public interface UtilisateurDAO {
	
	public void insert(Utilisateur utilisateur);
	
	public String selectPseudoByPseudo(String pseudo);
	
	public String selectEmailByEmail(String email);

	public Utilisateur getUserByEmail(String userMail);

	public Utilisateur getUserByPseudo(String identifiant);
	
	public ArrayList<Utilisateur> selectAllSaufMDP();
	
	public Utilisateur getUserById (String userId);

	public void update(Utilisateur user);

}
