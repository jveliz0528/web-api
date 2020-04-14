package ar.com.educacionit.dao.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import ar.com.eduacionit.app.domain.Producto;
import ar.com.educacionit.dao.AdministradorDeConexiones;
import ar.com.educacionit.dao.ProductoDAO;
import ar.com.educacionit.dao.exceptions.DuplicateException;
import ar.com.educacionit.dao.exceptions.GenericExeption;

public class ProductoDAOJDBCImpl implements ProductoDAO {

	private Connection con;

	public ProductoDAOJDBCImpl() {
		this.con = AdministradorDeConexiones.obtenerConexion();
	}

	@Override
	public Producto getProducto(String codigo) {

		PreparedStatement pst = null;

		ResultSet res = null;

		Producto producto = null;
		
		try {

			pst = this.con.prepareStatement("select * from PRODUCTO where codigo=?");

			pst.setString(1, codigo);
			
			res = pst.executeQuery();

			if(res.next()) {
				producto = new Producto(res.getLong("ID"), res.getString("DESCRIPCION"), res.getDouble("PRECIO"), res.getString("CODIGO"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cerrarConexiones(pst, res);
		}

		return producto;
	}

	@Override
	public List<Producto> findProductos() throws GenericExeption {

		List<Producto> productos = new ArrayList<>();

		PreparedStatement pst = null;

		ResultSet res = null;

		try {

			pst = this.con.prepareStatement("selec* from PRODUCTO");

			res = pst.executeQuery();

			while (res.next()) {
				Producto p = new Producto(res.getLong("ID"), res.getString("DESCRIPCION"), res.getDouble("PRECIO"), res.getString("CODIGO"));
				productos.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericExeption(e.getMessage(), e);
		} finally {
			cerrarConexiones(pst, res);
		}

		return productos;
	}

	/**
	 * Metodod que cirra las conexiones hacia la base de datos
	 * @param con
	 * @param pst
	 * @param res
	 */
	private void cerrarConexiones(PreparedStatement pst, ResultSet res) {
		try {
			if(res != null) {
				System.out.println("cerrando ResulSet");
				res.close();
			}
			if(pst != null) {
				System.out.println("cerrando PreparedStatement");
				pst.close();
			}			
		} catch (SQLException e1) {
			System.out.println("Error cerrando conexiones: " + e1.getMessage());
			e1.printStackTrace();
		}
	}
	
	/**
	 * Metodod que cirra las conexiones hacia la base de datos
	 * @param con
	 * @param pst
	 * @param res
	 */
	private void cerrarConexiones(Connection con) {
		try {			
			if(con!=null) {
				System.out.println("cerrando Connection");
				con.close();
			}
		} catch (SQLException e1) {
			System.out.println("Error cerrando conexiones: " + e1.getMessage());
			e1.printStackTrace();
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		cerrarConexiones(con);
	}

	@Override
	public Producto createProducto(Producto producto) throws GenericExeption, DuplicateException {
		
		PreparedStatement pst = null;

		ResultSet res = null;

		try {

			pst = this.con.prepareStatement("insert into PRODUCTO (DESCRIPCION,PRECIO,CODIGO) values(?,?,?)");

			pst.setString(1, producto.getDescripcion());
			
			pst.setDouble(2, producto.getPrecio());
			
			pst.setString(3, producto.getCodigo());
			
			pst.execute();
		}catch (SQLIntegrityConstraintViolationException e) {
			throw new DuplicateException(e.getMessage(),e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericExeption(e.getMessage(),e);
		} finally {
			cerrarConexiones(pst, res);
		}

		return producto;
	}
}
