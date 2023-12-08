package fr.eni.enchere.bll;

import java.util.ArrayList;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.DAOFactory;
import fr.eni.enchere.dal.utilisateur.UtilisateurDAO;
import fr.eni.enchere.dal.utilisateur.UtilisateurDAOJDBC;
import fr.eni.enchere.error.BusinessException;

public class UtilisateurManager {

	private UtilisateurDAO utilisateurDAO;

	public UtilisateurManager() {
		this.utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}

	public void ajouter(Utilisateur u) {
		this.utilisateurDAO.insert(u);
	}

	public Utilisateur getUserDetails(String identifiant, String password) throws BusinessException {
		BusinessException exception = new BusinessException();
		
		password = Utilisateur.hashPwd(password);
		
		System.out.println("test");
		Utilisateur user = this.utilisateurDAO.getUserByEmail(identifiant);
		
		if (user == null) {
			System.out.println("test1");
			user = this.utilisateurDAO.getUserByPseudo(identifiant);
		}

		this.verifIdentifiant(user,identifiant, password,  exception);

		if (!exception.hasErreurs()) {
			System.out.println("test3");
			return user;
		} else {
			throw exception;
		}
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

	public void updateUser(Utilisateur user) {
		UtilisateurDAO utilisateurDAO = new UtilisateurDAOJDBC();
		utilisateurDAO.update(user);
	}

	private void verifIdentifiant(Utilisateur user, String identifiant, String password, BusinessException exception) {
		if (user == null || !password.equals(user.getMot_de_passe())) {
			System.out.println("test 2");
			exception.ajouterErreur(CodesErrorBLL.IDENTIFIANT_MDP_ERROR);
		}
	}
	
	public void deleteUser(int userId) {
        utilisateurDAO.delete(userId);
    }
}
