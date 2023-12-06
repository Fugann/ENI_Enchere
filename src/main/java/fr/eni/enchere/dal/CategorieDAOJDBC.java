package fr.eni.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import fr.eni.enchere.bo.Categorie;

public class CategorieDAOJDBC implements CategorieDAO {
	private static final String SELECT_ALL_CATEGORIES = "SELECT * FROM CATEGORIES";

	@Override
	public ArrayList<Categorie> getAllCategories() {
		
		try (Connection conn = ConnectionProvider.getConnection()){
			
			PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_CATEGORIES);
			ResultSet rs = pstmt.executeQuery();
			
			ArrayList<Categorie> categories = new ArrayList<Categorie>();
			Categorie categorie = null;
			while(rs.next()) {
				int no_categorie = rs.getInt("no_categorie");
				String libelle = rs.getString("libelle");
				
				categorie = new Categorie(no_categorie, libelle);
				categories.add(categorie);
			}
			System.out.println("Select des categories : succes");
			conn.close();
			return categories;
		} catch (Exception e) {
			System.out.println("Select des categories : echec");
			e.printStackTrace();
		}
		return null;
	}

}