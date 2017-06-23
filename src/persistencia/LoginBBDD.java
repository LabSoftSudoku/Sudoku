package persistencia;

class LoginBBDD {
	
	private ConnectionBBDD connection;
	private static LoginBBDD instancia;
	
	private LoginBBDD(String user, String password) throws Exception{
		login(user, password);
	}
	
	public void login(String user, String password) throws Exception{
		if(connection==null){
			connection = new ConnectionBBDD(user, password);
		}else{
			throw new Exception("Ja has iniciat sessió.");
		}
	}
	
	ConnectionBBDD getConnection() throws Exception{
		if(connection == null) throw new Exception("No ha iniciat sessio.");
		return connection;
	}
	
	public synchronized static LoginBBDD getInstancia(String user, String password) throws Exception{
		if(instancia == null){
			instancia = new LoginBBDD(user,password);
		}
		return instancia;
	}
	
	public synchronized static LoginBBDD getInstancia() throws Exception{
		if(instancia == null){
			throw new Exception("Primer genera el LoginBBDD.");
		}
		return instancia;
	}
}
