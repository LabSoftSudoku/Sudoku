package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.OracleDriver;

class LoginBBDD {

	private static LoginBBDD instancia;
	private Connection connection;

	private LoginBBDD(String user, String password) throws Exception {
		try {
			DriverManager.registerDriver(new OracleDriver());
			
			connect(user, password);
			
		} catch (Exception e) {
			throw new Exception("Error al conectar a la base de dades", e);
		}
		
	}
	
	private void connect(String user, String password) throws SQLException{
		connection = DriverManager.getConnection(
				"jdbc:oracle:thin:@kali.tecnocampus.cat:1521:sapiens",user,password);
	}
	
	
	//singleton
	public synchronized static LoginBBDD getInstancia(String user, String password) throws Exception {
		if (instancia == null) {
			instancia = new LoginBBDD(user, password);
		}
		return instancia;
	}

	public synchronized static LoginBBDD getInstancia() throws Exception {
		if (instancia == null) {
			throw new Exception("Primer genera el LoginBBDD.");
		}
		return instancia;
	}
	
	
	//Connection
	public Statement createStatement() throws SQLException {
		return connection.createStatement();
	}
	
	public PreparedStatement prepareStatement(String sql) throws SQLException {
		return connection.prepareStatement(sql);
	}
}
