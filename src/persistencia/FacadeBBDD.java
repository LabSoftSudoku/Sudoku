package persistencia;

import java.util.Date;
import java.util.HashMap;

import domini.Partida;

public class FacadeBBDD {
	
	private JugadorBBDD jugadorBBDD;
	private PartidaBBDD partidaBBDD;
	private CasellaBBDD casellaBBDD;
	
	
	public FacadeBBDD(String user, String password) throws Exception{
		try {
			LoginBBDD.getInstancia(user, password);
			
			this.jugadorBBDD = new JugadorBBDD();
			this.partidaBBDD = new PartidaBBDD();
			this.casellaBBDD = new CasellaBBDD();
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}


	public void setOffline(String nom) throws Exception {
		try {
			jugadorBBDD.setOffline(nom);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public void setOnline(String nom) throws Exception {
		try {
			jugadorBBDD.setOnline(nom);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}


	public void guardarPartida(String nom, Partida partida) throws Exception {
		try {
			if (partidaBBDD.existPartida(partida.getId(), nom)) {
				casellaBBDD.updateCaselles(nom, partida);
			} else {
				partidaBBDD.guardarPartida(nom, partida);
				casellaBBDD.guardarCasellas(nom, partida);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}


	public Partida carregarPartida(String nom, int id, Date date) throws Exception {
		try {
			return casellaBBDD.cargarCaselles(nom, id, date);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}


	public HashMap<Integer, Date> getInfoPartides(String nom) throws Exception {
		try {
			return partidaBBDD.getPartides(nom);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}


	public boolean existJugador(String nom) throws Exception {
		try {
			return jugadorBBDD.existJugador(nom);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}


	public void crearJuagador(String nom) throws Exception {
		try {
			jugadorBBDD.crearJuagador(nom);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}


	public boolean getEstatJugant(String nom) throws Exception {
		try {
			return jugadorBBDD.getEstatJugant(nom);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	
}
