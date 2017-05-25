package domini;

import java.util.Random;

class Shake {

	public static void shakeTaulell(Casella[][] taulell) {
		int moviments[] = new int[getRandom(1, 6)];
		int aux;
		
		System.out.println("SHAKE:");
		System.out.println("Número Total de canvis: " + moviments.length);

		for (int i = 0; i < moviments.length; i++) {

			aux = getRandom(1, 6);
			while (conteValor(moviments, aux)) {
				aux = getRandom(1, 6);
			}
			System.out.print(i + 1 + ". ");
			moviments[i] = aux;
			seleccioMetode(aux, taulell);

		}
		System.out.println("FI\n");
	}

	private static boolean conteValor(int[] moviments, int aux) {
		for (int i = 0; i < moviments.length; i++) {
			if (moviments[i] == aux)
				return true;
		}
		return false;
	}

	private static void seleccioMetode(int num, Casella[][] taulell) {
		switch (num) {
		case 1:
			intercanviFilaRegio(taulell);
			break;
		case 2:
			intercanviColumnaRegio(taulell);
			break;
		case 3:
			transpossarGraella(taulell, true);
			break;
		case 4:
			girarGraella(taulell);
			break;
		case 5:
			intercanviarRegioHoritzontal(taulell);
			break;
		case 6:
			intercanviarRegioVertical(taulell);
			break;
		}
	}

	private static void intercanviFilaRegio(Casella[][] taulell) {
		int[][] intercanvis = { { 0, 1 }, { 0, 2 }, { 1, 2 }, { 3, 4 }, { 3, 5 }, { 4, 5 }, { 6, 7 }, { 6, 8 },
				{ 7, 8 } };
		int r = getRandom(0, 8);
		intercanviFila(taulell, intercanvis[r][0], intercanvis[r][1]);
		System.out.println("Intercanvi fila: " + intercanvis[r][0] + " : " + intercanvis[r][1]);
	}

	private static void intercanviColumnaRegio(Casella[][] taulell) {
		int[][] intercanvis = { { 0, 1 }, { 0, 2 }, { 1, 2 }, { 3, 4 }, { 3, 5 }, { 4, 5 }, { 6, 7 }, { 6, 8 },
				{ 7, 8 } };
		int r = getRandom(0, 8);
		intercanviColumna(taulell, intercanvis[r][0], intercanvis[r][1]);
		System.out.println("Intercanvi columna: " + intercanvis[r][0] + " : " + intercanvis[r][1]);
	}

	private static void intercanviFila(Casella[][] taulell, int fila1, int fila2) {
		Casella aux[] = taulell[fila1];
		taulell[fila1] = taulell[fila2];
		taulell[fila2] = aux;
	}

	private static void intercanviColumna(Casella[][] taulell, int columna1, int columna2) {
		Casella aux;
		for (int fila = 0; fila < 9; fila++) {
			aux = taulell[fila][columna1];
			taulell[fila][columna1] = taulell[fila][columna2];
			taulell[fila][columna2] = aux;
		}
	}

	private static void transpossarGraella(Casella[][] taulell, boolean b) {
		Casella aux;
		for (int x = 0; x < 8; x++) {
			for (int y = x + 1; y < 9; y++) {
				aux = taulell[x][y];
				taulell[x][y] = taulell[y][x];
				taulell[y][x] = aux;
			}
		}
		if (b)
			System.out.println("Transpossar graella.");
	}

	private static void girarGraella(Casella[][] taulell) {
		int num = getRandom(1, 3);
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < 4; j++) {
				intercanviFila(taulell, j, 8 - j);
			}
			transpossarGraella(taulell, false);
		}
		System.out.println("Girar graella: " + num);
	}

	private static void intercanviarRegioHoritzontal(Casella[][] taulell) {
		int[][] intercanvis = { { 0, 1 }, { 0, 2 }, { 1, 2 } };
		int r = getRandom(0, 2);

		intercanviFila(taulell, intercanvis[r][0] * 3, intercanvis[r][1] * 3);
		intercanviFila(taulell, intercanvis[r][0] * 3 + 1, intercanvis[r][1] * 3 + 1);
		intercanviFila(taulell, intercanvis[r][0] * 3 + 2, intercanvis[r][1] * 3 + 2);

		System.out.println("Intercanvi regió horitzontal: " + intercanvis[r][0] + " : " + intercanvis[r][1]);
	}

	private static void intercanviarRegioVertical(Casella[][] taulell) {
		int[][] intercanvis = { { 0, 1 }, { 0, 2 }, { 1, 2 } };
		int r = getRandom(0, 2);

		intercanviColumna(taulell, intercanvis[r][0] * 3, intercanvis[r][1] * 3);
		intercanviColumna(taulell, intercanvis[r][0] * 3 + 1, intercanvis[r][1] * 3 + 1);
		intercanviColumna(taulell, intercanvis[r][0] * 3 + 2, intercanvis[r][1] * 3 + 2);

		System.out.println("Intercanvi regió vertical: " + intercanvis[r][0] + " : " + intercanvis[r][1]);
	}

	private static int getRandom(int min, int max) {
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

}
