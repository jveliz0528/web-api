package ar.com.educacionit.web.managedbeans.login;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ar.com.eduacionit.app.domain.User;
import ar.com.educacionit.services.UserService;
import ar.com.educacionit.services.exceptions.ServiceException;
import ar.com.educacionit.services.impl.UserServiceImpl;
import ar.com.educacionit.web.managedbeans.usuario.UsuarioBean;

@Named
@RequestScoped
public class LoginBean implements Serializable{

	private static final long serialVersionUID = 1909648574315208370L;

	@Inject
	private UsuarioBean usuarioBean;
	
	private String user;
	
	private String password;
	
	private UserService userService = new UserServiceImpl();
	
	public String login() {
		
		User user;
		try {
			user = userService.getUserByUserName(this.user);
			
			if(user !=null && user.getPassword().equals(password)) {
				usuarioBean.setUsuario(user);
				return "login-success";
			    
			}else {
				return "login-fail";
			}
		} catch (ServiceException e) {
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

	public UsuarioBean getUsuarioBean() {
		return usuarioBean;
	}

	public void setUsuarioBean(UsuarioBean usuarioBean) {
		this.usuarioBean = usuarioBean;
	}
	
}
