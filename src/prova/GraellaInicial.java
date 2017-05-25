package prova;

import aplicacio.Generador;
import domini.Taulell;

public class GraellaInicial extends Generador {

	private int graellaIni[][] = { { 5, 3, 0, 0, 7, 0, 0, 0, 0 }, { 6, 0, 0, 1, 9, 5, 0, 0, 0 },
			{ 0, 9, 8, 0, 0, 0, 0, 6, 0 }, { 8, 0, 0, 0, 6, 0, 0, 0, 3 }, { 4, 0, 0, 8, 0, 3, 0, 0, 1 },
			{ 7, 0, 0, 0, 2, 0, 0, 0, 6 }, { 0, 6, 0, 0, 0, 0, 2, 8, 0 }, { 0, 0, 0, 4, 1, 9, 0, 0, 5 },
			{ 0, 0, 0, 0, 8, 0, 0, 7, 9 } };

	@Override
	public void graellaInicial(Taulell t) throws Exception {
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (graellaIni[i][j] != 0) {
					t.addValorTaulell(i + 1, j + 1, graellaIni[i][j]);
					t.setIsCasellaInicial(i + 1, j + 1);
				}
			}
		}
	}
}
