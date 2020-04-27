package ar.com.educacionit.web.managedbeans;

import java.io.Serializable;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ar.com.eduacionit.app.domain.User;
import ar.com.educacionit.services.UserService;
import ar.com.educacionit.services.exceptions.ServiceException;
import ar.com.educacionit.services.impl.UserServiceImpl;

@Named
@RequestScoped
public class LoginBean implements Serializable{

	private static final long serialVersionUID = 1909648574315208370L;

	@Inject
	private UsuarioBean usuarioBean;
	
	private String user;
	
	private String password;
	
	private String error;

	private UserService userService = new UserServiceImpl();
	
	public String login() {
		
		User user;
		try {
			user = userService.getUserByUserName("ADMIN");
			
			if(user !=null && user.getPassword().equals(password)) {
				usuarioBean.setUsuario(user);
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

	public UsuarioBean getUsuarioBean() {
		return usuarioBean;
	}

	public void setUsuarioBean(UsuarioBean usuarioBean) {
		this.usuarioBean = usuarioBean;
	}
	
}
