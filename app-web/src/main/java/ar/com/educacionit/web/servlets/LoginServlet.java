package ar.com.educacionit.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.com.educacionit.web.dto.User;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -5482717801125483358L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		if("pepe".equals(username) && password.equals(password)) {
			User user = new User(username);
			req.getSession(true).setAttribute("user", user);
			// redireccionar a productos.jsp
			getServletContext().getRequestDispatcher("/productos.jsp").forward(req, resp);
		}else {
			
		}
	}
}
