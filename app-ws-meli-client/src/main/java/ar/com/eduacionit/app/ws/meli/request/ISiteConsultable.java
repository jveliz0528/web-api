package ar.com.eduacionit.app.ws.meli.request;

import java.util.List;

import ar.com.eduacionit.app.ws.meli.client.dto.Site;

public interface ISiteConsultable {

	public List<Site> findSites();
}
