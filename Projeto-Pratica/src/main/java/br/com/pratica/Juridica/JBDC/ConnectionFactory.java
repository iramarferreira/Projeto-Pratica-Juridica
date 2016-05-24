package br.com.pratica.Juridica.JBDC;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class ConnectionFactory {

	public Connection getConnection() {
		try{
			Class.forName("org.postgresql.Driver");
			return DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/pratica-juridica", "postgres","root");
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}catch (ClassNotFoundException e){
			throw new RuntimeException(e);
		}
	}
	
}
