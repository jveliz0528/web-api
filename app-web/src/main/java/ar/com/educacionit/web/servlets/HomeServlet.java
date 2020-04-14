package ar.com.educacionit.web.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.com.eduacionit.app.domain.Producto;

@WebServlet(name = "HomeServlet", urlPatterns = "/api/home")
public class HomeServlet extends HttpServlet {

	private static final long serialVersionUID = 3429274211443195913L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("funciona!!!");

		// consultar a la db para obtener un producto
		List<Producto> productos = findProductos();

		// guardar en la sesion los datos para mostrarlos luego en otro jsp
		req.getSession().setAttribute("productos", productos);

		// redireccionar a productos.jsp
		getServletContext().getRequestDispatcher("/productos.jsp").forward(req, resp);
	}
	
	public List<Producto> findProductos() {
		List<Producto> lista =  new ArrayList<Producto>();
		Producto producto = new Producto();
		producto.setId(1L);
		producto.setDescripcion("Pend driver");
		producto.setCodigo("abc");
		producto.setPrecio(175d);
		lista.add(producto);
		return lista;
	}
		
}
