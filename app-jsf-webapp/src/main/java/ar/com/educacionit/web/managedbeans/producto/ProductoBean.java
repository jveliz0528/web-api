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
	
	private Producto producto = new Producto();
	
	private String mensajeError;
	
	public List<Producto> findProductos() {
		List<Producto> productos;
		try {
			productos = productoService.findProductos();
		} catch (ServiceException e) {
			e.printStackTrace();
			mensajeError = e.getMessage();
			productos = new ArrayList<Producto>();
		}
		return productos;
	}

	public String nuevoProducto() {
		return "nuevo-producto";
	}
	
	public String crearNuevoProducto() {
		try {
			this.productoService.createProducto(this.producto);
		} catch (ServiceException e) {
			this.mensajeError = e.getMessage();
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
	
	public String eliminarProducto(String codigo) {
		try {
			this.producto =  this.productoService.eliminarProducto(codigo);
		} catch (ServiceException e) {
			return "nuevo-producto";
		}
		return "listado-productos";
	}
	
	public String updateProducto() {
		
		try {
			this.productoService.updateProducto(this.producto);
		} catch (ServiceException e) {
			e.printStackTrace();
			return "editar-producto"; 
		}
		
		return "listado-productos";
		
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}
	
}
