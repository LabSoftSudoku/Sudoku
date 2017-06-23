package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import domini.Partida;

 class CasellaBBDD {
	
	final static int INICIAL = 1;
	final static int NOINICIAL = 0;
	
	void guardarCasellas(String nom, Partida partida) throws Exception{
		
		ConnectionBBDD connection = LoginBBDD.getInstancia().getConnection();
		
		String[][] taulellInicial = partida.getNumerosInicials();
		String[][] taulell = partida.getNumeros();
		
		String sql = "insert into CASELLA values (?, ?, ?, ?, ?, ?)";
		
		
		
		
		for (int i = 0; i < taulell.length; i++) {
			for (int j = 0; j < taulell.length; j++) {
				
				sql = "insert into CASELLA values (?, ?, ?, ?, ?, ?)";
				
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
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
		//preparedStatement.close();
	}
	
	
	public Partida cargarCaselles(String nom, int id, Date date) throws Exception{
		
		ConnectionBBDD connection = LoginBBDD.getInstancia().getConnection();

		Partida partida = new Partida(id, date);

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
	
	public void updateCaselles (String nom, Partida partida) throws Exception{
		ConnectionBBDD connection = LoginBBDD.getInstancia().getConnection();

		String[][] taulell = partida.getNumeros();
		String[][] taulellInicial = partida.getNumerosInicials();

		try {
			for (int i = 0; i < taulell.length; i++) {
				for (int j = 0; j < taulell.length; j++) {
					String sql = "UPDATE CASELLA set VALOR = ?, ISEDITABLE = ? WHERE NOMJUGADOR = ? AND IDSUDOKU = ? AND COORX = ? AND COORY = ?";
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					preparedStatement.clearParameters();
					
					preparedStatement.setString(3, nom);
					preparedStatement.setInt(4, partida.getId());
					
					preparedStatement.setInt(5, i); // COORX 0-8
					preparedStatement.setInt(6, j); // COORY 0-8

					if (taulell[i][j].length() == 0) {
						preparedStatement.setInt(1, 0); // VALOR 0-9
					} else {
						//son necesarios los parseInt
						preparedStatement.setInt(1, Integer.parseInt(taulell[i][j])); // VALOR
																						// 1-9
					}
					
					if (taulellInicial[i][j] != null) {
						preparedStatement.setInt(2, INICIAL); // ISEDITABLE
																// EDITABLE-NOEDITABLE
					} else {
						preparedStatement.setInt(2, NOINICIAL); // ISEDITABLE
																// EDITABLE-NOEDITABLE
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
}
