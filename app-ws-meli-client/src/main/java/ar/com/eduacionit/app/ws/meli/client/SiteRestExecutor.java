package ar.com.eduacionit.app.ws.meli.client;

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

import ar.com.eduacionit.app.ws.meli.client.dto.Site;

public class SiteRestExecutor  {

	public List<Site> findSites() 	{
		
		Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFeature.class ) );
		
		WebTarget webTarget = client.target("https://api.mercadolibre.com/").path("sites");
		 
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		 
		Response response = invocationBuilder.get();

		GenericType<List<Site>> entity = new GenericType<List<Site>>(){		
		};
		List<Site> sites = response.readEntity(entity);
		//logica de consulta al sistema externo
		
		//conersion de la respuesta al tipo de datos que necesita
		
		//return 
		return sites;
	}

}
