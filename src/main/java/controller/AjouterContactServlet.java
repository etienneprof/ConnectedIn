package controller;

import java.io.IOException;
import java.time.LocalDate;

import bll.ContactBLL;
import bo.Contact;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ajouter")
public class AjouterContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("action", "ajouter");
		request.getRequestDispatcher("/WEB-INF/jsp/formulaire.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		LocalDate ddn = LocalDate.parse(request.getParameter("ddn"));
		String telephone = request.getParameter("telephone");
		String url = request.getParameter("url");
		String poste = request.getParameter("poste");
		String spe = request.getParameter("spe");
		
		Contact contact = new Contact(nom, prenom, ddn, telephone, url, poste, spe);
		
		ContactBLL bll = new ContactBLL();
		bll.insert(contact);
		
		// deux choix :
		response.sendRedirect("contacts?id=" + contact.getId());
	}

}
