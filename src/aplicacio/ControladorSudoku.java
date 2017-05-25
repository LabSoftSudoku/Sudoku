package aplicacio;

import domini.Taulell;

public class ControladorSudoku {

	private Taulell taulell;

	public ControladorSudoku() throws Exception {
		taulell = new Taulell();
	}

	public void addValorSudoku(String fila, String columna, String valor) throws Exception {
		try {
			taulell.addValorTaulell(Integer.parseInt(fila), Integer.parseInt(columna), Integer.parseInt(valor));
		} catch (NumberFormatException nfe) {
			throw new Exception("ERROR: els valor no son numeros");
		}
	}

	public void borrarValorSodoku(String fila, String columna) throws Exception {
		try {
			taulell.esborrarCasella(Integer.parseInt(fila), Integer.parseInt(columna));
		} catch (NumberFormatException nfe) {
			throw new Exception("ERROR: els valor no son numeros");
		}
	}
	
	public void generarSudokuAux() throws Exception{
		taulell.generarGraellaAux();
	}

	public String[][] getNumerosInicials() {
		return taulell.getNumerosInicials();
	}
	
	public void setNumerosInicials() throws Exception{
		taulell.setNumerosInicials();
	}

	public String[][] getNumeros() {
		return taulell.getNumeros();
	}

	public void generarNouSudoku() throws Exception {
		taulell.genererNouTaulell();
	}

	public String mostrarSodoku() {
		return taulell.mostarTaulell();
	}

	public boolean isSodokuComplet() {
		return taulell.isTaulellComplet();
	}

	public int[] getUltimError() {
		return taulell.getUltimError();
	}

}
