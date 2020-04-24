package ar.com.educacionit.web.managedbeans;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean()
@RequestScoped
public class UsuarioBean {

	public boolean logueado( ) {
		Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		return sessionMap.get("usuario") != null;
	}
}
