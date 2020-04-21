package ar.com.educacionit.web.servlets.meli;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.com.eduacionit.app.ws.meli.client.CategoryRestExecutor;
import ar.com.eduacionit.app.ws.meli.client.dto.cetegory.Categoria;

@WebServlet(name = "MeliServlet", urlPatterns = "/meli/categorias")
public class MeliServlet extends HttpServlet {

	private static final long serialVersionUID = 1792933180304887293L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		CategoryRestExecutor cre = new CategoryRestExecutor("https://api.mercadolibre.com/categories/MLA1071");
		
		try {
			Categoria categoria = cre.executeRestCall();
			resp.getWriter().print(categoria);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
