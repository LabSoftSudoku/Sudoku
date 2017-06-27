package aplicacio;

import java.util.Date;
import java.util.HashMap;

import domini.Jugador;
import domini.Partida;
import persistencia.FacadeBBDD;

public class ControladorJugador {
	private FacadeBBDD facadeBBDD;
	private Jugador jugador;
	
	public ControladorJugador(String user, String password, String nom) throws Exception {
		
		this.facadeBBDD = new FacadeBBDD(user, password);
		
		if(!facadeBBDD.existJugador(nom)){
			facadeBBDD.crearJuagador(nom);
		}else{
			if(facadeBBDD.getEstatJugant(nom))throw new Exception("Aquest jugador ja esta online actualment");
			facadeBBDD.setOnline(nom);
		}
		jugador = new Jugador(nom, facadeBBDD.getInfoPartides(nom));
	}
	
	
	
	//Jugador
	
	public void setOffline() throws Exception{
		facadeBBDD.setOffline(jugador.getNom());
	
	}
	
	//Partida
	public void guardarPartida(Partida partida) throws Exception{
		facadeBBDD.guardarPartida(jugador.getNom(), partida);
	}
	
	Partida getPartida(){
		return jugador.getPartidaActual();
	}
	
	public void borrarPartida(Partida partida) throws Exception {
		facadeBBDD.guardarPartida(jugador.getNom(), partida);
	}
	
	public HashMap<Integer, Date> getInfoPartides() { //hashs
		return jugador.getInfoPartides();
	}
	
	public void carregarPartida(int id) throws Exception{
		jugador.cargarPartida(facadeBBDD.carregarPartida(jugador.getNom(), id, jugador.getInfoPartides().get(id)));
	}

	public void novaPartida() throws Exception {
		jugador.crearPartida(facadeBBDD.getInfoPartides(jugador.getNom()));
	}
	
	public void generarGraellaAux() throws Exception{
		jugador.generarGraellaAux();
	}

}
