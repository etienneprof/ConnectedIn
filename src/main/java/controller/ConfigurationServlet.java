package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/configuration")
public class ConfigurationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer nbAcces = (Integer) session.getAttribute("acces");
		if (nbAcces != null) {
			nbAcces++;
			session.setAttribute("acces", nbAcces);
		} else {
			session.setAttribute("acces", 1);
		}
		
		request.getRequestDispatcher("/WEB-INF/jsp/configuration.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String defaut = request.getParameter("defaut");
		Cookie cookie = new Cookie("defaut", defaut);
		cookie.setMaxAge(60*60*24*10);
		response.addCookie(cookie);
		request.setAttribute("message", "La valeur " + defaut + " a bien été enregistrée.");
		request.getRequestDispatcher("/WEB-INF/jsp/configuration.jsp").forward(request, response);
	}
}
