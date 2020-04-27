package ar.com.educacionit.web.managedbeans.producto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import ar.com.eduacionit.app.domain.Producto;
import ar.com.educacionit.services.ProductoService;
import ar.com.educacionit.services.exceptions.ServiceException;
import ar.com.educacionit.services.impl.ProductoServiceImpl;

@Named
@RequestScoped
public class ProductoBean implements Serializable{

	private static final long serialVersionUID = -8975956926465148459L;

	private ProductoService productoService = new ProductoServiceImpl();
	
	private List<Producto> productos;
	
	private Producto producto = new Producto();
	
	public String consultarTodos() {
		try {
			this.productos = productoService.findProductos();
		} catch (ServiceException e) {
			e.printStackTrace();
			this.productos = new ArrayList<Producto>();
		}
		
		return "listado-productos";
	}

	public String nuevoProducto() {
		return "nuevo-producto";
	}
	
	public String crearNuevoProducto() {
		try {
			this.productoService.createProducto(this.producto);
		} catch (ServiceException e) {
			return "nuevo-producto";
		}
		return "listado-productos";
	}
	
	public String editarProducto(String codigo) {
		try {
			this.producto =  this.productoService.getProducto(codigo);
		} catch (ServiceException e) {
			return "nuevo-producto";
		}
		return "editar-producto";
	}
	
	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
}
