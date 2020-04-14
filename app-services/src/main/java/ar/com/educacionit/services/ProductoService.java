package ar.com.educacionit.services;

import java.util.List;

import ar.com.eduacionit.app.domain.Producto;
import ar.com.educacionit.services.exceptions.ServiceException;

public interface ProductoService {

	public Producto getProducto(String codigo) throws ServiceException;

	public List<Producto> findProductos() throws ServiceException;

	public Producto createProducto(Producto producto) throws ServiceException;
}
