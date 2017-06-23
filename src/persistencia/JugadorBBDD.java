package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.HashMap;

import domini.Partida;


class JugadorBBDD {
	
	final static int ONLINE = 1;
	final static int OFFLINE = 0;
	
	public boolean existJugador(String nom) throws Exception{
		
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
	
	public void crearJuagador(String nom) throws Exception{
		
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
	
	private void canviarEstat(String nom, int estatJugant) throws Exception{
		
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
	
	public void setOnline(String nom) throws Exception{
		canviarEstat(nom, ONLINE);
	}
	
	public void setOffline(String nom) throws Exception{
		canviarEstat(nom, OFFLINE);
	}
	
	public boolean getEstatJugant(String nom) throws Exception{
		
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
	
}
