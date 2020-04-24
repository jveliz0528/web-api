package ar.com.educacionit.web.managedbeans;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "helloEduITManagedBean", eager = true)
public class HelloEduITManagedBean {

	public HelloEduITManagedBean() {
		System.out.println("HelloEduITManagedBean iniciado");
	}

	public String getMessage() {
		return "Hello World!";
	}
}
