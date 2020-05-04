package ar.com.educacionit.dao;

import java.util.List;

import ar.com.eduacionit.app.domain.Producto;
import ar.com.eduacionit.app.domain.TipoProducto;
import ar.com.educacionit.dao.exceptions.DuplicateException;
import ar.com.educacionit.dao.exceptions.GenericExeption;

public interface ProductoDAO {

	public Producto getProducto(String codigo) throws GenericExeption;

	public List<Producto> findProductos() throws GenericExeption;

	public Producto createProducto(Producto producto) throws DuplicateException, GenericExeption;
	
	public Producto updateProducto(Producto producto) throws GenericExeption;

	public Producto deleteProducto(String codigoProducto) throws GenericExeption;
	
	public List<TipoProducto> findTipoProductos() throws GenericExeption;
}
