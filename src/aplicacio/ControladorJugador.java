package aplicacio;

import java.sql.Timestamp;
import java.util.HashMap;

import domini.Jugador;
import domini.Partida;
import persistencia.JugadorBBDD;
import persistencia.LoginBBDD;

public class ControladorJugador {
	
	private JugadorBBDD jugadorBBDD;
	private Jugador jugador;
	
	public ControladorJugador(String user, String password, String nom) throws Exception {
		LoginBBDD.getInstancia(user, password);
		jugadorBBDD = new JugadorBBDD(nom);
		jugador = new Jugador(nom, jugadorBBDD.getInfoPartides());
	}
	
	
	//Jugador
	
	public void setOffline() throws Exception {
		jugadorBBDD.setOffline();
	}
	
	//Partida
	public void guardarPartida(Partida partida) throws Exception{
		jugadorBBDD.guardarPartida(partida);
	}
	
	 Partida getPartida(){ //sol domini mirar tots
		return jugador.getPartidaActual();
		
	}
	
	public void borrarPartida(Partida partida) throws Exception{
		jugadorBBDD.borrarPartida(partida);
	}
	
	public HashMap<Integer, Timestamp> getInfoPartides() throws Exception{
		return jugador.getInfoPartides();
	}
	
	public void carregarPartida(int id) throws Exception{
		jugador.cargarPartida(jugadorBBDD.carregarPartida(id, jugador.getInfoPartides().get(id)));
	}

	public void novaPartida() throws Exception {
		jugador.crearPartida(jugadorBBDD.getInfoPartides());
	}
	
	public void generarGraellaAux() throws Exception{
		jugador.generarGraellaAux();
	}

	public void novaPartidaAmbInfo(String[][] numerosInicials) throws Exception {
		jugador.crearPartidaAmbInfo(jugadorBBDD.getInfoPartides(), numerosInicials);
	}
}
