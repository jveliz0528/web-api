package ar.com.eduacionit.app.ws.meli.client;

import java.util.List;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import ar.com.eduacionit.app.ws.meli.client.dto.Site;

public class SiteRestExecutor extends RestRequestExecutor<List<Site>> {

	public SiteRestExecutor(String urlRestApi) {
		super(urlRestApi);
	}

	@Override
	protected List<Site> buildResponseDto(Response response) {

		GenericType<List<Site>> entity = new GenericType<List<Site>>() {
		};

		List<Site> sites = response.readEntity(entity);

		return sites;
	}

}
