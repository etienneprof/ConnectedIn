package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import bll.UserBLL;
import bo.User;

@WebServlet("/deconnexion")
public class DeconnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate(); // je vide ma session
		
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie current : cookies) {
				if ("token".equals(current.getName())) {
					current.setMaxAge(0);
					response.addCookie(current);
				}
			}
		}
		
		response.sendRedirect("connexion"); // je ramène vers la page de connexion
	}
}
