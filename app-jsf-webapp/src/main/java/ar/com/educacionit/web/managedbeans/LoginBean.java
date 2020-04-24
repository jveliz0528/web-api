package ar.com.educacionit.web.managedbeans;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean()
@RequestScoped
public class LoginBean {

	private String user;
	
	private String password;
	
	private String error;

	public String login() {
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
