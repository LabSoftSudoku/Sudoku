package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.HashMap;

import domini.Partida;

class PartidaBBDD {

	final static int INICIAL = 1;
	final static int NOINICIAL = 0;

	public HashMap<Integer, Timestamp> getPartides(String nom) throws Exception {

		ConnectionBBDD connection = LoginBBDD.getInstancia().getConnection();

		try {

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

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al obtenir partides de base de dades");
		}

	}

	public void guardarPartida(String nom, Partida partida) throws Exception {
		if (existPartida(partida.getId(), nom)) {
			guardarPartidaJaEsistent(nom, partida);
		} else {
			guardarPartidaNoExistent(nom, partida);
		}
	}

	private void guardarPartidaNoExistent(String nom, Partida partida) throws Exception {

		ConnectionBBDD connection = LoginBBDD.getInstancia().getConnection();

		String[][] taulellInicial = partida.getNumerosInicials();
		String[][] taulell = partida.getNumeros();

		try {

			String sql = "insert into SUDOKU values (?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.clearParameters();
			preparedStatement.setString(1, nom);
			preparedStatement.setTimestamp(2, partida.getTimestamp());
			preparedStatement.setInt(3, partida.getId());

			preparedStatement.executeQuery();

			preparedStatement.close();

			for (int i = 0; i < taulell.length; i++) {
				for (int j = 0; j < taulell.length; j++) {
					sql = "insert into CASELLA values (?, ?, ?, ?, ?, ?)";
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.clearParameters();
					preparedStatement.setString(1, nom);
					preparedStatement.setInt(2, partida.getId());

					preparedStatement.setInt(3, i); // COORX 0-8
					preparedStatement.setInt(4, j); // COORY 0-8

					if (taulell[i][j].length() == 0) {
						preparedStatement.setInt(5, 0); // VALOR 0-9
					} else {
						preparedStatement.setInt(5, Integer.parseInt(taulell[i][j])); // VALOR
																						// 1-9
					}

					if (taulellInicial[i][j] != null) {
						preparedStatement.setInt(6, INICIAL); // ISEDITABLE
																// EDITABLE-NOEDITABLE
					} else {
						preparedStatement.setInt(6, NOINICIAL); // ISEDITABLE
																// EDITABLE-NOEDITABLE
					}

					preparedStatement.executeQuery();

					preparedStatement.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al guardar una nova partida");
		}

	}

	private void guardarPartidaJaEsistent(String nom, Partida partida) throws Exception {
		ConnectionBBDD connection = LoginBBDD.getInstancia().getConnection();

		String[][] taulell = partida.getNumeros();

		try {
			for (int i = 0; i < taulell.length; i++) {
				for (int j = 0; j < taulell.length; j++) {
					String sql = "UPDATE CASELLA set VALOR = ? WHERE NOMJUGADOR = ? AND IDSUDOKU = ? AND COORX = ? AND COORY = ?";
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					preparedStatement.clearParameters();

					preparedStatement.setString(2, nom);
					preparedStatement.setInt(3, partida.getId());
					preparedStatement.setInt(4, i); // COORX 0-8
					preparedStatement.setInt(5, j); // COORY 0-8

					if (taulell[i][j].length() == 0) {
						preparedStatement.setInt(1, 0); // VALOR 0-9
					} else {
						preparedStatement.setInt(1, Integer.parseInt(taulell[i][j])); // VALOR
																						// 1-9
					}

					preparedStatement.executeQuery();
					preparedStatement.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al guardar una partida existent");
		}
	}

	public Partida cargarPartida(String nom, int id, Timestamp timestamp) throws Exception {
		ConnectionBBDD connection = LoginBBDD.getInstancia().getConnection();

		Partida partida = new Partida(id, timestamp);

		try {

			String sql = "select COORX, COORY, VALOR, ISEDITABLE from CASELLA where NOMJUGADOR = ? AND IDSUDOKU = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.clearParameters();
			preparedStatement.setString(1, nom);
			preparedStatement.setInt(2, id);

			ResultSet rs = preparedStatement.executeQuery();

			int coorX, coorY, valor;
			while (rs.next()) {
				coorX = rs.getInt("COORX") + 1;
				coorY = rs.getInt("COORY") + 1;
				valor = rs.getInt("VALOR");

				if (valor != 0) {
					partida.addValorTaulell(coorX, coorY, valor);
				}

				if (rs.getInt("ISEDITABLE") == INICIAL) {
					partida.setIsCasellaInicial(coorX, coorY);
				}

			}
			rs.close();
			preparedStatement.close();

			return partida;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al carregar una partida");
		}

	}

	public boolean existPartida(int id, String nom) throws Exception {

		ConnectionBBDD connection = LoginBBDD.getInstancia().getConnection();

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

			ConnectionBBDD connection = LoginBBDD.getInstancia().getConnection();

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
