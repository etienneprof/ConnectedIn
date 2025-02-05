package controller;

import java.io.IOException;

import bll.UserBLL;
import bo.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/connexion")
public class ConnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/connexion.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String souvenir = request.getParameter("souvenir");
		
		UserBLL bll = new UserBLL();
		User user = bll.selectByLoginAndPassword(login, password);
		if (user == null) {
			request.setAttribute("message", "Identifiants invalides.");
			request.getRequestDispatcher("/WEB-INF/jsp/connexion.jsp").forward(request, response);
		} else {
			if (souvenir != null) {
				String token = bll.generateToken(user);
				Cookie cookie = new Cookie("token", token);
				cookie.setMaxAge(60*60*24*7);
				response.addCookie(cookie);
			}
			request.getSession().setAttribute("user", user);
			response.sendRedirect("choix");
		}
	}
}
