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

@WebServlet("/modifier")
public class ModifierContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		ContactBLL bll = new ContactBLL();
		Contact contact = bll.select(id);
		
		request.setAttribute("contact", contact);
		request.setAttribute("action", "modifier");
		request.getRequestDispatcher("/WEB-INF/jsp/formulaire.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		LocalDate ddn = LocalDate.parse(request.getParameter("ddn"));
		String telephone = request.getParameter("telephone");
		String url = request.getParameter("url");
		String poste = request.getParameter("poste");
		String spe = request.getParameter("spe");
		
		ContactBLL bll = new ContactBLL();
		Contact contact = bll.select(id);
		
		contact.setNom(nom);
		contact.setPrenom(prenom);
		contact.setDateNaissance(ddn);
		contact.setTelephone(telephone);
		contact.setUrl(url);
		contact.setPoste(poste);
		contact.setSpecialite(spe);
		
		bll.update(contact);
		
		response.sendRedirect("contacts?id=" + contact.getId());
	}

}
