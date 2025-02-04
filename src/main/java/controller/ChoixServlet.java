package controller;

import java.io.IOException;

import bll.ContactBLL;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/choix")
public class ChoixServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filtre = request.getParameter("filtre");
		
		ContactBLL bll = new ContactBLL();
		
		if (filtre == null || filtre.isBlank()) {
			request.setAttribute("contacts", bll.select());
		} else {
			request.setAttribute("contacts", bll.selectByNomOrPrenom(filtre));
		}
		request.getRequestDispatcher("/WEB-INF/jsp/choix.jsp").forward(request, response);
	}
}
