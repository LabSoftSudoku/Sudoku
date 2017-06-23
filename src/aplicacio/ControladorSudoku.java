package aplicacio;

import domini.Partida;

public class ControladorSudoku {

	private Partida partida;
	private ControladorJugador controladorJugador;

	public ControladorSudoku(ControladorJugador controladorJugador) throws Exception {
		
		
		this.controladorJugador = controladorJugador;
		this.partida = controladorJugador.getPartida();
		
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

	public String mostrarSodoku() {
		return partida.mostarTaulell();
	}

	public boolean isSodokuComplet() {
		return partida.isTaulellComplet();
	}

	public int[] getUltimError() {
		return partida.getUltimError();
	}
	
	public void guardarPartida() throws Exception{
		controladorJugador.guardarPartida(partida);
	}
	
	public void setOffline() throws Exception{
		controladorJugador.setOffline();
	}
	
	public void borrarPartida() throws Exception{
		controladorJugador.borrarPartida(partida);
	}
	
	public void shakeTaulell() throws Exception {
		partida.shakeTaulell();
		
	}

}
