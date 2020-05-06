package ar.com.educacionit.dao.hibernate.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import ar.com.eduacionit.app.domain.Producto;
import ar.com.eduacionit.app.domain.TipoProducto;
import ar.com.educacionit.dao.ProductoDAO;
import ar.com.educacionit.dao.exceptions.DuplicateException;
import ar.com.educacionit.dao.exceptions.GenericExeption;
import ar.com.educacionit.dao.hibernate.HibernateUtils;

public class ProductoDAOHibernateImpl implements ProductoDAO {

	private SessionFactory factory;
	
	public ProductoDAOHibernateImpl() {
		
		factory = HibernateUtils.getSessionFactory();
	}
	
	@Override
	public Producto getProducto(String codigo) throws GenericExeption {
		
		Session session = factory.getCurrentSession();

		Producto producto = null;
		
		try {

			// All the action with DB via Hibernate
			// must be located in one transaction.
			// Start Transaction.
			session.getTransaction().begin();

			// Create an HQL statement, query the object.
			String sql = "Select e from " + Producto.class.getName() + " e where e.codigo=:codigo ";

			// Create Query object.
			Query<Producto> query = session.createQuery(sql);

			query.setParameter("codigo", codigo);

			// Execute query.
			Optional<Producto> employees = query.uniqueResultOptional();

			if(employees.isPresent()) {
				producto = employees.get();
			}
			
			// Commit data.
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			// Rollback in case of an error occurred.
			session.getTransaction().rollback();
			throw new GenericExeption(e.getMessage(), e);
		}
		return producto;
	}

	@Override
	public List<Producto> findProductos() {
		Session session = factory.getCurrentSession();

		List<Producto> products = new ArrayList<Producto>();
		
		try {

			// All the action with DB via Hibernate
			// must be located in one transaction.
			// Start Transaction.
			session.getTransaction().begin();

			// Create an HQL statement, query the object.
			String sql = "Select e from " + Producto.class.getName() + " e ";

			// Create Query object.
			Query<Producto> query = session.createQuery(sql);

			// Execute query.
			products = query.getResultList();

			// Commit data.
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			// Rollback in case of an error occurred.
			session.getTransaction().rollback();
		}
		return products;
	}

	@Override
	public Producto createProducto(Producto producto) throws GenericExeption, DuplicateException {
		Session session = factory.getCurrentSession();

		try {

			// All the action with DB via Hibernate
			// must be located in one transaction.
			// Start Transaction.
			session.getTransaction().begin();

			session.saveOrUpdate(producto);
			
			// Commit data.
			session.getTransaction().commit();
		}catch (ConstraintViolationException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			throw new DuplicateException(e.getCause().getMessage(),e);
		} catch (Exception e) {
			e.printStackTrace();
			// Rollback in case of an error occurred.
			session.getTransaction().rollback();
			throw new GenericExeption(e.getMessage(),e);
		}finally {
			session.close();
		}
		return producto;
	}

	@Override
	public Producto updateProducto(Producto producto) throws GenericExeption {
		try {
			// return createProducto(producto);
			Session session = factory.getCurrentSession();

			try {
				// All the action with DB via Hibernate
				// must be located in one transaction.
				// Start Transaction.
				session.getTransaction().begin();

				// Create an HQL statement, query the object.
				String sql = "Select e from " + Producto.class.getName() + " e where e.codigo=:codigo ";

				// Create Query object.
				Query<Producto> query = session.createQuery(sql);
				
				query.setParameter("codigo", producto.getCodigo());

				// Execute query.
				Optional<Producto> productoOptional = query.uniqueResultOptional();

				Producto productoBean = null;
				if(productoOptional.isPresent()) {
					productoBean = productoOptional.get();
					productoBean.setDescripcion(producto.getDescripcion());
					productoBean.setPrecio(producto.getPrecio());
					productoBean.setTipoProducto(producto.getTipoProducto());
				}

				session.saveOrUpdate(productoBean);
				
				// Commit data.
				session.getTransaction().commit();
			}catch (ConstraintViolationException e) {
				e.printStackTrace();
				session.getTransaction().rollback();
				throw new DuplicateException(e.getCause().getMessage(),e);
			} catch (Exception e) {
				e.printStackTrace();
				// Rollback in case of an error occurred.
				session.getTransaction().rollback();
				throw new GenericExeption(e.getMessage(),e);
			}finally {
				session.close();
			}
			return producto;
		} catch (DuplicateException e) {
			throw new GenericExeption(e.getMessage(), e);
		}
	}

	@Override
	public Producto deleteProducto(String codigoProducto) throws GenericExeption {
		Producto producto = getProducto(codigoProducto);

		Session session = factory.getCurrentSession();

		try {

			// All the action with DB via Hibernate
			// must be located in one transaction.
			// Start Transaction.
			session.getTransaction().begin();
			
			session.remove(producto);
			
			// Commit data.
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			// Rollback in case of an error occurred.
			session.getTransaction().rollback();
			throw new GenericExeption(e.getMessage(),e);
		}finally {
			session.close();
		}
		return producto;
	}

	@Override
	public List<TipoProducto> findTipoProductos() throws GenericExeption {
		Session session = factory.getCurrentSession();

		List<TipoProducto> tipoProductos = new ArrayList<TipoProducto>();
		
		try {

			// All the action with DB via Hibernate
			// must be located in one transaction.
			// Start Transaction.
			session.getTransaction().begin();

			// Create an HQL statement, query the object.
			String sql = "Select e from " + TipoProducto.class.getName() + " e ";

			// Create Query object.
			Query<TipoProducto> query = session.createQuery(sql);

			// Execute query.
			tipoProductos = query.getResultList();

			// Commit data.
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			// Rollback in case of an error occurred.
			session.getTransaction().rollback();
		}
		return tipoProductos;
	}
}
