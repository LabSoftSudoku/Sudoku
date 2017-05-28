package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.HashMap;

import domini.Partida;


public class JugadorBBDD {
	
	final static int ONLINE = 1;
	final static int OFFLINE = 0;
	
	private String nom;
	private PartidaBBDD partidaBBDD;

	public JugadorBBDD(String nom) throws Exception{
		this.nom = nom;
		partidaBBDD = new PartidaBBDD();
		
		if(!existJugador()){
			crearJuagador();
		}else{
			if(getEstatJugant())throw new Exception("Aquest jugador ja esta online actualment");
			this.setOnline();
		}
	}
	
	private boolean existJugador() throws Exception{
		
		ConnectionBBDD connection = LoginBBDD.getInstancia().getConnection();
		
		try{
			String sql = "SELECT * FROM JUGADOR WHERE NOMJUGADOR = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.clearParameters();
			preparedStatement.setString(1, nom);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			
			while (rs.next()) {
				rs.close();
				preparedStatement.close();
				return true;
			}
			rs.close();
			preparedStatement.close();
			return false;
		} catch(Exception e){
			System.out.println(e);
			throw new Exception("Error al comprovar si existeix el jugador");
		}
		
	}
	
	private void crearJuagador() throws Exception{
		
		ConnectionBBDD connection = LoginBBDD.getInstancia().getConnection(); 
		
		try{
			
			String sql = "INSERT INTO JUGADOR VALUES (?, 1)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.clearParameters();
			preparedStatement.setString(1, nom);
			
			preparedStatement.executeQuery();
			
			preparedStatement.close();
			
			
		} catch(Exception e){
			System.out.println(e);
			throw new Exception("Error al crear un nou jugador");
		}
	}
	
	private void canviarEstat(int estatJugant) throws Exception{
		
		ConnectionBBDD connection = LoginBBDD.getInstancia().getConnection(); 
		
		try{
			
			String sql = "UPDATE JUGADOR SET ESTATJUGANT = ? WHERE NOMJUGADOR = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.clearParameters();
			
			preparedStatement.setInt(1, estatJugant);

			
			preparedStatement.setString(2, nom);
			
			preparedStatement.executeQuery();
			
			preparedStatement.close();
			
			
		} catch(Exception e){
			System.out.println(e);
			throw new Exception("Error al canviar l'estat del jugador");
		}
	}
	
	public void setOnline() throws Exception{
		canviarEstat(ONLINE);
	}
	
	public void setOffline() throws Exception{
		canviarEstat(OFFLINE);
	}
	
	public boolean getEstatJugant() throws Exception{
		
		ConnectionBBDD connection = LoginBBDD.getInstancia().getConnection();
		
		try{
			String sql = "select ESTATJUGANT from JUGADOR where NOMJUGADOR = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.clearParameters();
			preparedStatement.setString(1, nom);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			
			while (rs.next()) {
				if(rs.getInt(1) == ONLINE){
					rs.close();
					preparedStatement.close();
					return true;
				}
				rs.close();
				preparedStatement.close();
				return false;
			}
			rs.close();
			preparedStatement.close();
			throw new Exception("Jugador not found");
			
		} catch(Exception e){
			System.out.println(e);
			throw new Exception("Error al comprovar si el jugador ja ha iniciat sessio");
		}
	}
	
	public HashMap<Integer, Timestamp> getInfoPartides() throws Exception{
		return partidaBBDD.getPartides(nom);
	}
	
	public Partida carregarPartida(int id, Timestamp timestamp) throws Exception{
		return partidaBBDD.cargarPartida(nom, id, timestamp);
	}

	public void guardarPartida(Partida partida) throws Exception {
		partidaBBDD.guardarPartida(nom, partida);
	}

	public void borrarPartida(Partida partida) throws Exception {
		partidaBBDD.borrarPartida(partida, nom);
		
	}
	
}
