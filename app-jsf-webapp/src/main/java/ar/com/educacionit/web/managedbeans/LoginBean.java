package ar.com.educacionit.web.managedbeans;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import ar.com.eduacionit.app.domain.User;
import ar.com.educacionit.services.UserService;
import ar.com.educacionit.services.exceptions.ServiceException;
import ar.com.educacionit.services.impl.UserServiceImpl;

@ManagedBean()
@RequestScoped
public class LoginBean {

	private String user;
	
	private String password;
	
	private String error;

	private UserService userService = new UserServiceImpl();
	
	public String login() {
		
		User user;
		try {
			user = userService.getUserByUserName("ADMIN");
			
			if(user !=null && user.getPassword().equals(password)) {
				Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
				sessionMap.put("usuario", user);
				return "login-success";
			    
			}else {
				error = "Bad User/password";
				return "login";
			}
		} catch (ServiceException e) {
			error = e.getMessage();
			return "login";
		}
	}
		
	public String login2() {
		
		
		
		if (user.equalsIgnoreCase("eduit") && password.equals("eduit")) {
			Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
			sessionMap.put("usuario", "EDUIT");
			sessionMap.put("nick", "eduitnick");
			return "login-success";
		} else {
			error = "Bad User/password";
			return "login";
		}
	}

	
	public String home() {
		return "home";
	}
	
	public String getPassword() {
		return password;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
}
