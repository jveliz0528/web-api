package ar.com.educacionit.ws.rest.rerources;

import java.util.Arrays;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("auth")
public class AuthResources {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(String username, String password) {
		
		return Response.ok(Arrays.asList("hola", "chau")).build();
	}
}
