package ar.com.educacionit.web.managedbeans.usuario;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import ar.com.educacionit.app.domain.User;

@Named
@SessionScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = -1132070778972244042L;

	private User usuario;
	private String[] roles;
	
	public boolean logueado( ) {
		return this.usuario != null;
	}
	
	public String logout() {
		setUsuario(null);
		setRoles(new String[]{});
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		
		return "login?faces-redirect=true";
	}

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
		if(usuario != null) {
			Set<String> roles = this.usuario.getRoles()
					.stream()
					.map(x->x.getRole())
					.collect(Collectors.toSet());
	
			int i=0;
			this.roles = new String[roles.size()];
			for(String role : roles) {
				this.roles[i++] = role;
			}
		}
	}

	public String[] getRoles() {
		return roles;
	}

	public String[] getUserRoles() {
		return this.usuario.getRoles().stream().map(x->x.getRole())
				.collect(Collectors.toSet()).toArray(new String[] {});
	}
	
	public void setRoles(String[] roles) {
		this.roles = roles;
	}
	
}
