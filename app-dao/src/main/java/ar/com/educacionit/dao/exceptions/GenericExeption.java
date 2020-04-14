package ar.com.educacionit.dao.exceptions;

public class GenericExeption extends Exception {

	private static final long serialVersionUID = -6818721669787938490L;

	public GenericExeption(String message) {
		super(message);
	}

	public GenericExeption(String message, Exception e) {
		super(message, e);
	}
}
