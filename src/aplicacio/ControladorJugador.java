package aplicacio;

import java.sql.Timestamp;
import java.util.HashMap;

import domini.Partida;
import persistencia.JugadorBBDD;
import persistencia.LoginBBDD;

public class ControladorJugador {
	
	private JugadorBBDD jugadorBBDD;
	
	public ControladorJugador(String user, String password, String nom) throws Exception {
		LoginBBDD.getInstancia(user, password);
		jugadorBBDD = new JugadorBBDD(nom);
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
	
	public Partida getPartida(){
		return null;
		
	}
	
	public boolean borrarPartida(){
		return false;
	}
	
	public boolean modificarPartida(){
		return false;
	}
	
	public HashMap<Integer, Timestamp> getInfoPartides() throws Exception{
		return jugadorBBDD.getInfoPartides();
	}
	
}
