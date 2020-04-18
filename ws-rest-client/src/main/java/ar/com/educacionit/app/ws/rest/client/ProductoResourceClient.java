package ar.com.educacionit.app.ws.rest.client;


import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.logging.LoggingFeature;

import ar.com.eduacionit.app.domain.Producto;


public class ProductoResourceClient {

	public static void main(String[] args) {

		Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFeature.class ) );
		
		WebTarget webTarget = client.target("http://localhost:8080/app-ws-rest/api/").path("productos/a002");
		 
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		 
		Response response = invocationBuilder.get();

		Producto producto = response.readEntity(Producto.class);
		
		System.out.println(producto);
	}

	public Producto getProducto(Long id) {
		Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFeature.class ) );
		
		WebTarget webTarget = client.target("http://localhost:8080/app-rest/api/").path("productos/"+id);
		 
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		 
		Response response = invocationBuilder.get();

		Producto producto = response.readEntity(Producto.class);
		
		return producto;
	}
	
	public List<Producto> getProductos() {
		Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFeature.class ) );
		
		WebTarget webTarget = client.target("http://localhost:8080/app-rest/api/").path("productos");
		 
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		 
		Response response = invocationBuilder.get();

		GenericType<List<Producto>> entity = new GenericType<List<Producto>>(){		
		};
		List<Producto> productos = response.readEntity(entity);
		
		return productos;
	}
}
