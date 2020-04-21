package ar.com.eduacionit.app.ws.meli.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.logging.LoggingFeature;

public abstract class RestRequestExecutor<T> {

	protected String urlRestApi;
	
	public RestRequestExecutor(String urlRestApi) {
		this.urlRestApi = urlRestApi;
	}

	public T executeRestCall() throws Exception {
		
		Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFeature.class ) );
		
		WebTarget webTarget = this.buildWebTarget(client);
		 
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		 
		Response response = this.buildResponse(invocationBuilder);

		if(Status.OK.getStatusCode() != response.getStatus()) {
			throw new Exception(response.getStatusInfo().getReasonPhrase());
		}
		
		T responseDto = this.buildResponseDto(response);
		
		return responseDto;
	}

	protected abstract T buildResponseDto(Response response);

	/**
	 * Por defecto se ejecuta GET
	 * @param invocationBuilder
	 * @return
	 */
	private Response buildResponse(Builder invocationBuilder) {
		return invocationBuilder.get();
	}

	/**
	 * Arma la url del servicio rest que será consumido.
	 * Ej: http://www.pagina.com.ar/recurso
	 * @param client
	 * @param requestDto
	 * @return
	 */
	protected WebTarget buildWebTarget(Client client) {
		return client.target(getUrlRestApi());
	}

	public String getUrlRestApi() {
		return urlRestApi;
	}

	public void setUrlRestApi(String urlRestApi) {
		this.urlRestApi = urlRestApi;
	}
}
