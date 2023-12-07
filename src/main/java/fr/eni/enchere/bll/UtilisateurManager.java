package fr.eni.enchere.bll;

import java.util.ArrayList;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.DAOFactory;
import fr.eni.enchere.dal.UtilisateurDAO;

public class UtilisateurManager {

	private UtilisateurDAO utilisateurDAO;

	public UtilisateurManager() {
		this.utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}

	public void ajouter(Utilisateur u) {
		this.utilisateurDAO.insert(u);
	}

	public Utilisateur getUserDetails(String userEmail) {
		return this.utilisateurDAO.getUserByEmail(userEmail);
	}
	
	public String selectPseudoByPseudo(String pseudo) {
		return this.utilisateurDAO.selectPseudoByPseudo(pseudo);
	}
	
	public String selectEmailByEmail(String email) {
		return this.utilisateurDAO.selectEmailByEmail(email);
	}

	public ArrayList<Utilisateur> selectAllSaufMDP() {
		return this.utilisateurDAO.selectAllSaufMDP();
	}

	public Utilisateur getUserById(String userId) {
		return this.utilisateurDAO.getUserById(userId);
	}
}
