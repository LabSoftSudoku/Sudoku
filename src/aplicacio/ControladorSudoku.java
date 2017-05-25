package aplicacio;

import domini.Partida;
import persistencia.LoginBBDD;

public class ControladorSudoku {

	private Partida partida;
	private ControladorJugador controladorBBDD;

	public ControladorSudoku() throws Exception {
		
		partida = new Partida();
	}

	public void addValorSudoku(String fila, String columna, String valor) throws Exception {
		try {
			partida.addValorTaulell(Integer.parseInt(fila), Integer.parseInt(columna), Integer.parseInt(valor));
		} catch (NumberFormatException nfe) {
			throw new Exception("ERROR: els valor no son numeros");
		}
	}

	public void borrarValorSodoku(String fila, String columna) throws Exception {
		try {
			partida.esborrarCasella(Integer.parseInt(fila), Integer.parseInt(columna));
		} catch (NumberFormatException nfe) {
			throw new Exception("ERROR: els valor no son numeros");
		}
	}
	
	public void generarSudokuAux() throws Exception{
		partida.generarGraellaAux();
	}

	public String[][] getNumerosInicials() {
		return partida.getNumerosInicials();
	}
	
	public void setNumerosInicials() throws Exception{
		partida.setNumerosInicials();
	}

	public String[][] getNumeros() {
		return partida.getNumeros();
	}

	public void generarNouSudoku() throws Exception {
		partida.genererNouTaulell();
	}

	public String mostrarSodoku() {
		return partida.mostarTaulell();
	}

	public boolean isSodokuComplet() {
		return partida.isTaulellComplet();
	}

	public int[] getUltimError() {
		return partida.getUltimError();
	}
	
	public void login(String user, String password, String nom) throws Exception{
		controladorBBDD = new ControladorJugador(user, password, nom);
	}

}
