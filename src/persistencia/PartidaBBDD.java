package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.HashMap;

import domini.Partida;

class PartidaBBDD {
	
	final static int INICIAL = 1;
	final static int NOINICIAL = 0;
	
	public HashMap<Integer, Timestamp> getPartides(String nom) throws Exception{
		
		ConnectionBBDD connection = LoginBBDD.getInstancia().getConnection();

		try{
			
			String sql = "select IDSUDOKU, HORACREACIO from SUDOKU where NOMJUGADOR = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.clearParameters();
			preparedStatement.setString(1, nom);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			HashMap<Integer, Timestamp> partides = new HashMap<Integer, Timestamp>();
			
			while (rs.next()) {
				partides.put(rs.getInt("IDSUDOKU"), rs.getTimestamp("HORACREACIO"));
			}
			rs.close();
			preparedStatement.close();
			return partides;
			
		} catch(Exception e){
			e.printStackTrace();
			throw new Exception("Error al getPartides");
		}
		
	}
	
	public void guardarPartida(String nom, Partida partida) throws Exception{
		if(existPartida(partida.getId())){
			guardarPartidaJaEsistent(nom, partida);
		}
		guardarPartidaNoExistent(nom, partida);
	}
	
	private void guardarPartidaNoExistent(String nom, Partida partida) throws Exception{
		
		ConnectionBBDD connection = LoginBBDD.getInstancia().getConnection();
		
		String[][] taulellInicial = partida.getNumerosInicials();
		String[][] taulell = partida.getNumeros();

		try{
			for (int i = 0; i < taulell.length; i++) {
				for (int j = 0; j < taulell.length; j++) {
					String sql = "insert into CASELLA values (?, ?, ?, ?, ?, ?)";
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					preparedStatement.clearParameters();
					preparedStatement.setString(1, nom);
					preparedStatement.setInt(2, partida.getId());
					
					
					preparedStatement.setInt(3, i); //COORX 0-8
					preparedStatement.setInt(4, j); //COORY 0-8
					preparedStatement.setInt(5, Integer.parseInt(taulell[i][j])); //VALOR 1-9
					
					if(taulellInicial[i][j].length()>0){
						preparedStatement.setInt(6, INICIAL); //ISEDITABLE EDITABLE-NOEDITABLE
					}else{
						preparedStatement.setInt(6, NOINICIAL); //ISEDITABLE EDITABLE-NOEDITABLE
					}
					
					preparedStatement.executeQuery();

					preparedStatement.close();
				}
			}
		} catch(Exception e){
			e.printStackTrace();
			throw new Exception("Error al guardarPartidaNoExistent");
		}
		
	}
	private void guardarPartidaJaEsistent(String nom, Partida partida) throws Exception{
		ConnectionBBDD connection = LoginBBDD.getInstancia().getConnection();
		
		String[][] taulellInicial = partida.getNumerosInicials();
		String[][] taulell = partida.getNumeros();

		try{
			for (int i = 0; i < taulell.length; i++) {
				for (int j = 0; j < taulell.length; j++) {
					String sql = "UPDATE";
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					preparedStatement.clearParameters();
					
					
					preparedStatement.setString(1, nom);
					preparedStatement.setInt(2, partida.getId());
					preparedStatement.setInt(3, i); //COORX 0-8
					preparedStatement.setInt(4, j); //COORY 0-8
					
					
					
					preparedStatement.setInt(5, Integer.parseInt(taulell[i][j])); //VALOR 1-9
					
					preparedStatement.executeQuery();

					preparedStatement.close();
				}
			}
		} catch(Exception e){
			e.printStackTrace();
			throw new Exception("Error al guardarPartidaNoExistent");
		}
	}
	
	
	
	public Partida cargarPartida(String nom, int id) throws Exception{
		ConnectionBBDD connection = LoginBBDD.getInstancia().getConnection();

		try{
			
			String sql = "select COORX, COORY, VALOR, ISEDITABLE from CASELLA where NOMJUGADOR = ? AND IDSUDOKU = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.clearParameters();
			preparedStatement.setString(1, nom);
			preparedStatement.setInt(2, id);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			
			while (rs.next()) {
				
				
				
				
				
			}
			rs.close();
			preparedStatement.close();
			
			
			return null;
			
		} catch(Exception e){
			e.printStackTrace();
			throw new Exception("Error al getPartides");
		}
		
	}
	
	public boolean existPartida(int id){
		return false;
	}
}
