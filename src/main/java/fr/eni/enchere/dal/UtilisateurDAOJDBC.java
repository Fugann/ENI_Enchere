package fr.eni.enchere.dal;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.eni.enchere.bo.Utilisateur;

public class UtilisateurDAOJDBC implements UtilisateurDAO {

	private static final String INSERT_UTILISATEUR = "INSERT INTO UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES(?,?,?,?,?,?,?,?,?,?,?);";
	private static final String GET_USER_BY_EMAIL = "SELECT * FROM UTILISATEURS WHERE email = ?";
	private static final String SELECT_PSEUDO_BY_PSEUDO = "SELECT PSEUDO FROM UTILISATEURS WHERE PSEUDO = ?";
	private static final String SELECT_EMAIL_BY_EMAIL = "SELECT EMAIL FROM UTILISATEURS WHERE EMAIL = ?";
	private static final String SELECT_ALL_SAUF_MDP = "SELECT NO_UTILISATEUR, PSEUDO, NOM, PRENOM, EMAIL, TELEPHONE, RUE, CODE_POSTAL, VILLE FROM UTILISATEURS";

	@Override
	public void insert(Utilisateur utilisateur) {
		try (Connection conn = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = conn.prepareStatement(INSERT_UTILISATEUR,
					PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setString(7, utilisateur.getCode_postal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setString(9, utilisateur.getMot_de_passe());
			pstmt.setInt(10, utilisateur.getCredit());
			pstmt.setByte(11, utilisateur.getAdministrateur());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				utilisateur.setNo_utilisateur(rs.getInt(1));
			}

			conn.close();
			System.out.println("Ajout de l'utilisateur : succes");

		} catch (Exception e) {
			System.out.println("Ajout de l'utilisateur : echec");
			e.printStackTrace();
		}

	}

	@Override
	public Utilisateur getUserByEmail(String userEmail) {
		Utilisateur utilisateur = null;

		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(GET_USER_BY_EMAIL)) {

			pstmt.setString(1, userEmail);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					// Populate the Utilisateur object with data from the ResultSet
					utilisateur = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"),
							rs.getString("nom"), rs.getString("prenom"), rs.getString("email"),
							rs.getString("telephone"), rs.getString("rue"), rs.getString("code_postal"),
							rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"),
							rs.getByte("administrateur"));
				}
			}

		} catch (SQLException e) {
			// Handle the exception or log it appropriately
			e.printStackTrace();
		}

		return utilisateur;
	}

	@Override
	public String selectPseudoByPseudo(String pseudo) {
		try (Connection conn = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = conn.prepareStatement(SELECT_PSEUDO_BY_PSEUDO);
			pstmt.setString(1, pseudo);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				return rs.getString("pseudo");

			}
			System.out.println("comparaison des pseudos = success");
			conn.close();

		} catch (Exception e) {
			System.out.println("Comparaison des pseudos : echec");
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public String selectEmailByEmail(String email) {
		try (Connection conn = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = conn.prepareStatement(SELECT_EMAIL_BY_EMAIL);
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				return rs.getString("email");
			}
			System.out.println("comparaison des emails = success");
			conn.close();

		} catch (SQLException e) {
			System.out.println("comparaison des emails = echec");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Utilisateur> selectAllSaufMDP() {
		try (Connection conn = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_SAUF_MDP);
			ResultSet rs = pstmt.executeQuery();

			ArrayList<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
			Utilisateur utilisateur = null;
			while(rs.next()) {
				int no_utilisateur = rs.getInt("no_utilisateur");
				String pseudo = rs.getString("pseudo");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String email = rs.getString("email");
				String telephone = rs.getString("telephone");
				String rue = rs.getString("rue");
				String code_postal = rs.getString("code_postal");
				String ville = rs.getString("ville");
				
				utilisateur = new Utilisateur(no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville);
				utilisateurs.add(utilisateur);
			}
			System.out.println("Select des utilisateurs = success");
			conn.close();
			return utilisateurs;
		} catch (SQLException e) {
			System.out.println("Select des utilisateurs = echec");
			e.printStackTrace();
		}
		return null;
	}
}
