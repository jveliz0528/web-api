package ar.com.educacionit.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.management.RuntimeErrorException;

public class AdministradorDeConexiones {
	
	private AdministradorDeConexiones() {

	}
	
	public static Connection obtenerConexion()
	{
	 
        // Establece el nombre del driver a utilizar
        String dbDriver = "com.mysql.cj.jdbc.Driver";
    	
    	// Establece la url y base de datos a utilizar
        String dbConnString = "jdbc:mysql://localhost:3306/producto?serverTimezone=UTC";
    	
    	// Establece el usuario de la base de datos
        String dbUser = "root";
       
        // Establece la contrase�a de la base de datos
        String dbPassword = "root";
       
        // Establece el driver de conexion
        try {
			Class.forName(dbDriver).newInstance();
			
			// Retorna la conexion
			return DriverManager.getConnection(dbConnString, dbUser, dbPassword);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException| SQLException e) {
			e.printStackTrace();
			throw new RuntimeErrorException(new Error(e), e.getMessage());
		}
	}
}
