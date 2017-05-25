package domini;

import java.util.HashSet;
import java.util.Set;

import aplicacio.Generador;

public class Taulell {

	private Casella taulell[][];
	private int[] ultimError = new int[2];

	public Taulell() throws Exception {

		taulell = new Casella[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				taulell[i][j] = new Casella();
			}
		}
	}

	public void generarGraellaAux() throws Exception {
		Generador.generarTaulell(this);
	}

	public boolean isTaulellComplet() {

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (taulell[i][j].esBuit())
					return false;
			}
		}
		return true;
	}

	public void setIsCasellaInicial(int fila, int columna) throws Exception {
		new Coordenades(fila, columna);
		taulell[fila - 1][columna - 1].setIsCasellaInicial();
	}

	public void esborrarCasella(int fila, int columna) throws Exception {
		new Coordenades(fila, columna);
		taulell[fila - 1][columna - 1].buidar();
	}

	public void addValorTaulell(int fila, int columna, int valor) throws Exception {

		Coordenades coor = new Coordenades(fila, columna);

		if (taulell[fila - 1][columna - 1].isCasellaInicial())
			throw new Exception("ERROR: Es una casella inicial");

		if (valor == taulell[fila - 1][columna - 1].getValor())
			return;

		Set<Coordenades> conjuntCaselles = new HashSet<Coordenades>();
		conjuntCaselles.addAll(coor.getConjuntoFila());
		conjuntCaselles.addAll(coor.getConjuntoColumna());
		conjuntCaselles.addAll(coor.getConjuntoRegio());

		if (valor != Casella.BUIT) {
			for (Coordenades c : conjuntCaselles) {
				int auxFila = c.getFila();
				int auxColumna = c.getColumna();
				if (taulell[auxFila - 1][auxColumna - 1].getValor() == valor) {
					ultimError[0] = auxFila - 1;
					ultimError[1] = auxColumna - 1;
					return;
				}
			}
		}

		taulell[fila - 1][columna - 1].setValor(valor);
		ultimError[0] = -1;
		ultimError[1] = -1;
	}

	public int[] getUltimError() {
		return ultimError;
	}

	public void genererNouTaulell() throws Exception {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (!taulell[i][j].isCasellaInicial()) {
					esborrarCasella(i + 1, j + 1);
				}
			}
		}
		Shake.shakeTaulell(taulell);
	}

	public String[][] getNumerosInicials() {

		String t[][] = new String[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {

				if (!taulell[i][j].isCasellaInicial()) {
					t[i][j] = null;
				} else {
					t[i][j] = "" + taulell[i][j].getValor();
				}
			}
		}

		return t;
	}

	public void setNumerosInicials() throws Exception {

		String[][] num = getNumeros();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (num[i][j] != "") {
					setIsCasellaInicial(i + 1, j + 1);
				}
			}
		}
	}

	public String mostarTaulell() {
		String text = "";
		int aux = 0;
		text += "\n";
		text += "    1 2 3 | 4 5 6 | 7 8 9\n";
		text += "   -----------------------\n";
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				text += ((x * 3 + y) + 1) + " | ";
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						aux = taulell[x * 3 + y][i * 3 + j].getValor();
						if (aux == Casella.BUIT) {
							text += "? ";
						} else {
							text += aux + " ";
						}
					}
					text += "| ";
				}
				text += "\n";
			}
			text += "   -----------------------\n";
		}
		text += "\n";
		return text;
	}

	public String[][] getNumeros() {

		String t[][] = new String[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {

				if (taulell[i][j].esBuit()) {
					t[i][j] = "";
				} else {
					t[i][j] = "" + taulell[i][j].getValor();
				}
			}
		}

		return t;
	}
	
}
