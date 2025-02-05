package filter;

import java.io.IOException;

import bll.UserBLL;
import bo.User;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(
		dispatcherTypes = DispatcherType.REQUEST,
		urlPatterns = "/*"
)
public class ConnexionFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		String url = httpRequest.getServletPath();
		
		// 1. J'autorise l'accès aux ressources qui doivent toujours être accessibles
		if (url.contains(".css") || url.contains("connexion") || url.contains("inscription")) {
			chain.doFilter(httpRequest, httpResponse);
			return;
		}
		
		// 2. J'autorise l'accès à condition que l'utilisateur soit connecté
		User user = (User) httpRequest.getSession().getAttribute("user");
		if (user != null) {
			chain.doFilter(httpRequest, httpResponse);
			return;
		}
		
		// 3. J'autorise l'accès à condition que le cookie existe (qu'on ait coché au préalable l'option "se souvenir de moi")
		Cookie[] cookies = httpRequest.getCookies();
		if (cookies != null) {
			for (Cookie current : cookies) {
				if ("token".equals(current.getName())) {
					UserBLL bll = new UserBLL();
					User userByToken = bll.selectByToken(current.getValue());
					if (userByToken != null) {
						httpRequest.getSession().setAttribute("user", userByToken);
						chain.doFilter(httpRequest, httpResponse);
						return;
					}
				}
			}
		}

		// 4. Si aucune des conditions précédentes n'est respectée, je renvoie vers la page de connexion
		httpResponse.sendRedirect("connexion");
	}
}
