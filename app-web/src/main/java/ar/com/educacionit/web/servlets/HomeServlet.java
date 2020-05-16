package ar.com.educacionit.web.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.com.educacionit.app.domain.Producto;
import ar.com.educacionit.services.ProductoService;
import ar.com.educacionit.services.exceptions.ServiceException;
import ar.com.educacionit.services.impl.ProductoServiceImpl;

@WebServlet(name = "HomeServlet", urlPatterns = "/api/home")
public class HomeServlet extends HttpServlet {

	private static final long serialVersionUID = 3429274211443195913L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("funciona!!!");

		ProductoService productoService = new ProductoServiceImpl();

		// consultar a la db para obtener un producto
		List<Producto> productos;
		
		try {
			productos = productoService.findProductos();
		} catch (ServiceException e) {
			throw new ServletException(e);
		}

		// guardar en la sesion los datos para mostrarlos luego en otro jsp
		req.getSession().setAttribute("productos", productos);

		// redireccionar a productos.jsp
		getServletContext().getRequestDispatcher("/productos.jsp").forward(req, resp);
	}
}
