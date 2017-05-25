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
	
	public void crearPartida(){
		//partidaActual = new Partida(gestioID());
	}
	
	public Partida getPartidaActual(){
		return partidaActual;
	}
	

}
