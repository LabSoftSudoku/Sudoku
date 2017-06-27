package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

import domini.Partida;

class PartidaBBDD {

	public HashMap<Integer, Date> getPartides(String nom) throws Exception {

		LoginBBDD connection = LoginBBDD.getInstancia();
		
		try {

			String sql = "select IDSUDOKU, HORACREACIO from SUDOKU where NOMJUGADOR = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.clearParameters();
			preparedStatement.setString(1, nom);

			ResultSet rs = preparedStatement.executeQuery();

			HashMap<Integer, Date> partides = new HashMap<Integer, Date>();

			while (rs.next()) {
				partides.put(rs.getInt("IDSUDOKU"), new Date(rs.getTimestamp("HORACREACIO").getTime()));
			}
			rs.close();
			preparedStatement.close();
			return partides;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al obtenir partides de base de dades");
		}

	}

	public void guardarPartida(String nom, Partida partida) throws Exception {

		LoginBBDD connection = LoginBBDD.getInstancia();
		
		try {

			String sql = "insert into SUDOKU values (?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.clearParameters();
			preparedStatement.setString(1, nom);
			preparedStatement.setTimestamp(2, new Timestamp(partida.getDate().getTime()));
			preparedStatement.setInt(3, partida.getId());

			preparedStatement.executeQuery();

			preparedStatement.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al guardar una nova partida");
		}

	}

	public boolean existPartida(int id, String nom) throws Exception {

		LoginBBDD connection = LoginBBDD.getInstancia();
		
		try {
			String sql = "select * from SUDOKU where IDSUDOKU = ? AND NOMJUGADOR = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.clearParameters();
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, nom);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				rs.close();
				preparedStatement.close();
				return true;

			}
			rs.close();
			preparedStatement.close();

			return false;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al comprovar si existeix la partida a BBDD");
		}
	}

	public void borrarPartida(Partida partida, String nom) throws Exception {
		if (existPartida(partida.getId(), nom)) {

			LoginBBDD connection = LoginBBDD.getInstancia();
			
			try {

				String sql = "delete from CASELLA where NOMJUGADOR = ? and IDSUDOKU = ?";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.clearParameters();
				preparedStatement.setString(1, nom);
				preparedStatement.setInt(2, partida.getId());

				preparedStatement.executeQuery();

				preparedStatement.close();

				sql = "delete from SUDOKU where NOMJUGADOR = ? and IDSUDOKU = ?";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.clearParameters();
				preparedStatement.setString(1, nom);
				preparedStatement.setInt(2, partida.getId());

				preparedStatement.executeQuery();

				preparedStatement.close();

			} catch (Exception e) {
				throw new Exception("Error al borrar la patida");
			}
		}
	}

}
