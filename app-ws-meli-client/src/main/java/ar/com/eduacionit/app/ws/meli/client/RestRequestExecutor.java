package ar.com.eduacionit.app.ws.meli.client;

import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.logging.LoggingFeature;

import ar.com.eduacionit.app.ws.meli.client.dto.Site;

public abstract class RestRequestExecutor<T> {

	public T get(String endPoint, Map<String, String> params ) {
		
		Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFeature.class ) );
		
		WebTarget webTarget = client.target(endPoint).path("site");
		 
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		 
		Response response = invocationBuilder.get();

		GenericType<List<Site>> entity = new GenericType<List<Site>>(){		
		};
		List<Site> productos = response.readEntity(entity);
		//logica de consulta al sistema externo
		
		
		//conersion de la respuesta al tipo de datos que necesita
		
		
		//return 
		
		return null;
		//return entity;
	}
}
