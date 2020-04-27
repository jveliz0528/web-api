package ar.com.educacionit.web.managedbeans;

import javax.inject.Named;

@Named
public class HelloEduITManagedBean {

	public HelloEduITManagedBean() {
		System.out.println("HelloEduITManagedBean iniciado");
	}

	public String getMessage() {
		return "Hello World!";
	}
}
