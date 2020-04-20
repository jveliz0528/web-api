package ar.com.educacionit.ws.rest.rerources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ar.com.eduacionit.app.domain.Producto;
import ar.com.educacionit.services.ProductoService;
import ar.com.educacionit.services.exceptions.ServiceException;
import ar.com.educacionit.services.impl.ProductoServiceImpl;

@Path("productos")
public class ProductoResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllProductos() {
		
		ProductoService productoService = new ProductoServiceImpl();
		
		try {
			List<Producto> productos = productoService.findProductos();
			return Response.ok(productos).build();
		} catch (ServiceException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
	/**
	 * Servicio que retorna un producto dado su codigo
	 * @param codigoProducto
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{codigoProducto}")
	public Response getProductoByNombre(
			@PathParam("codigoProducto") String codigoProducto)  throws Exception{

		ProductoService productoService = new ProductoServiceImpl();

		try {
			//logica de negocio
			Producto producto = productoService.getProducto(codigoProducto);
			
			if(producto != null) {
				//jax-res -> json
				return Response.ok(producto).build();
			}else {
				return Response.status(Status.FORBIDDEN).build();
			}
		} catch (ServiceException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createProducto(Producto producto) {
		
		ProductoService productoService = new ProductoServiceImpl();
		
		try {
			productoService.createProducto(producto);
			
			return Response.status(Status.CREATED).entity(producto).build();
			
		} catch (ServiceException e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(producto).build();
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateProducto(Producto producto) {
		ProductoService productoService = new ProductoServiceImpl();
		
		try {
			productoService.updateProducto(producto);
			
			return Response.ok().entity(producto).build();
			
		} catch (ServiceException e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{codigoProducto}")
	public Response deleteProducto(
			@PathParam(value = "codigoProducto") String codigoProducto) {
		
		ProductoService productoService = new ProductoServiceImpl();
		Producto productoEliminado;
		try {
			productoEliminado = productoService.eliminarProducto(codigoProducto);
			return Response.ok().entity(productoEliminado).build();
		} catch (ServiceException e) {
			return Response.status(Status.NOT_FOUND).build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
