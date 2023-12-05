package fr.eni.enchere;

import jakarta.servlet.RequestDispatcher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;


/**
 * Servlet implementation class ajoutVente
 */
public class ajoutVente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/ajoutVente.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/ajoutVente.jsp");
		
		String article = request.getParameter("article");
		String description = request.getParameter("description");
		String categorie = request.getParameter("categorie");
		String photo = request.getParameter("photo");
		String prix = request.getParameter("prix");
		LocalDate debut = LocalDate.parse(request.getParameter("debut"));
		LocalDate fin = LocalDate.parse(request.getParameter("fin"));
		String rue = request.getParameter("rue");
		String CP = request.getParameter("CP");
		String ville = request.getParameter("ville");
		System.out.println(debut);
		System.out.println(fin);
		
		if (article == null || article.equals("") || description == null || description.equals("") || categorie == null
				|| categorie.equals("") || rue == null || rue.equals("") || CP == null || CP.equals("") || ville == null
				|| ville.equals("")) {
			request.setAttribute("error", "Veuillez remplir tous les champs !");
		}
		

	}
}
