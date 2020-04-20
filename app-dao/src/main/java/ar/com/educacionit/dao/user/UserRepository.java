package ar.com.educacionit.dao.user;

import ar.com.eduacionit.app.domain.User;
import ar.com.educacionit.dao.exceptions.GenericExeption;

public interface UserRepository {

	public User getUser(String userName) throws GenericExeption;
}
