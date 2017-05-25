package aplicacio;

import domini.Taulell;
import persistencia.JugadorBBDD;
import persistencia.LoginBBDD;

public class ControladorBBDD {
	
	private JugadorBBDD  jugadorBBDD;
	
	public ControladorBBDD(String user, String password) throws Exception{
		LoginBBDD.getInstancia(user, password);
	}
	
	
	//Jugador
	
	public boolean modificarEstatJugador(){
		return false;
	}
	
	public boolean getEstatJugador(){
		return false;
	}
	
	//Partida
	public boolean guardarPartida(){
		return false;
	}
	
	public Taulell getPartida(){
		return null;
		
	}
	
	public boolean borrarPartida(){
		return false;
	}
	
	public boolean modificarPartida(){
		return false;
	}
	
}
