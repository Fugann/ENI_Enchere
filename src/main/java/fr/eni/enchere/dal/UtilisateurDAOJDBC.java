package fr.eni.enchere.dal;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.enchere.bo.Utilisateur;

public class UtilisateurDAOJDBC implements UtilisateurDAO {

	private static final String INSERT_UTILISATEUR = "INSERT INTO UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES(?,?,?,?,?,?,?,?,?,?,?);";
	private static final String SELECT_PSEUDO_BY_PSEUDO = "SELECT PSEUDO FROM UTILISATEURS WHERE PSEUDO = ?";
	private static final String SELECT_EMAIL_BY_EMAIL = "SELECT EMAIL FROM UTILISATEURS WHERE EMAIL = ?";
	
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
}
