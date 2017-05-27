package domini;

import java.sql.Timestamp;
import java.util.HashMap;

public class Jugador {
	
	private String nom;
	private boolean estaJugant;
	private Partida partidaActual;
	private HashMap<Integer, Timestamp> infoPartides;
	
	
	public Jugador(String nom, HashMap<Integer, Timestamp> partides){
		this.nom = nom;
		this.infoPartides = partides;
		this.estaJugant = true;	
		
	}
	
	public void setPartida (Partida partidaActual){
		this.partidaActual = partidaActual;
	}

	public String getNom() {
		return nom;
	}

	public boolean isEstaJugant() {
		return estaJugant;
	}
	
	private int gestioID(){
		for (int i = 0; i < infoPartides.size(); i++) {
			if(!infoPartides.containsKey(i)){
				return i;
			}
		}
		return infoPartides.size();
	}
	
	public void crearPartida(HashMap<Integer, Timestamp> infoPartides) throws Exception{
		this.infoPartides = infoPartides;
		partidaActual = new Partida(gestioID());
	}
	
	public void generarGraellaAux() throws Exception{
		partidaActual.generarGraellaAux();
	}
	
	public Partida getPartidaActual(){
		return partidaActual;
	}
	
	public void cargarPartida(Partida partida){
		partidaActual = partida;
	}

	public HashMap<Integer, Timestamp> getInfoPartides() {
		return infoPartides;
	}

	public void crearPartidaAmbInfo(HashMap<Integer, Timestamp> infoPartides, String[][] numerosInicials) throws Exception {
		this.infoPartides = infoPartides;
		partidaActual = new Partida(gestioID(), numerosInicials);
		
	}


}
