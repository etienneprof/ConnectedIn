package controller;

import java.io.IOException;

import bll.ContactBLL;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/contacts")
public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int index = Integer.valueOf(request.getParameter("id"));
		ContactBLL bll = new ContactBLL();
		request.setAttribute("contact", bll.select(index));
		
		request.getRequestDispatcher("/WEB-INF/jsp/contact.jsp").forward(request, response);
	}
}
