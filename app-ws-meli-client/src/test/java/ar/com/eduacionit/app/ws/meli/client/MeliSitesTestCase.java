package ar.com.eduacionit.app.ws.meli.client;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import ar.com.eduacionit.app.ws.meli.client.dto.Site;

public class MeliSitesTestCase {

	@Test
	public void testMeliSites() {
		
		SiteRestExecutor sre = new SiteRestExecutor("https://api.mercadolibre.com/sites");
		
		try {
			List<Site> sites = sre.executeRestCall();
			System.out.println(sites);
			assertNotNull(sites);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
