package ar.com.educacionit.ws.rest.rerources;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import ar.com.eduacionit.app.domain.User;
import ar.com.educacionit.services.UserService;
import ar.com.educacionit.services.exceptions.ServiceException;
import ar.com.educacionit.services.impl.UserServiceImpl;

@Path("auth")
public class AuthResources {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(
			@QueryParam("userName") String username, 
			@QueryParam("password") String password) {

		ResponseBuilder response = Response.ok();
		
		MultivaluedMap<String, Object> header = response.build().getHeaders();

		UserService userService = new UserServiceImpl();
				
		try {
			User user = userService.getUserByUserName(username);
			
			// if(username.equals("howtodoinjava") && password.equals("password"))
			if(user !=null && user.getPassword().equals(password)) {
				
				if(user.getPassword().equals(password)) {
					header.putSingle("token", "123456789");
				}
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR);
		}
    	return response.build();
	}
}
