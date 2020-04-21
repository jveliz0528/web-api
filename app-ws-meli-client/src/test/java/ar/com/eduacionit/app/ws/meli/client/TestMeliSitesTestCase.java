package ar.com.eduacionit.app.ws.meli.client;

import java.util.List;

import ar.com.eduacionit.app.ws.meli.client.dto.Site;
import junit.framework.TestCase;

public class TestMeliSitesTestCase extends TestCase {

	public void testMeliSites() {
		
		SiteRestExecutor sre = new SiteRestExecutor();
		
		List<Site> sites = sre.findSites();
		
		assertNotNull(sites);
		
	}
}
