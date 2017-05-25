package domini;

import java.util.HashSet;
import java.util.Set;

class Coordenades {

	private int fila;
	private int columna;

	public Coordenades(int fila, int columna) throws Exception {
		if (fila < 1 || fila > 9)
			throw new Exception("ERROR: valor de la fila incorrecte");
		this.fila = fila;
		if (columna < 1 || columna > 9)
			throw new Exception("ERROR: valor de la columna incorrecte");
		this.columna = columna;

	}

	public int getFila() {
		return this.fila;
	}

	public int getColumna() {
		return this.columna;
	}

	public Set<Coordenades> getConjuntoFila() throws Exception {
		Set<Coordenades> fila = new HashSet<Coordenades>();
		for (int i = 1; i < 10; i++) {
			fila.add(new Coordenades(this.fila, i));
		}
		return fila;

	}

	public Set<Coordenades> getConjuntoColumna() throws Exception {
		Set<Coordenades> columna = new HashSet<Coordenades>();
		for (int i = 1; i < 10; i++) {
			columna.add(new Coordenades(i, this.columna));
		}
		return columna;
	}

	public Set<Coordenades> getConjuntoRegio() throws Exception {
		Set<Coordenades> regio = new HashSet<Coordenades>();

		int filaBloc = quinBloc(this.fila);
		int columnaBloc = quinBloc(this.columna);

		for (int i = filaBloc; i < filaBloc + 3; i++) {
			for (int j = columnaBloc; j < columnaBloc + 3; j++) {
				regio.add(new Coordenades(i, j));
			}
		}
		return regio;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Coordenades)
			if(this.columna == ((Coordenades)obj).columna && this.fila == ((Coordenades)obj).fila)
				return true;
		return false;
	}

	private int quinBloc(int num) {
		return (((num-1)/3)*3)+1;
	}
}
